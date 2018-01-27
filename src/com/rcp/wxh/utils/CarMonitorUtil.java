package com.rcp.wxh.utils;

import java.util.Date;

import com.rcp.wxh.pojo.TCarEnter;
import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.service.impl.CardService;

/**
 * �������볡��ع���
 * 1-��Ƭ��Ч����֤
 * @author wuxuehong  2011-11-18
 *
 */
public class CarMonitorUtil {
	
	
	/**
	 * �����볡   ��֤�ÿ�����Ч��
	 * @param cardID  ����
	 * @param retMsg   ������Ϣ
	 * @return  true/false
	 */
	public static boolean isAviliable(TCard card, TCarEnter carEnter, StringBuffer retMsg){
		if(card == null){
			retMsg.append("�ÿ�δע��!");
			return false;
		}
		if(carEnter != null){
			retMsg.append("�ÿ��Ѿ�����볡,�޷��ظ��볡!");
			return false;
		}
		if(card.getCardstatus().intValue() == TCard.LOCKED){
			retMsg.append("�ÿ�������,���޷�ʹ��!");
			return false;
		}
		if(card.getCardtype().intValue() == TCard.TEMP_CARD){ //�������ʱ�����������
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			retMsg.append("��֤ͨ��!�볡ʱ��"+sdf.format(new Date()));
			return true;
		}else{
			int chargeType = card.getTCardType().getTChargeType().getType().intValue(); 
			switch(chargeType){
			case TChargeType.MINUTE:
			case TChargeType.HOUR:
				if(card.getCardprice()<=0){ // ������/Сʱ�շ�  ��֤�����
					retMsg.append("��Ƭ����!");
					return false;
				}
				break;
			case TChargeType.PERIOD:   //���շ���ʱ����շ�   ��֤������Ƿ����
				if(card.getCardendtime().getTime()<new Date().getTime()){ //�������
					retMsg.append("�ÿ������Ѿ�����!");
					return false;
				}
				break;
			case TChargeType.TIME: //���մ����Ʒ�   ��֤��ʣ��ͣ������
				if(card.getCardtimes().intValue()<1){
					retMsg.append("�ÿ�ͣ������������!");
					return false;
				}
				break;
			case TChargeType.FREE: //���
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				retMsg.append("��֤ͨ��!�볡ʱ��"+sdf.format(new Date()));
				return true;
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		retMsg.append("��֤ͨ��!�볡ʱ��"+sdf.format(new Date()));
		return true;
		}
	}
	
	
	/**
	 * ���ݳ����볡��Ϣ ��ȡ����ͣ����¼
	 * @param tce
	 * @return
	 */	
	public static TCarRecord getCarRecord(TCarEnter tce){
		TCarRecord tcr = new TCarRecord();
		tcr.setCarnumber(tce.getCarnumber()); //���ƺ���
		tcr.setEntertime(tce.getEntertime()); //�볡ʱ��
		tcr.setExittime(new Date()); //����ʱ��
		tcr.setTCard(tce.getTCard()); //��Ƭ��Ϣ
		getExpense(tcr);
		return tcr;
	}
	/**
	 * ���ݿ��ż������
	 * @param carRecord
	 * @return
	 */
	private static void getExpense(TCarRecord carRecord){
		long entertime = carRecord.getEntertime().getTime();
		long exittime = carRecord.getExittime().getTime();
		int period = (int) ((exittime-entertime)/60000); //ͣ��ʱ��
//System.out.println("ͣ��:"+period+"����");
		carRecord.setStoptime(period); 
		//��ȡ�շѷ�ʽ
		TChargeType chargeType = carRecord.getTCard().getTCardType().getTChargeType();
		if(period<=chargeType.getMinutecount().intValue()){  
			carRecord.setDueexpense(0f);
			carRecord.setFactexpense(0f);
			carRecord.setRemark("ͣ��ʱ��δ�����Ʒ�ʱ��!");
		}else{
			long pe = exittime-entertime;  //�����
			int count,over,add;
			switch(chargeType.getType().intValue()){
				case TChargeType.MINUTE:   //�����ӼƷ�
					  int seconds = (int) (pe/1000);                //����
					  count = seconds/(chargeType.getMinute()*60);   //�Ʒѵ�λ��
					  over = seconds%(chargeType.getMinute()*60);   //��ʱ����
					  add = over>chargeType.getOverminute().intValue()? 1:0;  //��ʱ ��һ���Ʒѵ�λ
					  count += add;
					  carRecord.setDueexpense(count*chargeType.getFee());  //Ӧ������
					  carRecord.setFactexpense(count*chargeType.getFee());
					  break;
				case TChargeType.HOUR: //��Сʱ�Ʒ�
					  count = period/(chargeType.getHour()*60); //�Ʒѵ�λ��
					  over = period%(chargeType.getHour()*60); //��ʱ������
					  add = over>chargeType.getOverhour().intValue()?1:0;
					  count += add;
					  carRecord.setDueexpense(count*chargeType.getFee());
					  carRecord.setFactexpense(carRecord.getDueexpense());
					  break;
				case TChargeType.TIME: //������
					  carRecord.setDueexpense(0f);
					  carRecord.setFactexpense(0f);
					  break;
				case TChargeType.FREE: //���
					 carRecord.setDueexpense(0f);
					 carRecord.setFactexpense(0f);
					 break;
				case TChargeType.PERIOD://��������
					carRecord.setDueexpense(0f);
					 carRecord.setFactexpense(0f);
					 break;
			}
		}
	}
}

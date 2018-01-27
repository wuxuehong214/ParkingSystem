package com.rcp.wxh.fortables;

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TMember;

/**
 * ���������¼  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class CarRecordLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TCarRecord record = (TCarRecord) element;
		Iterator<TMember> it = record.getTCard().getTMembers().iterator();
		TMember member = null;
		if(it.hasNext()){
			member = it.next();
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		switch (columnIndex) {
		case 0:      //����
			return record.getTCard().getCardid();
		case 1:   //���ƺ���
			return record.getCarnumber();
		case 2:  //������
			return record.getTCard().getTCardType().getName();
		case 3:   //�볡ʱ��
			return sdf.format(record.getEntertime());
		case 4:   //����ʱ��
			return sdf.format(record.getExittime());
		case 5:  //ͣ��ʱ��
			int times = record.getStoptime();  //����
			int hour = (int) (times/60);  //Сʱ��
			int minute = (int) (times%60); //������
			return hour>0? hour+"Сʱ"+minute+"����":minute+"����";
		case 6: //Ӧ�շ���
			return record.getDueexpense()+"";
		case 7: //ʵ�շ���
			return record.getFactexpense()+"";
		case 8: //��ע��Ϣ
			return record.getRemark();
		default:
			return "δ֪";
		}
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}

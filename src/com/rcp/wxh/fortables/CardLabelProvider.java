package com.rcp.wxh.fortables;

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TMember;

/**
 * ��Ƭ  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class CardLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TCard card = (TCard) element;
		Iterator<TMember> it = card.getTMembers().iterator();
		TMember member = null;
		if(it.hasNext()){
			member = it.next();
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		switch (columnIndex) {
		case 0:      //����
			return card.getCardid();
		case 1:   //������
			return card.getTCardType().getName();
		case 2:   //���ƺ�
			if(member!=null){
				return  member.getCarnumber();
			}else
				return "";
		case 3:   //��������
			if(member!=null)
					return member.getName();
			else
				return "";
		case 4:  //����
			if(member!=null){
				return member.getCartype();
			}else
				return "";
		case 5: //��ϵ��ʽ
			if(member != null){
				return member.getPhonenumber();
			}else
				return "";
		case 6: //�������� 
			return sdf.format(card.getCarddate());
		case 7: //��Ƭ״̬
		{
			switch(card.getCardstatus()){
				case TCard.AVILIABLE:{
					return "�Ѽ���";
				}
				case TCard.LOCKED:{
					return "����";
				}
			}
		}
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

package com.rcp.wxh.fortables;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.pojo.TChargeType;

/**
 * �շѷ�ʽ  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class ChargeTypeLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TChargeType tct = (TChargeType) element;
		String[] str = {"�������շ�","��Сʱ�շ�","�������շ�","��ʱ����շ�","���"};
		String temp = "";
		switch (columnIndex) {
		case 0:
			return tct.getName();
		case 1:
			return str[tct.getType()-1];
		case 2:{
			temp = "����"+tct.getMinutecount()+"���ӿ�ʼ�Ʒ�,";
			switch (tct.getType()) {
			case TChargeType.MINUTE:
				 return temp+"ÿ"+tct.getMinute()+"�����շ�"+tct.getFee()+"Ԫ,����"+tct.getOverminute()+"��Ϊһ���Ʒѵ�λ";
			case TChargeType.HOUR:
				return temp+"ÿ"+tct.getHour()+"Сʱ�շ�"+tct.getFee()+"Ԫ,����"+tct.getOverhour()+"����Ϊһ���Ʒѵ�λ";
			case TChargeType.TIME:
				return temp+"ÿ��ͣ���۳�����ͣ������һ��";
			case TChargeType.PERIOD:
				return "����Чʱ�����ͣ�����";
			case TChargeType.FREE:
				return "�κ�ʱ��ͣ�����";
			default:
				return "δ֪";
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

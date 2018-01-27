package com.rcp.wxh.fortables;

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.dialogs.Temp;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.pojo.TMember;

/**
 * Ա��  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class EmpLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TEmp emp = (TEmp) element;
		String[] pri =  {"����Ա","����Ա"};
		switch (columnIndex) {
		case 0:      //Ա��ID
			return emp.getOperatorid();
		case 1:   //Ա������
			return emp.getOperatorname();
		case 2:   //����Ȩ��
			return pri[emp.getPriority()];
		case 3:   //��ϵ��ʽ
			return emp.getPhonenumber();
		case 4:  //֤������
			return emp.getIdentification();
		case 5: //סַ
			return emp.getAddress();
		case 6: //��ע��Ϣ
			return emp.getRemark();
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

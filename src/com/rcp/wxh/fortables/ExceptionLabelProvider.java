package com.rcp.wxh.fortables;

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TMember;

/**
 * �쳣��Ϣ��¼  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class ExceptionLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TExceptionRecord exception = (TExceptionRecord) element;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		switch (columnIndex) {
		case 0:      //�쳣�¼�
			return exception.getException();
		case 1:   //�쳣����ʱ��
			return sdf.format(exception.getExceptiontime());
		case 2:   //��ע��Ϣ
			return exception.getRemark();
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

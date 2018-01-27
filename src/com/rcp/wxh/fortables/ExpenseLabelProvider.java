package com.rcp.wxh.fortables;

import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.pojo.TMember;

/**
 * ������Ϣ��¼  ���ݱ�ǩ�ṩ��
 * @author wuxuehong  2011-11-9
 *
 */
public class ExpenseLabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TExpenseRecord expense = (TExpenseRecord) element;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Iterator<TMember> it = expense.getTCard().getTMembers().iterator();
		TMember member = null;
		if(it.hasNext()){
			member = it.next();
		}
		switch (columnIndex) {
		case 0:      //������
			return expense.getTEmp().getOperatorname();
		case 1:   //����
			return expense.getTCard().getTCardType().getName();
		case 2:   //��������
			if(member != null) return member.getName();
			else return "";
		case 3:    //���ƺ���
			if(member != null) return member.getCarnumber();
			else return "";
		case 4:  //Ӧ������
			return expense.getDueexpense()+"";
		case 5: //ʵ������
			return expense.getFactexpense()+"";
		case 6:  //�ɷ�����
			return sdf.format(expense.getExpensedate());
		case 7:  //��ע��Ϣ
			return expense.getRemark();
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

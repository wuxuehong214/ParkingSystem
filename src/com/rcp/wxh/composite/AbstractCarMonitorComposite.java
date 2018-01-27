package com.rcp.wxh.composite;

import org.eclipse.swt.widgets.Composite;

import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;

/**
 * ��Ҫ���� ��Ƭ��Ϣ�� �������볡������
 * �⳵�����볡������
 * @author wuxuehong  2011-11-23
 *
 */
public abstract class AbstractCarMonitorComposite extends Composite {

	public AbstractCarMonitorComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	//����Ƭ��Ϣ
	/**
	 * @param card ��Ƭ��Ϣ
	 * @param info ��ʾ��Ϣ
	 * @param isOk �Ƿ��ܹ�ͨ��
	 * @param type  ���������
	 * @param carRecord ����ͣ����¼
	 */
	public abstract void dealWithUI(TCard card, String info,boolean isOk, byte type,TCarRecord carRecord);

}

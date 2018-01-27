package com.rcp.wxh.event;

import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.pojo.TSysRecord;

/**
 * ϵͳ�¼������ӿ�
 * ��Ҫ���������¼�������־û�
 * @author wuxuehong  2011-11-2
 *
 */
public interface ParkingSystemListener {

	/**
	 * ϵͳ�����쳣��¼ �������쳣ʱ�������¼�
	 * (��Ҫ�����ݿ�����쳣��Ϣ��¼)
	 * @param ter
	 */
	public void onException(TExceptionRecord ter);
	
	/**
	 * ���������ü�¼ʱ �������¼�
	 * ����ʱ��    ��Ա���շѼ�¼��
	 * @param ter
	 */
	public void onExpense(TExpenseRecord ter);
	
	/**
	 * ��������������ʱ�������¼�
	 * ������������ʱ��־��¼��
	 * @param tcr
	 */
	public void onCarPark(TCarRecord tcr);
	
	/**
	 * ��������Ҫ��¼��ϵͳ��־ʱ �������¼�
	 * @param tsr
	 */
	public void onSysOperation(TSysRecord tsr);
}

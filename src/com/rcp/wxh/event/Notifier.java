package com.rcp.wxh.event;

import java.util.ArrayList;
import java.util.List;

import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.pojo.TSysRecord;

/**
 * �¼�֪ͨ��
 * ����
 * @author wuxuehong  2011-11-15
 *
 */
public class Notifier {
	
	private static Notifier notifier = new Notifier();
	
	private Notifier(){}  //���ù��캯��
	/**
	 * ��������
	 */
	private List<ParkingSystemListener> listeners = new ArrayList<ParkingSystemListener>();

	/**
	 * ����Ψһʵ��
	 * @return
	 */
	public static Notifier getInstance(){
		if(notifier == null){
			return new Notifier();
		}
		else
			return notifier;
	}
	
	/**
	 * ���Ӽ�����
	 * @param listener
	 */
	public void addListener(ParkingSystemListener listener){
		listeners.add(listener);
	}
	
	/**
	 * ����ͣ���¼���Ӧ
	 * @param tcr
	 */
	public void fireCarPark(TCarRecord tcr){
		for(ParkingSystemListener l: listeners){
			l.onCarPark(tcr);
		}
	}
	
	/**
	 * �����쳣�¼���Ӧ
	 * @param ter
	 */
	public void fireException(TExceptionRecord ter){
		for(ParkingSystemListener l: listeners){
			l.onException(ter);
		}
	}
	
	/**
	 * �����쳣�¼���Ӧ
	 * @param ter
	 */
	public void fireExpense(TExpenseRecord ter){
		for(ParkingSystemListener l: listeners){
			l.onExpense(ter);
		}
	}
	
	/**
	 * ����ϵͳ�����¼���Ӧ
	 * @param tsr
	 */
	public void fireSystemOperation(TSysRecord tsr){
		for(ParkingSystemListener l: listeners){
			l.onSysOperation(tsr);
		}
	}

}

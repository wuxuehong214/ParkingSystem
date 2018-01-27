package com.rcp.wxh.service.impl;

import java.io.IOException;
import java.util.Date;

import com.rcp.wxh.event.Notifier;
import com.rcp.wxh.pojo.TExceptionRecord;

/**
 * �쳣��Ϣ�����¼�
 * @author wuxuehong  2011-11-15
 *
 */
public class ExceptionEvent {

	/**
	 * �����쳣��Ϣ
	 * @param event
	 */
	/**
	 * �����쳣�¼���Ӧ
	 * @param msg
	 */
   public void fireExceptionRecord(String event, String remark){
	   TExceptionRecord ter = new TExceptionRecord();
	   ter.setException(event);
	   ter.setExceptiontime(new Date());
	   ter.setRemark(remark);
	   Notifier.getInstance().fireException(ter);
   }
   
}

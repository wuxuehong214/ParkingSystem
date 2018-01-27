package com.rcp.wxh.service.impl;

import com.rcp.wxh.dao.impl.TSystemDao;
import com.rcp.wxh.pojo.TSystem;

public class SystemService extends ExceptionEvent {
	
	private final String module = "ϵͳ����ģ��";
	
	/**
	 * ��ȡϵͳ��Ϣ
	 * @return
	 * @throws Exception 
	 */
	public TSystem getSystemInfo() throws Exception{
		try{
			TSystemDao tsd = new TSystemDao();
			TSystem system = tsd.getSystemInfo();
			return system;
		}catch(Exception e){
			fireExceptionRecord("��ȡϵͳ��Ϣ�쳣!", module);
			throw e;
		}
	}
	
	/**
	 * ����ϵͳ��Ϣ
	 * @param system
	 * @throws Exception
	 */
	public void updateSystem(TSystem system)throws Exception{
		TSystemDao tsd = new TSystemDao();
		try {
			tsd.updateSystem(system);
		} catch (Exception e) {
			fireExceptionRecord("����ϵͳ��Ϣ�쳣!", module);
			throw e;
		}
	}

}

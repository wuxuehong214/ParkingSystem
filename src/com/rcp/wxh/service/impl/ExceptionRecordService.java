package com.rcp.wxh.service.impl;

import java.util.Date;
import java.util.List;

import com.rcp.wxh.dao.impl.TEmpDao;
import com.rcp.wxh.dao.impl.TExceptionRecordDAO;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.pojo.TExceptionRecord;

public class ExceptionRecordService {
	/**
	 * �����쳣��Ϣ��¼
	 * @param ecpRecord
	 * @throws Exception
	 */
	public void addExceptionRecord(TExceptionRecord ecpRecord) throws Exception{
		TExceptionRecordDAO tdao = new TExceptionRecordDAO();
		try{
			tdao.save(ecpRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ɾ���쳣��Ϣ��¼
	 * @param ecpRecord
	 * @throws Exception
	 */
	public void delExceptionRecord(TExceptionRecord ecpRecord) throws Exception{
		TExceptionRecordDAO tdao = new TExceptionRecordDAO();
		try{
			tdao.delte(ecpRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * �����쳣��Ϣ��¼
	 * @param ecpRecord
	 * @throws Exception
	 */
	public void updateExceptionRecord(TExceptionRecord ecpRecord) throws Exception{
		TExceptionRecordDAO tdao = new TExceptionRecordDAO();
		try{
			tdao.update(ecpRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * �����쳣��ϢID��ѯ�쳣��Ϣ��¼
	 * @param ecpRecordId
	 * @throws Exception
	 */
	public TExceptionRecord getEcpRecordById(int ecpRecordId) throws Exception{
		TExceptionRecordDAO tdao = new TExceptionRecordDAO();
		TExceptionRecord ecpRecord = null;
		try{
			ecpRecord = (TExceptionRecord)tdao.findById(TExceptionRecord.class, ecpRecordId);
		}catch (Exception e) {
			throw e;
		}
		return ecpRecord;
	}
	
	/**
	 * ����ʱ��β�ѯ�쳣��Ϣ��¼ 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getEcpRecordByPeriod(Date startTime, Date endTime) throws Exception{
		TExceptionRecordDAO tdao = new TExceptionRecordDAO();
		List<Object> exceptions = null;
		try {
			exceptions = tdao.getExceptionsByPeriod(startTime, endTime);
		} catch (Exception e) {
			throw e;
		}
		return exceptions;
	}
}

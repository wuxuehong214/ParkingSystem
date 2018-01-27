package com.rcp.wxh.service.impl;

import java.util.Date;
import java.util.List;

import com.rcp.wxh.dao.impl.TExceptionRecordDAO;
import com.rcp.wxh.dao.impl.TExpenseRecordDao;
import com.rcp.wxh.dao.impl.TSysRecordDao;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.pojo.TSysRecord;

public class ExpenseRecordService {
	/**
	 * �����շѼ�¼
	 * @param expRecord
	 * @throws Exception
	 */
	public void addExpenseRecord(TExpenseRecord expRecord) throws Exception{
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			tdao.save(expRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ɾ���շѼ�¼
	 * @param expRecord
	 * @throws Exception
	 */
	public void delExpenseRecord(TExpenseRecord expRecord) throws Exception{
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			tdao.delte(expRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * �����շѼ�¼
	 * @param expRecord
	 * @throws Exception
	 */
	public void updateExpenseRecord(TExpenseRecord expRecord) throws Exception{
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			tdao.update(expRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * ����id��ѯ�շѼ�¼
	 * @param id
	 * @throws Exception
	 */
	public TExpenseRecord getExpenseRecordById(int expRecordId) throws Exception{
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		TExpenseRecord expRecord = null;
		try{
			expRecord = (TExpenseRecord)tdao.findById(TExpenseRecord.class, expRecordId);
		}catch (Exception e){
			throw e;
		}
		return expRecord;
	}
	
	/**
	 * ���ݿ��Ų�ѯ�շѼ�¼
	 * @param cardId
	 * @throws Exception
	 */
	public List<Object> getExpRecordByCardId(String cardId, Date start, Date end) throws Exception{
		List<Object> expRecord = null;
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			expRecord = tdao.getExpRecordByParas(new Object[]{cardId, start, end}, TExpenseRecordDao.CARDID);
		}catch(Exception er){
			throw er;
		}
		return expRecord;
	}
	
	/**
	 * ���ݳ��ƺ����ѯ�շѼ�¼
	 * @param cardId
	 * @throws Exception
	 */
	public List<Object> getExpRecordByCarnumber(String carnumber, Date start, Date end) throws Exception{
		List<Object> expRecord = null;
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			expRecord = tdao.getExpRecordByParas(new Object[]{"%"+carnumber+"%",start, end}, TExpenseRecordDao.CARNUMBER);
		}catch(Exception er){
			throw er;
		}
		return expRecord;
	}
	
	/**
	 * ���ݳ���������ѯ�շѼ�¼
	 * @param cardId
	 * @throws Exception
	 */
	public List<Object> getExpRecordByOwnername(String ownername,Date start, Date end) throws Exception{
		List<Object> expRecord = null;
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			expRecord = tdao.getExpRecordByParas(new Object[]{"%"+ownername+"%",start ,end}, TExpenseRecordDao.OWNERNAME);
		}catch(Exception er){
			throw er;
		}
		return expRecord;
	}
	
	/**
	 * ��ȡ�����շѼ�¼
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getAllExpRecords(Date start, Date end) throws Exception{
		List<Object> expRecord = null;
		TExpenseRecordDao tdao = new TExpenseRecordDao();
		try{
			expRecord = tdao.getExpRecordByParas(new Object[]{start, end}, TExpenseRecordDao.ALL);
		}catch(Exception er){
			throw er;
		}
		return expRecord;
	}
}

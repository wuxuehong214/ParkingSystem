package com.rcp.wxh.service.impl;

import java.util.Date;
import java.util.List;

import com.rcp.wxh.dao.impl.TCarRecordDao;
import com.rcp.wxh.dao.impl.TCardDao;
import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;

public class CarRecordService extends ExceptionEvent{
	
	private final String module = "�����������";
	/**
	 * ����������¼
	 * @param carRecord
	 * @throws Exception 
	 */
	public void addCarRecord(TCarRecord carRecord) throws Exception{
		TCarRecordDao tdao = new TCarRecordDao();
		try{
			tdao.save(carRecord);
		}catch (Exception e) {
			fireExceptionRecord("����ͣ����¼�쳣!", module);
			throw e;
		}
	}
	/**
	 * ɾ��������¼
	 * @param carRecord
	 * @throws Exception 
	 */
	public void delCarRecord(TCarRecord carRecord) throws Exception{
		TCarRecordDao tdao = new TCarRecordDao();
		try{
			tdao.delte(carRecord);
		}catch (Exception e) {
			fireExceptionRecord("ɾ��ͣ����¼�쳣!", module);
			throw e;
		}
	}
	/**
	 * ���³�����¼
	 * @param carRecord
	 * @throws Exception 
	 */
	public void updateCarRecord(TCarRecord carRecord) throws Exception{
		TCarRecordDao tdao = new TCarRecordDao();
		try{
			tdao.update(carRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ����ID��ѯ������¼
	 * @param carRecordId
	 * @throws Exception 
	 */
	public TCarRecord getCarRecordById(int carRecordId) throws Exception{
		TCarRecord carRecord = null;
		TCarRecordDao tdao = new TCarRecordDao();
		try{
			carRecord = (TCarRecord) tdao.findById(TCarRecord.class, carRecordId);
		}catch (Exception e) {
			throw e;
		}
		return carRecord;
	}
	
	/**
	 * ��ѯʱ��������г��������¼
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getAllCarRecords(Date start, Date end) throws Exception{
		List<Object> carRecords = null;
		TCarRecordDao tdao = new TCarRecordDao();
		try {
			carRecords = tdao.getCarRecordsByParas(new Object[]{start,end}, TCarRecordDao.ALL);
		} catch (Exception e) {
			throw e;
		}
		return carRecords;
	}
	
	/**
	 * ���ݿ���  ��ѯʱ��������г��������¼
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getCarRecordsByCardID(Date start, Date end,String cardID) throws Exception{
		List<Object> carRecords = null;
		TCarRecordDao tdao = new TCarRecordDao();
		try {
			carRecords = tdao.getCarRecordsByParas(new Object[]{start,end,cardID}, TCarRecordDao.CARDID);
		} catch (Exception e) {
			throw e;
		}
		return carRecords;
	}
	
	/**
	 * ���ݳ��ƺ���  ��ѯʱ��������г��������¼
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getCarRecordsByCarnumber(Date start, Date end,String carnumber) throws Exception{
		List<Object> carRecords = null;
		TCarRecordDao tdao = new TCarRecordDao();
		try {
			carRecords = tdao.getCarRecordsByParas(new Object[]{"%"+carnumber+"%",start,end}, TCarRecordDao.CARNUMBER);
		} catch (Exception e) {
			throw e;
		}
		return carRecords;
	}
	
	/**
	 * ���ݳ�������  ��ѯʱ��������г��������¼
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getCarRecordsByOwnername(Date start, Date end,String ownername) throws Exception{
		List<Object> carRecords = null;
		TCarRecordDao tdao = new TCarRecordDao();
		try {
			carRecords = tdao.getCarRecordsByParas(new Object[]{"%"+ownername+"%",start,end}, TCarRecordDao.OWNERNAME);
		} catch (Exception e) {
			throw e;
		}
		return carRecords;
	}
	
}


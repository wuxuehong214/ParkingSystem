package com.rcp.wxh.service.impl;

import com.rcp.wxh.dao.impl.TMemberDao;
import com.rcp.wxh.dao.impl.TSysRecordDao;
import com.rcp.wxh.pojo.TMember;
import com.rcp.wxh.pojo.TSysRecord;

public class SysRecordService {
	/**
	 * ����ϵͳ������¼
	 * @param sysRecord
	 * @throws Exception 
	 */
	public void addSysRecord(TSysRecord sysRecord) throws Exception{
		TSysRecordDao tdao = new TSysRecordDao();
		try{
			tdao.save(sysRecord);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ɾ��ϵͳ������¼
	 * @param sysRecord
	 * @throws Exception
	 */
	public void delSysRecord(TSysRecord sysRecord) throws Exception{
		TSysRecordDao tdao = new TSysRecordDao();
		try{
			tdao.delte(sysRecord);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * ����ϵͳ������¼
	 * @param sysRecord
	 * @throws Exception
	 */
	public void updateSysRecord(TSysRecord sysRecord) throws Exception{
		TSysRecordDao tdao = new TSysRecordDao();
		try{
			tdao.update(sysRecord);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * ����id��ѯϵͳ������¼
	 * @param id
	 * @throws Exception
	 */
	public TSysRecord getSysRecordById(int sysRecordId) throws Exception{
		TSysRecordDao tdao = new TSysRecordDao();
		TSysRecord sysRecord = null;
		try{
			sysRecord = (TSysRecord)tdao.findById(TSysRecord.class, sysRecordId);
		}catch (Exception e){
			throw e;
		}
		return sysRecord;
	}
	
}

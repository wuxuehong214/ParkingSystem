package com.rcp.wxh.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.rcp.wxh.dao.impl.TChargeTypeDao;
import com.rcp.wxh.pojo.TChargeType;

/**
 * �շѷ�ʽҵ���߼�����
 * @author wuxuehong  2011-11-9
 *
 */
public class ChargeTypeService extends ExceptionEvent{

	private final String module = "�շѷ�ʽ����";
	/**
	 * �����շѷ�ʽ
	 * @param chargeType
	 * @throws Exception 
	 */
	public void addChargeType(TChargeType chargeType) throws Exception{
		TChargeTypeDao tdao = new TChargeTypeDao();
		try{
			tdao.save(chargeType);
		}catch (Exception e) {
			fireExceptionRecord("�����շѷ�ʽ�쳣!", module);
			throw e;
		}
	}
	/**
	 * ɾ���շѷ�ʽ
	 * @param chargeType
	 * @throws Exception 
	 */
	public void delChargeType(TChargeType chargeType) throws Exception{
		TChargeTypeDao tdao = new TChargeTypeDao();
		try{
			tdao.delte(chargeType);
		}catch (Exception e) {
			fireExceptionRecord("ɾ���շѷ�ʽ�쳣!", module);
			throw e;
		}
	}
	/**
	 * �����շѷ�ʽ
	 * @param chargeType
	 * @throws Exception 
	 */
	public void updateChargeType(TChargeType chargeType) throws Exception{
		TChargeTypeDao tdao = new TChargeTypeDao();
		try{
			tdao.update(chargeType);
		}catch (Exception e) {
			fireExceptionRecord("�����շѷ�ʽ�쳣!", module);
			throw e;
		}
	}
	
	/**
	 * �����շ����ͱ�Ų�ѯ�շ�����
	 * @param chargeTypeId
	 * @throws Exception 
	 */
	public TChargeType getChargeTypeById(int chargeTypeId) throws Exception{
		TChargeTypeDao tdao = new TChargeTypeDao();
		TChargeType chargeType = null;
		try{
			chargeType = (TChargeType) tdao.findById(TChargeType.class, chargeTypeId);
		}catch (Exception e) {
			throw e;
		}
		return chargeType;
	}
	
	/**
	 * ��ȡ���е��շѷ�ʽ
	 * @return
	 * @throws Exception
	 */
	public List<Object> getAllChargeTypes() throws Exception{
		TChargeTypeDao tdao = new TChargeTypeDao();
		List<Object> chargeTypes;
		try{
			chargeTypes = tdao.findAll(TChargeType.class);
		}catch (Exception e) {
			fireExceptionRecord("��ѯ�����շѷ�ʽ�쳣!", module);
			throw e;
		}
		return chargeTypes;
	}
	
	/**
	 * �����շѷ�ʽ���Ʋ�ѯ�շѷ�ʽ
	 * @param name
	 * @throws Exception
	 */
	public List<Object> getChargeTypeByName(String name) throws Exception{
		List<Object> chargeType = null;
		TChargeTypeDao tdao = new TChargeTypeDao();
		try{
			chargeType =  tdao.getChargeTypeByName(name);
		}catch(Exception er){
			throw er;
		}
		return chargeType;
	}
	
	
	
	/**
	 * test!!!!!!!!!!!!
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String args[]) throws UnsupportedEncodingException{
		ChargeTypeService cts = new ChargeTypeService();
		TChargeType tct = new TChargeType();
		tct.setName(new String("���".getBytes(), "GB2312"));
		tct.setType(5);
		try {
			cts.addChargeType(tct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

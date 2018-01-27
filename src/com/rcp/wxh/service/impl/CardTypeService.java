package com.rcp.wxh.service.impl;

import java.util.List;

import com.rcp.wxh.dao.impl.TCardDao;
import com.rcp.wxh.dao.impl.TCardTypeDao;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;

public class CardTypeService extends ExceptionEvent{
	
	private final String module = "��Ƭ���͹���ģ��";
	/**
	 * ����������
	 * @param cardType
	 * @throws Exception 
	 */
	public void addCardType(TCardType cardType) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		try{
			tdao.save(cardType);
		}catch (Exception e) {
			fireExceptionRecord("������Ƭ������Ϣ�쳣!", module);
			throw e;
		}
	}
	/**
	 * ɾ��������
	 * @param cardType
	 * @throws Exception
	 */
	public void delCardType(TCardType cardType) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		try{
			tdao.delte(cardType);
		}catch (Exception e){
			fireExceptionRecord("ɾ����Ƭ������Ϣ�쳣!", module);
			throw e;
		}
	}
	/**
	 * ���¿�����
	 * @param cardType
	 * @throws Exception
	 */
	public void updateCardType(TCardType cardType) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		try{
			tdao.update(cardType);
		}catch (Exception e){
			fireExceptionRecord("���¿�Ƭ������Ϣ�쳣!", module);
			throw e;
		}
	}
	
	/**
	 * ���ݿ�����ID��ѯ������
	 * @param cardTypeId
	 * @throws Exception
	 */
	public TCardType getCardTypeById(int cardTypeId) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		TCardType cardType = null;
		try{
			cardType = (TCardType) tdao.findById(TCardType.class, cardTypeId);
		}catch (Exception e){
			throw e;
		}
		return cardType;
	}
	
	/**
	 * ���ݿ�Ƭ����ѯ������
	 * @param cardTypeId
	 * @throws Exception
	 */
	public List<Object> getCardTypeByName(String name) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		List<Object> cardType = null;
		try{
			cardType = tdao.getCardTypeByName(name);
		}catch (Exception e){
			throw e;
		}
		return cardType;
	}
	
	/**
	 * ���ݿ�Ƭ����ѯ������
	 * @param cardTypeId
	 * @throws Exception
	 */
	public List<Object> getCardTypeByChargeTypeId(int chargeTypeId) throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		List<Object> cardType = null;
		try{
			cardType = tdao.getCardTypeByChargeTypeId(chargeTypeId);
		}catch (Exception e){
			throw e;
		}
		return cardType;
	}
	
	/**
	 * ��ȡ���п�Ƭ����
	 * @return
	 * @throws Exception
	 */
	public List<Object> getAllCardTypes() throws Exception{
		TCardTypeDao tdao = new TCardTypeDao();
		List<Object> cardTypes = null;
		try{
			cardTypes = tdao.findAll(TCardType.class);
		}catch (Exception e){
			fireExceptionRecord("��ȡ���п�Ƭ������Ϣ�쳣!", module);
			throw e;
		}
		return cardTypes;
	}
	
}

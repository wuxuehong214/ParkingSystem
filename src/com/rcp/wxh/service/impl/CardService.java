package com.rcp.wxh.service.impl;

import java.util.Date;
import java.util.List;

import com.rcp.wxh.dao.impl.TCardDao;
import com.rcp.wxh.dao.impl.TMemberDao;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.pojo.TMember;

/**
 * �й�card �����ҵ���߼�����
 * @author JH
 * 2011 11 3
 */
public class CardService extends ExceptionEvent{
	
	private final String module = "��Ƭ����ģ��";
	/**
	 * ��ȡ���п�Ƭ��Ϣ
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getAllCards() throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try{
			cards = tdao.findAll(TCard.class);
		}catch(Exception e){
			fireExceptionRecord("��ȡ���п�Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	/**
	 * ������
	 * @param card
	 * @throws Exception 
	 */
	public void addCard(TCard card) throws Exception{
		TCardDao tdao = new TCardDao();
		try{
			tdao.save(card);
		}catch (Exception e) {
			fireExceptionRecord("��ע�ῨƬ��Ϣ�쳣!����:"+card.getCardid(), module);
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * ɾ����
	 * @param card
	 * @throws Exception
	 */
	public void delCard(Object card) throws Exception{
		TCardDao tdao = new TCardDao();
		try{
			tdao.delte(card);
		}catch (Exception e){
			fireExceptionRecord("ɾ����Ƭ��Ϣ�쳣!����:"+((TCard)card).getCardid(), module);
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * ���¿�
	 * @param card
	 * @throws Exception
	 */
	public void updateCard(TCard card) throws Exception{
		TCardDao tdao = new TCardDao();
		try{
			tdao.update(card);
		}catch (Exception e){
			fireExceptionRecord("���¿�Ƭ��Ϣ�쳣!����:"+card.getCardid(), module);
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * ����ID��ѯ��
	 * @param card
	 * @throws Exception
	 */
	public TCard getCardById(String cardid) throws Exception{
		TCard card = null;
		TCardDao tdao = new TCardDao();
		try{
			card = (TCard)tdao.findById(TCard.class, cardid);
		}catch (Exception e){
			fireExceptionRecord("��ѯ��Ƭ��Ϣ�쳣!����:"+cardid, module);
			e.printStackTrace();
			throw e;
		}
		return card;
		
	}
	
	/**
	 * ��������ID��ѯ��
	 * @param card
	 * @throws Exception
	 */
	public List<Object> getCardByCardTypeId(int typeid) throws Exception{
		List<Object> card = null;
		TCardDao tdao = new TCardDao();
		try{
			card = tdao.getCardByPara(new Object[]{typeid}, TCardDao.CARDTYPEID);
		}catch (Exception e){
			fireExceptionRecord("���ݿ����Ͳ�ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return card;
	}
	/**
	 * ����״̬��ѯ��
	 * @param card
	 * @throws Exception
	 */
	public List<Object> getCardByCardStatus(int status) throws Exception{
		List<Object> card = null;
		TCardDao tdao = new TCardDao();
		try{
			card = tdao.getCardByPara(new Object[]{status}, TCardDao.CARDSTATUS);
		}catch (Exception e){
			throw e;
		}
		return card;
	}
	
	/**
	 * ���ݳ��ƺ����ѯ
	 * @param carnumber
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<Object> getCardByCarnumber(String carnumber) throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try {
			cards = tdao.getCardByPara(new Object[]{"%"+carnumber+"%"}, TCardDao.CARNUMBER);
		} catch (Exception e) {
			fireExceptionRecord("���ݳ��ƺ����ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	/**
	 * ���ݳ��ƺ����Լ���Ƭ���Ͳ�ѯ
	 * @param carnumber
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<Object> getCardByCarnumberAndType(String carnumber, int type) throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try {
			cards = tdao.getCardByPara(new Object[]{"%"+carnumber+"%", type}, TCardDao.CARNUMBGER_AND_TYPE);
		} catch (Exception e) {
			fireExceptionRecord("���ݳ��ƺ���Ϳ�Ƭ���Ͳ�ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	
	/**
	 * ���� ��Ƭ�� ����������ѯ
	 * @param carid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Object> getCardByCardidAndType(String carid, int type) throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try {
			cards = tdao.getCardByPara(new Object[]{carid, type}, TCardDao.CARDID_AND_TYPE);
		} catch (Exception e) {
			fireExceptionRecord("���ݿ��źͿ�Ƭ���Ͳ�ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	
	/**
	 * ���ݳ���������ѯ����Ϣ
	 * @param ownerName
	 * @return
	 * @throws Exception
	 */
	public List<Object> getCardByOwnerName(String ownerName)throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try {
			cards = tdao.getCardByPara(new Object[]{ownerName}, TCardDao.OWNERNAME);
		} catch (Exception e) {
			fireExceptionRecord("���ݳ���������ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	
	/** 
	 * ���ݳ�������  �Լ���Ƭ���Ͳ�ѯ����Ϣ
	 * @param ownerName
	 * @return
	 * @throws Exception
	 */
	public List<Object> getCardByOwnerNameAndType(String ownerName, int type)throws Exception{
		List<Object> cards = null;
		TCardDao tdao = new TCardDao();
		try {
			cards = tdao.getCardByPara(new Object[]{ownerName, type}, TCardDao.OWNERNAME_AND_TYPE);
		} catch (Exception e) {
			fireExceptionRecord("���ݳ��������Ϳ�Ƭ���Ͳ�ѯ��Ƭ��Ϣ�쳣!", module);
			e.printStackTrace();
			throw e;
		}
		return cards;
	}
	
}

package com.rcp.wxh.service.impl;

import java.util.List;

import com.rcp.wxh.dao.impl.TCarEnterDao;
import com.rcp.wxh.pojo.TCarEnter;

/**
 * �ڳ�����  ҵ���߼������
 * @author wuxuehong  2011-11-25
 *
 */
public class CarEnterService extends ExceptionEvent{
	
	private final String module = "�볡������¼����";
	/**
	 * �����볡 �����ڳ�������¼
	 * @throws Exception 
	 */
	public void addCarEnter(TCarEnter tce) throws Exception{
		TCarEnterDao tedao = new TCarEnterDao();
		try{
			tedao.saveOrUpdate(tce);
		}catch(Exception e){
			fireExceptionRecord("�볡������¼��Ϣ�����쳣", module);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ɾ�������ڳ���¼
	 * @param tce
	 * @throws Exception
	 */
	public void delCarEnter(TCarEnter tce)throws Exception{
		TCarEnterDao tedao = new TCarEnterDao();
		try{
			tedao.delte(tce);
		}catch(Exception e){
			fireExceptionRecord("�볡������¼��Ϣɾ���쳣", module);
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ��ȡ�ڳ���������
	 * @return
	 * @throws Exception
	 */
	public int getCarEnterNum() throws Exception{
		int total = 0;
		TCarEnterDao tedao = new TCarEnterDao();
		try {
			total = tedao.getTotalNum();
		} catch (Exception e) {
			fireExceptionRecord("��ȡ�ڳ���������Ϣ�쳣", module);
			throw e;
		}
		return total;
	}
	
	/**
	 * ���ݿ��� ��ȡ�����볡��¼
	 * @param cardid
	 * @return
	 * @throws Exception
	 */
	public TCarEnter getCarEnterByCardid(String cardid) throws Exception{
		TCarEnter tce = null;
		TCarEnterDao tedao = new TCarEnterDao();
		try {
			tce = tedao.getCarEnterByCardid(cardid);
		} catch (Exception e) {
			fireExceptionRecord("��ȡ�볡��Ϣ�쳣", module);
			throw e;
		}
		return tce;
	}
	/**
	 * ��ȡ��ǰͣ�����ڳ�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<Object> getCarEnters()throws Exception{
		List<Object> list = null;
		TCarEnterDao tedao = new TCarEnterDao();
		try{
			list = tedao.findAll(TCarEnter.class);
		} catch (Exception e) {
			fireExceptionRecord("��ȡ�볡��Ϣ�쳣", module);
			throw e;
		}
		return list;
	}

}

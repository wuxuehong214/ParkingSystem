package com.rcp.wxh.dao.inter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	
	/**
	 * ��ȡ�Ự
	 * @return
	 */
	public Session getSession();  //��ȡ�Ự
	/**
	 * �رջỰ
	 */
	public void closeSession(Session session);
	
	/**
	 * ��������ʵ��
	 */
	public void saveOrUpdate(Object obj)throws Exception;
	/**
	 * ��������
	 * @param obj
	 */
	public void save(Object obj); 
	/**
	 * ɾ������
	 * @param obj
	 */
	public void delte(Object obj);
	/**
	 * ���¶���
	 * @param obj
	 */
	public void update(Object obj);
	/**
	 * ����������ȡ����
	 * @param entity name
	 * @param id
	 * @return
	 */
	public Object findById(Class ObjName, Serializable id);
	/**
	 * ���ݲ����б��ȡ����
	 * @param sql
	 * @param paramValues
	 * @return
	 */
	public Object findByParas(String sql, Object[] paramValues);
	/**
	 * ���ݲ����б��ȡ���󼯺�
	 * @param ObjName entity name
	 * @param paramValues
	 * @return
	 */
	public List<Object> findByProperties(String sql, Object[] paramValues);
	/**
	 * ��ȡ���ж��󼯺�
	 * @return
	 */
	public List<Object> findAll(Class ObjName);
	
	/**
	 * ��ҳ��ѯ��ȡ���϶���
	 * @param ObjName   entity name
	 * @param start    ��ʼҳ
	 * @param offset   ÿҳ��ʾ��Ŀ
	 * @param paramValues  �����б�
	 * @return
	 */
	public List<Object> findByPages(String sql,int start, int offset,Object[] paramValues);
	
}
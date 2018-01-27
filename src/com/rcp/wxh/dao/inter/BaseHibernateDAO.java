package com.rcp.wxh.dao.inter;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.rcp.wxh.dao.impl.TCardDao;
import com.rcp.wxh.sql.hibernate.HibernateSessionFactory;
import com.rcp.wxh.sql.hibernate.MySessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(BaseHibernateDAO.class);
	
	public Session getSession() {
		return MySessionFactory.getSessionFactory().openSession();
	}
	
	public void closeSession(Session session){
		if(session != null)
			session.close();
	}

	@Override
	public void delte(Object obj) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			session.delete(obj); //ִ������
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
		}catch(RuntimeException re){
			log.error("Delete object failed.");
			throw re;
		}
	}

	@Override
	public List<Object> findAll(Class ObjName) {
		// TODO Auto-generated method stub
		List<Object> list;
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			list = session.createQuery("from "+ObjName.getName()).list();
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
			return list;
		}catch(RuntimeException re){
			log.error("get object list failed.");
			throw re;
		}
	}

	@Override
	public Object findById(Class ObjName, Serializable id) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			Object obj = session.get(ObjName, id);
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
			return obj;
		}catch(RuntimeException re){
			log.error("find object failed.");
			throw re;
		}
	}

	@Override
	public List<Object> findByPages(String sql, int start, int offset,
			Object[] paramValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findByProperties(String sql,
			Object[] paramValues) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			Query query = session.createQuery(sql);
			for(int i=0;i<paramValues.length;i++){
				query.setParameter(i, paramValues[i]);
			}
			List<Object> list = query.list();
//			System.out.println(query.getQueryString());
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
			return list;
		}catch(RuntimeException re){
			log.error("find object list failed.");
			throw re;
		}
	}

	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			session.save(obj); //ִ������
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
		}catch(RuntimeException re){
			log.error("Save object failed.");
			throw re;
		}
	}

	@Override
	public void saveOrUpdate(Object obj) throws Exception {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			session.saveOrUpdate(obj); //ִ������
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
		}catch(RuntimeException re){
			log.error("SaveOrUpdate object failed.");
			throw re;
		}
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			session.update(obj); //ִ������
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
		}catch(RuntimeException re){
			log.error("Update object failed.");
			throw re;
		}
	}

	@Override
	public Object findByParas(String sql, Object[] paramValues) {
		// TODO Auto-generated method stub
		try{
			Session session = getSession(); //�����Ự
			org.hibernate.Transaction ta = session.beginTransaction(); //��������
			Query query = session.createQuery(sql);
			for(int i=0;i<paramValues.length;i++){
				query.setParameter(i, paramValues[i]);
			}
			Object obj = query.uniqueResult();
			ta.commit();  //�ύ����
			closeSession(session);  //�رջỰ
			return obj;
		}catch(RuntimeException re){
			log.error("Update object failed.");
			throw re;
		}
	}
	
	public int getCount(String sql) throws Exception{
		int count = 0;
		try{
			Session session = getSession(); //�����Ự
			java.sql.Connection con = session.connection();
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
			con.close();
			closeSession(session);
			return count;
		}catch(Exception re){
			log.error("Update object failed.");
			throw re;
		}
	}
	
}
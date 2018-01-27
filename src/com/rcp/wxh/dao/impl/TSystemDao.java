package com.rcp.wxh.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rcp.wxh.dao.inter.BaseHibernateDAO;
import com.rcp.wxh.pojo.TSystem;

/**
 * ϵͳ����ģ��  dao��
 * @author Administrator
 *
 */
public class TSystemDao extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(TSystemDao.class);
	
	/**
	 * ��ȡϵͳ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public TSystem getSystemInfo() throws Exception{
		try{
			List<Object> list = findAll(TSystem.class);
			if(list.size()>0)
				return (TSystem)list.get(0);
			else
				return null;
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * ����ϵͳ��Ϣ
	 * @param system
	 * @throws Exception
	 */
	public void updateSystem(TSystem system) throws Exception{
		try {
			saveOrUpdate(system);
		} catch (Exception e) {
			throw e;
		}
	}

}

package com.rcp.wxh.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rcp.wxh.dao.inter.BaseHibernateDAO;
import com.rcp.wxh.pojo.TEmp;

/**
 * Ա����Ϣ�������ݲ�
 * @author JH   
 * 2011 11 3
 */
public class TEmpDao extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(TEmpDao.class);
	
	/**
	 * �����û����������ѯԱ��
	 * @param username,password
	 * @throws Exception
	 */
	public TEmp getEmpByNamePwd(String username, String password) throws Exception{
		TEmp emp = null;
		String sql = "from TEmp where operatorid=? and password=?";
		Object[] paras={username,password};
		try{
			emp = (TEmp) findByParas(sql, paras);
		}catch(Exception er){
			throw er;
		}
		return emp;
	}
	
	/**
	 * ����ID��ѯԱ��
	 * @param operatorid
	 * @throws Exception
	 */
	public TEmp getEmpById(String operatorid) throws Exception{
		TEmp emp = null;
		String sql = "from TEmp where operatorid=?";
		Object[] paras={operatorid};
		try{
			emp = (TEmp) findByParas(sql, paras);
		}catch(Exception er){
			throw er;
		}
		return emp;
	}
	
	/**
	 * ����������ѯԱ��
	 * @param operatorid
	 * @throws Exception
	 */
	public List<Object> getEmpByName(String operatorname) throws Exception{
		List<Object> emp = null;
		String sql = "from TEmp where operatorname like ?";
		//��ֹ�û�ƴsql���
		operatorname.replaceAll("%", ""); 
		operatorname.replaceAll("_", "");
		Object[] paras={"%"+operatorname+"%"};
		try{
			emp =  findByProperties(sql, paras);
		}catch(Exception er){
			throw er;
		}
		return emp;
	}
	
	/**
	 * ��ѯ����Ա��
	 * @param priority
	 * @throws Exception
	 */
	public List<Object> getAllEmpByPriority(int priority) throws Exception{
		List<Object> emp = null;
		String sql = "from TEmp where priority=?";
		Object[] paras={priority};
		try{
			emp =   findByProperties(sql, paras);
		}catch(Exception er){
			throw er;
		}
		return emp;
	}
	
	public List<Object> getAllEmpByFindExpress(String find_what ,String find_express) throws Exception{
		List<Object> emp = null;
		String sql = null;
		if (find_what == "priority")
		{
			int p = Integer.parseInt(find_express);
			sql = "from TEmp where priority=?";
			Object[] paras={p};
			try{
				emp =   findByProperties(sql, paras);
			}catch(Exception er){
				throw er;
			}
		}else{
			if(find_what == "operatorid")
			sql = "from TEmp where operatorid=?";
			else if(find_what == "operatorname" )
				sql = "from TEmp where operatorname=?";
			else if(find_what == "identification")
				sql = "from TEmp where identification=?";
			Object[] paras={find_express};
			try{
				emp =   findByProperties(sql, paras);
			}catch(Exception er){
				throw er;
			}
		}
		return emp;
	}
}

package com.rcp.wxh.service.impl;


import java.util.List;

import com.rcp.wxh.dao.impl.TEmpDao;
import com.rcp.wxh.dao.impl.TMemberDao;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.pojo.TMember;
/**
 * �й�emp �����ҵ���߼�����
 * @author JH
 * 2011 11 3
 */
public class EmpService extends ExceptionEvent{
	
	private final String module = "Ա������";

	/**
	 * ����Ա��
	 * @param tEmp
	 * @throws Exception
	 */
	public void addEmp(TEmp tEmp) throws Exception{
		TEmpDao tdao = new TEmpDao();
		try{
			tdao.save(tEmp);
		}catch (Exception e) {
			fireExceptionRecord("����Ա����Ϣ�쳣!", module);
			throw e;
		}
	}
	/**
	 * ɾ��Ա��
	 * @param tEmp
	 * @throws Exception
	 */
	public void delEmp(TEmp tEmp) throws Exception{
		TEmpDao tdao = new TEmpDao();
		try{
			tdao.delte(tEmp);
		}catch (Exception e) {
			fireExceptionRecord("ɾ��Ա����Ϣ�쳣!", module);
			throw e;
		}
	}
	/**
	 * �����˻�
	 * @param tEmp
	 * @throws Exception
	 */
	public void updateEmp(TEmp tEmp) throws Exception{
		TEmpDao tdao = new TEmpDao();
		try{
			tdao.update(tEmp);
		}catch (Exception e) {
			fireExceptionRecord("����Ա����Ϣ�쳣!", module);
			throw e;
		}
	}
	/**
	 * �����û����������ѯԱ��
	 * @param username,password
	 * @throws Exception
	 */
	public TEmp getEmpByNamePwd(String username, String password) throws Exception{
		TEmp emp = null;
		TEmpDao tdao = new TEmpDao();
		try{
			emp = tdao.getEmpByNamePwd(username, password);
		}catch(Exception er){
			fireExceptionRecord("Ա����¼�쳣!", module);
			throw er;
		}
		return emp;
	}
	/**
	 * ��ѯ����Ա��   ����Ա������
	 * @param 
	 * @throws Exception
	 */
	public List<Object> getAllEmp() throws Exception{
		List<Object> allEmp = null;
		TEmpDao tdao = new TEmpDao();
		try{
			allEmp =  tdao.findAll(TEmp.class); 
		}catch (Exception e){
			fireExceptionRecord("��ѯ����Ա����Ϣ�쳣!", module);
			throw e;
		}
		return allEmp;
	}
	/**
	 * ��ѯ���в���Ա  ���ز���Ա����
	 * @param 
	 * @throws Exception
	 */
	public List<Object> getAllOpt() throws Exception{
		List<Object> allOpt = null;
		TEmpDao tdao = new TEmpDao();
		try{
			allOpt =  tdao.getAllEmpByPriority(1); 
		}catch (Exception e){
			throw e;
		}
		return allOpt;
	}
	/**
	 * ��ѯ���й���Ա   ���ع���Ա����
	 * @param 
	 * @throws Exception
	 */
	public List<Object> getAllAdmin() throws Exception{
		List<Object> allOpt = null;
		TEmpDao tdao = new TEmpDao();
		try{
			allOpt =  tdao.getAllEmpByPriority(0); 
		}catch (Exception e){
			throw e;
		}
		return allOpt;
	}
	/**
	 * ����Ա��ID��ѯ  ����ĳ��Ա��  null��ʾ�޴�ID
	 * @param 
	 * @throws Exception
	 */
	public TEmp getEmpById(String operatorid) throws Exception{
		TEmp emp = null;
		TEmpDao tdao = new TEmpDao();
		try{
			emp = tdao.getEmpById(operatorid);
		}catch (Exception e){
			throw e;
		}
		return emp;
	}
	
	/**
	 * ����Ա��������ѯ  ����ͬ��Ա������
	 * @param 
	 * @throws Exception
	 */
	public List<Object> getEmpByName(String operatorname) throws Exception{
		List<Object> emp = null;
		TEmpDao tdao = new TEmpDao();
		try{
			emp = tdao.getEmpByName(operatorname);
		}catch (Exception e){
			throw e;
		}
		return emp;
	}
	
	public List<Object> getEmpByFindExpress(String find_what ,String find_express) throws Exception{
		List<Object> emp = null;
		TEmpDao tdao = new TEmpDao();
		try{
			emp = tdao.getAllEmpByFindExpress(find_what ,find_express) ;
		}catch (Exception e){
			throw e;
		}
		return emp;
	}
}

package com.rcp.wxh.service.impl;

import java.util.List;

import com.rcp.wxh.dao.impl.TExceptionRecordDAO;
import com.rcp.wxh.dao.impl.TMemberDao;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.pojo.TMember;

/**
 * �й�Member �����ҵ���߼�����
 * @author JH
 * 2011 11 3
 */
public class MemberService {
	
	/**
	 * ������Ա
	 * @param member
	 * @throws Exception 
	 */
	public void addMember(TMember member) throws Exception{
		TMemberDao tdao = new TMemberDao();
		try{
			tdao.save(member);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ɾ����Ա
	 * @param member
	 * @throws Exception
	 */
	public void delMember(TMember member) throws Exception{
		TMemberDao tdao = new TMemberDao();
		try{
			tdao.delte(member);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * ���»�Ա
	 * @param member
	 * @throws Exception
	 */
	public void updateMember(TMember member) throws Exception{
		TMemberDao tdao = new TMemberDao();
		try{
			tdao.update(member);
		}catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * ���ݻ�ԱID��ѯ��Ա
	 * @param memberid
	 * @throws Exception
	 */
	public TMember getMemberById(Integer memberid) throws Exception{
		TMemberDao tdao = new TMemberDao();
		TMember tMember = null;
		try{
			tMember = (TMember)tdao.findById(TMember.class, memberid);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	
	/**
	 * ���ݿ��Ų�ѯ��Ա
	 * @param tCard
	 * @throws Exception
	 */
	public TMember getMemberByCard(TCard tCard) throws Exception{
		TMemberDao tdao = new TMemberDao();
		TMember tMember = null;
		try{
			tMember = tdao.getMemberByCard(tCard);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	
	
	/**
	 * ����������ѯ��Ա  
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByName(String name) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(name, TMemberDao.NAME);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	/**
	 * ���ݳ��ƺŲ�ѯ��Ա  
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByCarNumber(String carnumber) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(carnumber, TMemberDao.CARNUMBER);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	/**
	 * ���ݳ����ͺŲ�ѯ��Ա  
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByCarType(String cartype) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(cartype, TMemberDao.CARTYPE);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	/**
	 * �������֤�Ų�ѯ��Ա
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByIdentify(String identification) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(identification, TMemberDao.IDENTIFICATION);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	/**
	 * �����ֻ��Ų�ѯ��Ա  
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByPhoneNumber(String phoneNumber) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(phoneNumber, TMemberDao.PHONENUMBER);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	/**
	 * ���ݹ����ص��ѯ��Ա  
	 * @param tCard
	 * @throws Exception
	 */
	public List<Object> getMemberByWorkPlace(String workPlace) throws Exception{
		TMemberDao tdao = new TMemberDao();
		List<Object> tMember = null;
		try{
			tMember = tdao.getMemberByPara(workPlace, TMemberDao.WORKPLACE);
		}catch (Exception e){
			throw e;
		}
		return tMember;
	}
	
	
	public static void main(String args[]) throws Exception{
		TMember tm = new TMember();
		tm.setCarnumber("333");
		tm.setIdentification("123");
		tm.setName("Mr J");
		tm.setPhonenumber("12312131");
		TCard tCard = new TCard();
		tCard.setCardid("0");
		tm.setMemberid(1);
		tm.setTCard(tCard);
		MemberService MS = new MemberService();
		
	}
	
}

package com.rcp.wxh.service.impl;

import java.util.List;

import com.rcp.wxh.dao.impl.TMemberDao;
import com.rcp.wxh.dao.impl.TTempMemberDao;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TMember;
import com.rcp.wxh.pojo.TTempMember;

/**
 * �й�TempMember �����ҵ���߼�����
 * @author JH
 * 2011 11 3
 */
public class TempMemberService {
	/**
	 * ������ʱ��Ա
	 * @param member
	 * @throws Exception 
	 */
	public void addTempMember(TTempMember member) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		try{
			tdao.save(member);
		}catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ɾ����ʱ��Ա
	 * @param member
	 * @throws Exception
	 */
	public void delTempMember(TTempMember member) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		try{
			tdao.delte(member);
		}catch (Exception e){
			throw e;
		}
	}
	/**
	 * ������ʱ��Ա
	 * @param member
	 * @throws Exception
	 */
	public void updateTempMember(TTempMember member) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		try{
			tdao.update(member);
		}catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * ���ݻ�ԱID��ѯ��ʱ��Ա
	 * @param memberid
	 * @throws Exception
	 */
	public TTempMember getTempMemberById(Integer memberid) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		TTempMember tTempMember = null;
		try{
			tTempMember = (TTempMember)tdao.findById(TTempMember.class, memberid);
		}catch (Exception e){
			throw e;
		}
		return tTempMember;
	}
	
	/**
	 * ���ݿ��Ų�ѯ��ʱ��Ա
	 * @param tCard
	 * @throws Exception
	 */
	public TTempMember getMemberByCard(TCard tCard) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		TTempMember tTempMember = null;
		try{
			tTempMember = tdao.getTempMemberByCard(tCard);
		}catch (Exception e){
			throw e;
		}
		return tTempMember;
	}
	
	/**
	 * ���ݳ��ƺŲ�ѯ��ʱ��Ա
	 * @param carNumber
	 * @throws Exception
	 */
	public List<Object> getMemberByCarNumber(String carNumber) throws Exception{
		TTempMemberDao tdao = new TTempMemberDao();
		List<Object> tTempMember = null;
		try{
			tTempMember = tdao.getMemberByCarNumber(carNumber);
		}catch (Exception e){
			throw e;
		}
		return tTempMember;
	}
	
}

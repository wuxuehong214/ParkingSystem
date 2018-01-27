package com.rcp.wxh.dao.impl;

import java.util.List;

import com.rcp.wxh.dao.inter.BaseHibernateDAO;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TTempMember;
/**
 * 临时会员信息操作数据层
 * @author JH   
 * 2011 11 3
 */
public class TTempMemberDao extends BaseHibernateDAO{
	/**
	 * 根据卡查临时会员
	 * @param tCard
	 * @return
	 * @throws Exception
	 */
	public TTempMember getTempMemberByCard(TCard tCard) throws Exception{
		TTempMember tm = null;
		String sql = "from TTempMember where TCard=?";
		Object[] paras={tCard};
		try{
			tm = (TTempMember) findByParas(sql, paras);
		}catch (Exception e){
			throw e;
		}
		return tm;
		
	}
	/**
	 * 根据车牌号查询临时会员
	 * @param carNumber
	 * @throws Exception
	 */
	public List<Object> getMemberByCarNumber(String carNumber) throws Exception{
		List<Object> tm = null;
		String sql = "from TTempMember where carnumber=?";
		Object[] paras={carNumber};
		try{
			tm =  findByProperties(sql, paras);
		}catch (Exception e){
			throw e;
		}
		return tm;
	}
}

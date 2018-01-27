package com.rcp.wxh.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;

import com.rcp.wxh.dao.inter.BaseHibernateDAO;
import com.rcp.wxh.pojo.TEmp;

import static org.hibernate.criterion.Example.create;

/**
 * A data access object (DAO) providing persistence and search support for TCard
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rcp.wxh.dao.TCard
 * @author MyEclipse Persistence Tools
 */

public class TCardDao extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TCardDao.class);
	// property constants
	public static final int CARDTYPEID = 1;  //���ݿ�Ƭ���Ͳ� ѯ ��С�ࣩ
	public static final int CARDSTATUS = 2;  //���ݿ�Ƭ״̬��ѯ
	public static final int CARDID_AND_TYPE = 4;   //���ݿ�Ƭ��� �Լ� ���Ͳ�ѯ
	public static final int CARNUMBGER_AND_TYPE = 5; //���ݳ��ƺ��� �Լ���Ƭ���Ͳ�ѯ    ֧��ģ����ѯ
    public static final int CARNUMBER = 6; //���ݳ��ƺŲ�ѯ   ֧��ģ����ѯ
    public static final int OWNERNAME_AND_TYPE = 7;    //���ݳ������������Ͳ�ѯ  ֧��ģ����ѯ
    public static final int OWNERNAME = 8;   //���ݳ���������ѯ  ֧��ģ����ѯ
	
	/**
	 * ���ݲ���(����ID   ״̬   ����)��ѯ  ���ؿ����� 
	 * @param para,op
	 * @throws Exception
	 */
	public List<Object> getCardByPara(Object[] para, int op) throws Exception{
		List<Object> card = null;
		String sql = null;
		switch (op){
		case CARDTYPEID:
			sql = "from TCard where cardtypeid=?";
			break;
		case CARDSTATUS:
			sql = "from TCard where cardstatus=?";
			break;
		case CARDID_AND_TYPE:
			sql = "from TCard where cardid=? and cardtypeid=?";
			break;
		case CARNUMBGER_AND_TYPE:
			sql = "from TCard where cardid in (select TCard.cardid from TMember where carnumber like ?) and cardtypeid=?";
			break;
		case CARNUMBER:
			sql = "from TCard where cardid in (select TCard.cardid from TMember where carnumber like ?)";
			break;
		case OWNERNAME:
			sql = "from TCard where cardid in (select TCard.cardid from TMember where name like ?)";
			break;
		case OWNERNAME_AND_TYPE:
			sql = "from TCard where cardid in (select TCard.cardid from TMember where name like ?) and cardtypeid=?";
			break;
		}
		try{
			card = findByProperties(sql, para);
		}catch(Exception er){
			throw er;
		}
		return card;
	}
	
}
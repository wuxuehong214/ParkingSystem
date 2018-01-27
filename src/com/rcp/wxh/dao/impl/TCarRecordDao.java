package com.rcp.wxh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.rcp.wxh.dao.inter.BaseHibernateDAO;

/**
 * ͣ����¼ dao��
 * @author wuxuehong  2011-11-16
 *
 */
public class TCarRecordDao  extends BaseHibernateDAO {
	
	private static final Log log = LogFactory.getLog(TExceptionRecordDAO.class);
	
	public static final int  ALL = 1; //��ѯ����
	public static final int  CARDID = 2; //���ݿ��Ų�ѯ
	public static final int CARNUMBER = 3; //���ݳ��ƺ����ѯ
	public static final int OWNERNAME = 4; //���ݳ���������ѯ

	//��ʱ����ڰ�����ͳ��
	public Object[][] getCarRecodebyTimearea_day(String timefrom,String timeto,int stoptype)throws Exception{
		Object[][] chart = null;
		String typestring;
		try{
			Session session = getSession(); //�����Ự
			Connection con = session.connection();	
			if(stoptype == 0)//0Ϊ����
				typestring = "EXITTIME";
			else //1Ϊ�볡
				typestring = "ENTERTIME";
			PreparedStatement ps = con.prepareStatement(
					"select count(ID) as c,DATE_FORMAT("+typestring+",'%Y-%m-%d') as d ," +
					typestring+" from T_CAR_RECORD where DATE_FORMAT("+ typestring+",'%Y-%m-%d') between ? and ? group by d order by "+typestring);
			//ps.setInt(1,stoptype);
			ps.setString(1,timefrom);//����ֹʱ�����SQL���
			ps.setString(2,timeto);
			//System.out.println(ps.toString());
			ResultSet pss = ps.executeQuery();
			if(pss.next()){		//��������Ϊ��	
				pss.last();
				chart = new Object[pss.getRow()][2];
				pss.beforeFirst();
				int i =0 ;
				while (pss.next()){
					chart[i][0] =  pss.getInt("c");
					chart[i][1] = pss.getDate(typestring);//��ʱ������ά����ĵڶ���
					i++;
				}
			}
			closeSession(session);  //�رջỰ
			ps.close();
			con.close();
			return chart;
		}catch(Exception re){
			log.error("Delete object failed.");
			throw re;
		}
	}
	
		//��ʱ����ڰ�����ͳ��
		public Object[][] getCarRecodebyTimearea_month(String timefrom,String timeto,int stoptype)throws Exception{
			Object[][] chart = null;
			String typestring_month;
			try{
				Session session = getSession(); //�����Ự
				Connection con = session.connection();	
				//System.out.println("dao���������ʱ"+timefrom+"ȥʱ��"+timeto);
				//PreparedStatement ps = con.prepareStatement(
				//		"select count(ID) as c,DATE_FORMAT(STOPTIME,'%Y-%m') as m ,STOPTIME " +
				//		"from T_CAR_RECORD where stoptype = ? and " +
				//		"DATE_FORMAT(STOPTIME,'%Y-%m') between ? and ? group by m");
				if(stoptype == 0)//0Ϊ����
					typestring_month = "EXITTIME";
				else //1Ϊ�볡
					typestring_month = "ENTERTIME";
				String query_str_a = "select count(ID) as c,DATE_FORMAT("+typestring_month+",'%Y-%m') as m , "+typestring_month +
				" from T_CAR_RECORD where DATE_FORMAT("+typestring_month+",'%Y-%m') between ? and ? group by m";
				//System.out.println(query_str_a);
				PreparedStatement ps = con.prepareStatement(query_str_a);
				//ps.setInt(1,stoptype);
				ps.setString(1,timefrom);//����timefrom,timeto��������Ӧ���ǡ�2010-05����2010-11��֮���ʽ��
				ps.setString(2,timeto);//��ʽΪ��2010-5��MYsql��������
				//System.out.println(ps.toString());
				ResultSet pss = ps.executeQuery();
				if(pss.next()){		//��������Ϊ��	
					pss.last();
					chart = new Object[pss.getRow()][2];
					//System.out.println("�������٣�"+pss.getRow());
					pss.beforeFirst();
					int i =0 ;
					while (pss.next()){
						chart[i][0] =  pss.getInt("c");
						chart[i][1] = pss.getDate(typestring_month);//��ʱ������ά����ĵڶ���
						i++;
						//System.out.println(pss.getInt("c"));
					}
				}

				closeSession(session);  //�رջỰ
				ps.close();
				con.close();
				return chart;
			}catch(Exception re){
				log.error("Delete object failed.");
				throw re;
			}
		}
	/**
	 * ���ݲ�ͬ��������ѯָ���ѯ���������¼��Ϣ
	 * @param paras
	 * @param op
	 * @return
	 * @throws Exception 
	 */
	public List<Object>  getCarRecordsByParas(Object[] paras, int op) throws Exception{
		List<Object> carRecords = null;
		String sql = null;
		switch(op){
			case ALL:
				sql = "from TCarRecord where entertime between ? and ? order by entertime desc";
				break;
			case CARDID:
				sql = "from TCarRecord where entertime between ? and ? and TCard.cardid = ? order by entertime desc";
				break;
			case CARNUMBER:
				sql = "from TCarRecord where TCard.cardid in (select TCard.cardid from TMember where carnumber like ?) and entertime between ? and ? order by entertime desc";
				break;
			case OWNERNAME:
				sql = "from TCarRecord where TCard.cardid in (select TCard.cardid from TMember where name like ?) and entertime between ? and ? order by entertime desc";
				break;
		}
		try{
			carRecords = findByProperties(sql, paras);
		}catch(Exception e){
			throw e;
		}
		return carRecords;
	}
	
}

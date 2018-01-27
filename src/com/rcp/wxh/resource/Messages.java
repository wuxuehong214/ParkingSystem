package com.rcp.wxh.resource;

import org.eclipse.osgi.util.NLS;
/**
 * �ַ���������Դ
 * @author Administrator
 *
 */
public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "messages";
	
	static{
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * for system
	 */
	public static String SOFT_NAME;
	
	/**
	 * for menu
	 */
	public static String PASSWORD_MANAGE_LABEL; //�����������
	public static String LOGINOUT_MANAGE_LABEL; //ע����½
	
	public static String LOGIN_LABEL; //��¼��
	public static String ENTER_MANAGE_LABEL; //�����볡����
	public static String LEAVE_MANAGE_LABEL; //������������
	public static String ENTER_EXIT_MANAGE_LABEL; //�������볡����
	
	public static String STATISTIC_MANAGE_LABEL; //����ͳ��
	public static String PERSONAL_MANAGE_LABEL; //������Ϣ����
	public static String EMP_MANAGE_LABEL; //Ա����Ϣ����
	
	public static String CARD_MANAGE_LABEL; //��Ƭ����
	public static String SYSTEM_MANAGE_LABEL; //ϵͳ����
	public static String EXCEPTION_MANAGE_LABEL; //�쳣��¼����
	public static String EXPENSE_MANAGE_LABEL; //���ü�¼����
	public static String CARRECORD_MANAGE_LABEL; //���������¼����
	public static String CARENTER_MANAGE_LABEL; //��ǰͣ����Ϣ��ѯ
	/**
	 * for dialog
	 */
	public static String LOGIN_TITLE;
	public static String LOGIN_TIPS;
	public static String LOGIN_USERNAME;
	public static String LOGIN_PASSWORD;
	public static String LOGIN_CONFIRM;
	public static String LOGIN_CANCEL;
	
	
	public static void main(String args[]){
		char c = 0 ;
		System.out.println(c);
	}

}

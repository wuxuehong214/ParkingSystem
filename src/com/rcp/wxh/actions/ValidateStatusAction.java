package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;

import com.rcp.wxh.pojo.TEmp;

/**
 * ������֤��  
 * ����action��Ҫ�̳иó���������֤���
 * @author Administrator
 *
 */
public abstract class ValidateStatusAction extends Action {
	
	public static TEmp user;
	
	public ValidateStatusAction(){
		if(user!=null && user.getPriority()==TEmp.ADMINSTRATOR)  //�������Ա��������Ӧ���Ȩ��
			this.setEnabled(true);
		else
			this.setEnabled(false);
	}
	
}

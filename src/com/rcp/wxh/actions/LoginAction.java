package com.rcp.wxh.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.dialogs.LoginDialog;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.service.impl.EmpService;
import com.rcp.wxh.utils.AutomLoginUtil;
import com.rcp.wxh.utils.MessageDialogUtil;
/**
 * ϵͳ��¼��Ӧ
 * @author Administrator
 *
 */
public class LoginAction extends ValidateStatusAction implements IWorkbenchAction{
	
	public static final String ID = LoginAction.class.getName();
	
	private IWorkbenchWindow window;
	
	public LoginAction(IWorkbenchWindow window,String label){
		this.window = window;
		this.setText(label);
		this.setId(ID); //һ��Ҫ��ס
	}

	public void run(){
//		MessageDialog.openInformation(window.getShell(), "����", "ֻ�й���Ա�ܵ���������ľ��?");
		int r = MessageDialogUtil.showConfirmMessage(window.getShell(), "ȷ��Ҫע����½ô?");
		if(r == SWT.OK){
			AutomLoginUtil alu = new AutomLoginUtil();
			TEmp emp = (TEmp) alu.readObject();
			if(emp != null){
				alu.setAutLogin(false, emp);
			}
			System.exit(0);
		}
	}
	
	public void dispose() {
		System.out.println(ID+"\tdisposed!!!");
	}

}

package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.dialogs.LoginDialog;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.utils.AutomLoginUtil;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.sun.media.Log;

import parkingsystem.Activator;

/**
 * ע����½��Ӧ
 * @author Administrator
 *
 */
public class LoginOutAction extends Action implements IWorkbenchAction {

	public static final String ID = LoginOutAction.class.getName();
	
	private IWorkbenchWindow window;
	
	public LoginOutAction(IWorkbenchWindow window, String label,String image){
		this.window = window;
		setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		setId(ID);
	}
	
	public void run(){
		int r = MessageDialogUtil.showConfirmMessage(window.getShell(), "ȷ��Ҫע����½ô?");
		if(r == SWT.OK){
			AutomLoginUtil alu = new AutomLoginUtil();
			TEmp emp = (TEmp) alu.readObject();
			if(emp != null){
				alu.setAutLogin(false, emp);
				alu.writeObject(emp);
			}
			System.exit(0);
		}
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

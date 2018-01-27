package com.rcp.wxh.actions;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import parkingsystem.Activator;
import org.eclipse.jface.action.Action;

import com.rcp.wxh.dialogs.PasswordDialog;
import com.rcp.wxh.editors.CardEditor;
import com.rcp.wxh.editors.CardEditorInput;
import com.rcp.wxh.utils.MessageDialogUtil;

/**
 * 密码修改响应
 * @author wuxuehong  2011-11-17
 *
 */
public class PasswordResetAction extends Action implements IWorkbenchAction{
	
	public static final String ID = PasswordResetAction.class.getName();

	private IWorkbenchWindow window;

	public PasswordResetAction(IWorkbenchWindow window, String label,String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // 一定要记住
	}

	public void run() {
		PasswordDialog pd = new PasswordDialog(window.getShell(), SWT.NONE);
		pd.open();
	}

	public void dispose() {
		System.out.println(ID + "\tdisposed!!!");
	}


}

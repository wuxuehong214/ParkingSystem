package com.rcp.wxh.actions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.dialogs.TExceptionDialog;
import com.rcp.wxh.utils.MessageDialogUtil;

import parkingsystem.Activator;

/**
 * �쳣��Ϣ��¼��ѯ
 * @author wuxuehong  2011-11-14
 *
 */
public class ExceptionManageAction extends ValidateStatusAction implements
		IWorkbenchAction {
	
	public static String ID = ExceptionManageAction.class.getName();  //unique ID

	private IWorkbenchWindow window;

	public ExceptionManageAction(IWorkbenchWindow window, String label,String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // һ��Ҫ��ס
	}
	
	/**
	 * ��Ӧִ��
	 */
	public void run(){
//		MessageDialogUtil.showInfoMessage(window.getShell(), "�쳣��Ϣ");
		TExceptionDialog ted = new TExceptionDialog(window.getShell(), SWT.NONE);
		ted.open();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

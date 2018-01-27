package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.dialogs.CarEnterDialog;

import parkingsystem.Activator;

/**
 * ��ǰ�ڳ�������Ϣ��ѯ��Ӧ
 * @author wuxuehong
 *
 * 2012-1-29
 */
public class CarEnterManageAction extends Action implements IWorkbenchAction {
	//ΨһID
	public static final String ID = CarEnterManageAction.class.getName();

	private IWorkbenchWindow window;
	
	public CarEnterManageAction(IWorkbenchWindow window, String label,String image){
		this.window = window;
		setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		setId(ID);
	}
	
	public void run(){
		CarEnterDialog ced = new CarEnterDialog(window.getShell(), SWT.NONE);
		ced.open();
	}
	
	public void dispose() {

	}

}

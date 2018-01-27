package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import parkingsystem.Activator;

public class PersonalManageAction extends Action implements IWorkbenchAction {

public static final String ID = PersonalManageAction.class.getName();
	
	private final IWorkbenchWindow window;
	
	public PersonalManageAction(IWorkbenchWindow window, String label, String imagePath){
		this.window = window;
		setText(label);
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor(imagePath));
	}
	
	/**
	 * ����Ӧ��ͼ  
	 */
	public void run(){
		MessageDialog.openInformation(window.getShell(), "��ʾ", "�����볡���");
	}
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

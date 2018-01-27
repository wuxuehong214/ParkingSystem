package com.rcp.wxh.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.communicate.CommunicateJob;
import com.rcp.wxh.dialogs.SystemDialog;

import parkingsystem.Activator;

public class SystemManageAction extends ValidateStatusAction implements
		IWorkbenchAction {

	public static final String ID = SystemManageAction.class.getName();

	private IWorkbenchWindow window;

	public SystemManageAction(IWorkbenchWindow window, String label,String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // 一定要记住
	}

	public void run() {
//		CommunicateJob job = new CommunicateJob("与终端通信中...");
//		job.schedule();  
		SystemDialog sd = new SystemDialog(window.getShell(), SWT.NONE);
		sd.open();
	}

	public void dispose() {
		System.out.println(ID + "\tdisposed!!!");
	}

}

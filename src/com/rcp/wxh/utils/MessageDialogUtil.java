package com.rcp.wxh.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * ��Ϣ������Ϣ ����
 * @author wuxuehong  2011-11-13
 *
 */
public class MessageDialogUtil {

	/**
	 * ������Ϣ�Ի���
	 * @param shell
	 * @param message
	 */
	public static void showWarningMessage(Shell shell, String message){
		MessageBox mb = new MessageBox(shell, SWT.OK|SWT.ICON_WARNING);
		mb.setMessage(message);
		mb.setText("����");
		mb.open();
	}
	
	/**
	 * ��Ϣ��ʾ�Ի���
	 * @param shell
	 * @param message
	 */
	public static void showInfoMessage(Shell shell, String message){
		MessageBox mb = new MessageBox(shell, SWT.OK|SWT.ICON_INFORMATION);
		mb.setMessage(message);
		mb.setText("��Ϣ");
		mb.open();
	}
	
	/**
	 * ������Ϣ��ʾ��
	 * @param shell
	 * @param message
	 */
	public static void showErrorMessage(Shell shell, String message){
		MessageBox mb = new MessageBox(shell, SWT.OK|SWT.ICON_ERROR);
		mb.setMessage(message);
		mb.setText("����");
		mb.open();
	}
	
	/**
	 * ��Ϣȷ�϶Ի���
	 * @param shell
	 * @param message
	 */
	public static int showConfirmMessage(Shell shell, String message){
		MessageBox mb = new MessageBox(shell, SWT.OK|SWT.CANCEL|SWT.ICON_QUESTION);
		mb.setMessage(message);
		mb.setText("��ʾ");
		return mb.open();
	}
	
}

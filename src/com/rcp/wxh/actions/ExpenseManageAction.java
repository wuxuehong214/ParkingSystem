package com.rcp.wxh.actions;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.dialogs.TExpenseDialog;
import com.rcp.wxh.editors.CarRecordEditor;
import com.rcp.wxh.editors.CarRecordInput;
import com.rcp.wxh.editors.ExpenseEditor;
import com.rcp.wxh.editors.ExpenseEditorInput;
import com.rcp.wxh.utils.MessageDialogUtil;

import parkingsystem.Activator;

/**
 * �ɷѼ�¼��ѯ
 * @author wuxuehong  2011-11-14
 *
 */
public class ExpenseManageAction extends ValidateStatusAction implements
		IWorkbenchAction {
	
	public static String ID = ExpenseManageAction.class.getName(); //unique ID

	private IWorkbenchWindow window;

	public ExpenseManageAction(IWorkbenchWindow window, String label,String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // һ��Ҫ��ס
	}
	
	public void run(){
//		TExpenseDialog ted = new TExpenseDialog(window.getShell(), SWT.NONE);
//		ted.open();
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(ExpenseEditorInput.eei); // ��ȡ�༭��
		if (editor != null) { // ����༭���Ѿ���
			workbenchPage.bringToTop(editor); // ����ʾ�ñ༭��
		} else { // ���³�ʼ���ñ༭��
			try {
				editor = workbenchPage.openEditor(ExpenseEditorInput.eei, ExpenseEditor.ID);
			} catch (PartInitException ei) {
				ei.printStackTrace();
			}
		}
	}
	
	public void dispose() {

	}

}

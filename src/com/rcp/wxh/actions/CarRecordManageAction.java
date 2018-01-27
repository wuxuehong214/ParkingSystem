package com.rcp.wxh.actions;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import parkingsystem.Activator;

import com.rcp.wxh.editors.CarRecordEditor;
import com.rcp.wxh.editors.CarRecordInput;
import com.rcp.wxh.editors.CardEditor;
import com.rcp.wxh.editors.CardEditorInput;

/**
 * ���������¼���� ��Ӧ
 * @author wuxuehong 2011-11-16
 * 
 */
public class CarRecordManageAction extends ValidateStatusAction implements
		IWorkbenchAction {

	public static final String ID = CarRecordManageAction.class.getName();

	private IWorkbenchWindow window;

	public CarRecordManageAction(IWorkbenchWindow window, String label,
			String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // һ��Ҫ��ס
	}

	public void run() {
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(CarRecordInput.cri); // ��ȡ�༭��
		if (editor != null) { // ����༭���Ѿ���
			workbenchPage.bringToTop(editor); // ����ʾ�ñ༭��
		} else { // ���³�ʼ���ñ༭��
			try {
				editor = workbenchPage.openEditor(CarRecordInput.cri, CarRecordEditor.ID);
			} catch (PartInitException ei) {
				ei.printStackTrace();
			}
		}
	}

	public void dispose() {
		System.out.println(ID + "\tdisposed!!!");
	}

}
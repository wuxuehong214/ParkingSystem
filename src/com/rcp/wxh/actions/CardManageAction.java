package com.rcp.wxh.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.editors.CardEditor;
import com.rcp.wxh.editors.CardEditorInput;
import com.rcp.wxh.editors.StatisticEditor;
import com.rcp.wxh.editors.StatisticEditorInput;

import parkingsystem.Activator;

/**
 * ��Ƭ���� ��Ӧ  
 * @author wuxuehong  2011-11-16
 *
 */
public class CardManageAction extends ValidateStatusAction implements
		IWorkbenchAction {

	public static final String ID = CardManageAction.class.getName();

	private IWorkbenchWindow window;

	public CardManageAction(IWorkbenchWindow window, String label,String image) {
		this.window = window;
		this.setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		this.setId(ID); // һ��Ҫ��ס
	}

	public void run() {
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(CardEditorInput.eei);  //��ȡ�༭��
		if(editor != null){      //����༭���Ѿ���
			workbenchPage.bringToTop(editor);   //����ʾ�ñ༭��
		}else{  //���³�ʼ���ñ༭��
			try{
				editor = workbenchPage.openEditor(CardEditorInput.eei, CardEditor.ID);
			}catch(PartInitException ei){
				ei.printStackTrace();
			}
		}
	}

	public void dispose() {
		System.out.println(ID + "\tdisposed!!!");
	}

}

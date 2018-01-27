package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.editors.EnterEditor;
import com.rcp.wxh.editors.EnterEditorInput;
import com.rcp.wxh.utils.MessageDialogUtil;

import parkingsystem.Activator;

/**
 * �������������Ӧ
 * @author wuxuehong  2011-11-23
 *
 */
public class LeaveManageAction extends Action implements IWorkbenchAction {
	
	public static final String ID = LeaveManageAction.class.getName();
	
	private IWorkbenchWindow window;
	
	public LeaveManageAction(IWorkbenchWindow window, String label,String image){
		this.window = window;
		setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		setId(ID);
	}
	
	
	public void run(){
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(EnterEditorInput.eei);  //��ȡ�༭��
		if(editor != null){      //����༭���Ѿ���
			if(EnterEditor.STYLE == EnterEditor.EXIT_ONLY)  //�����ǰ�ı༭���� ��������
				workbenchPage.bringToTop(editor);   //����ʾ�ñ༭��
			else{ 
				workbenchPage.closeEditor(editor, true);  //�رյ�ǰ�༭��  �����´�
				EnterEditor.STYLE = EnterEditor.EXIT_ONLY;
				try {
					editor = workbenchPage.openEditor(EnterEditorInput.eei, EnterEditor.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}else{  //���³�ʼ���ñ༭��
			EnterEditor.STYLE = EnterEditor.EXIT_ONLY;
			try{
				editor = workbenchPage.openEditor(EnterEditorInput.eei, EnterEditor.ID);
			}catch(PartInitException ei){
				ei.printStackTrace();
			}
		}
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

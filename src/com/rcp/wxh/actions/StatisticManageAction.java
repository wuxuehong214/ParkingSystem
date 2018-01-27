package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.editors.StatisticEditor;
import com.rcp.wxh.editors.StatisticEditorInput;

import parkingsystem.Activator;

/**
 * ����ͳ����Ӧ
 * @author wuxuehong  2011-11-2
 *
 */
public class StatisticManageAction extends ValidateStatusAction implements IWorkbenchAction{
	
	public static final String ID = StatisticManageAction.class.getName();
	
	private IWorkbenchWindow window;
	
	public StatisticManageAction(IWorkbenchWindow window, String label,String image){
		this.window = window;
		setText(label);
		setImageDescriptor(Activator.getImageDescriptor(image));
		setId(ID);
	}
	
	
	public void run(){
//		MessageDialog.openInformation(window.getShell(), "��ʾ", "����ͳ��");
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(StatisticEditorInput.sei);  //��ȡ�༭��
		if(editor != null){      //����༭���Ѿ���
			workbenchPage.bringToTop(editor);   //����ʾ�ñ༭��
		}else{  //���³�ʼ���ñ༭��
			try{
				editor = workbenchPage.openEditor(StatisticEditorInput.sei, StatisticEditor.ID);
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

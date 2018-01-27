package com.rcp.wxh.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.rcp.wxh.editors.EmpEditor2;
import com.rcp.wxh.editors.EmpEditorInput;
import com.rcp.wxh.editors.EnterEditor;
import com.rcp.wxh.editors.EnterEditorInput;

import parkingsystem.Activator;


/**
 * �����볡���� ��Ӧ
 * @author Administrator
 *
 */
public class EnterManageAction extends Action implements IWorkbenchAction {

	public static final String ID = EnterManageAction.class.getName();
	
	private final IWorkbenchWindow window;
	
	public EnterManageAction(IWorkbenchWindow window, String label, String imagePath){
		this.window = window;
		setText(label);
		setId(ID);
		setImageDescriptor(Activator.getImageDescriptor(imagePath));
	}
	
	/**
	 * ����Ӧ��ͼ  
	 */
	public void run(){
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(EnterEditorInput.eei);  //��ȡ�༭��
		if(editor != null){      //����༭���Ѿ���
			if(EnterEditor.STYLE == EnterEditor.ENTER_ONLY)  //�����ǰ�ı༭���� �볡����
				workbenchPage.bringToTop(editor);   //����ʾ�ñ༭��
			else{ 
				workbenchPage.closeEditor(editor, true);  //�رյ�ǰ�༭��  �����´�
				EnterEditor.STYLE = EnterEditor.ENTER_ONLY;
				try {
					editor = workbenchPage.openEditor(EnterEditorInput.eei, EnterEditor.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}else{  //���³�ʼ���ñ༭��
			EnterEditor.STYLE = EnterEditor.ENTER_ONLY;
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

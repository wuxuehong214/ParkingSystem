package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class CardEditorInput implements IEditorInput {

	public static final CardEditorInput eei = new CardEditorInput();
	
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "��Ƭ����";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "��Ƭ��Ϣ����";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

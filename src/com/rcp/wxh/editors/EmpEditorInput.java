package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class EmpEditorInput implements IEditorInput {

	public static final EmpEditorInput eei = new EmpEditorInput();
	
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "Ա������";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "Ա����Ϣ����";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

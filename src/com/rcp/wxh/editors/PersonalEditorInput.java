package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class PersonalEditorInput implements IEditorInput {

	public static final PersonalEditorInput pei = new PersonalEditorInput();
	
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "������Ϣ����";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "������Ϣ����";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

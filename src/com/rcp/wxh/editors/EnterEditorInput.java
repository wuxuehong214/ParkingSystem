package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * �����볡��� Input
 * @author wuxuehong  2011-11-17
 *
 */
public class EnterEditorInput implements IEditorInput {

	public static final EnterEditorInput eei = new EnterEditorInput();
	
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "�����볡���";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "�����볡��ع���";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

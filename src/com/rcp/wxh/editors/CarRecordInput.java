package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * ���������¼  input
 * @author wuxuehong  2011-11-16
 *
 */
public class CarRecordInput implements IEditorInput {

	public static final CarRecordInput cri = new CarRecordInput();
	
	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return "���������¼";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "���������¼��Ϣ";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

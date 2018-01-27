package com.rcp.wxh.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * 车辆入场监控 Input
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
		return "车辆入场监控";
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return "车辆入场监控管理";
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}

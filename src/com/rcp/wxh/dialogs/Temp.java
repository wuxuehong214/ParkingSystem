package com.rcp.wxh.dialogs;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import com.swtdesigner.SWTResourceManager;

public class Temp extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Temp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

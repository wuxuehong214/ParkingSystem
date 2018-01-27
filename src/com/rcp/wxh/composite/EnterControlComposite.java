package com.rcp.wxh.composite;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

import com.rcp.wxh.communicate.CommunicateException;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.utils.CardComm;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

import parkingsystem.Activator;
import parkingsystem.ApplicationActionBarAdvisor;
import com.swtdesigner.ResourceManager;

/**
 * �볡����ģ��
 * @author wuxuehong  2011-12-11
 *
 */
public class EnterControlComposite extends Composite {

	private CLabel label;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EnterControlComposite(final Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		
		label = new CLabel(this, SWT.SHADOW_NONE | SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("������κ", 12, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setText("\u5165\u573A\u63D0\u793A...");
		label.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		RowLayout r2 = new RowLayout(SWT.HORIZONTAL);
		r2.spacing = 15;
		r2.justify = true;
		composite.setLayout(r2);
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(-1, 70));
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/up.gif"));
		button.addSelectionListener(new SelectionAdapter() {   //�˹�̧բ
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean a = false;
				try {
					a = CardComm.stickUp(CardComm.ENTERANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
				}
				System.out.println("��ڿ�բ:"+a);
			}
		});
		button.setText("\u4EBA\u5DE5\u62AC\u95F8");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setLayoutData(new RowData(-1, 70));
		button_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/down.gif"));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {          //��ڹ�բ
				boolean a = false;
				try {
					a = CardComm.stickDown(CardComm.ENTERANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
				}
				System.out.println("��ڹ�բ:"+a);
			}
		});
		button_2.setText("\u4EBA\u5DE5\u843D\u95F8");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setLayoutData(new RowData(-1, 70));
		button_1.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/pause.gif"));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {         //���ͣբ
				boolean a = false;
				try {
					a = CardComm.stickStop(CardComm.ENTERANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
				}
				System.out.println("���ͣբ:"+a);
			}
		});
		button_1.setText("\u6682    \u505C");
		
	}
	
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * �����볡��ʾ��Ϣ
	 * @param info
	 */
	Timer time = new Timer();
	public void setStatus(String info){
		label.setText(info);
		time.schedule(new TimerTask() {
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						label.setText("�볡��ʾ...");
					}
				});
			}
		}, 3000);
	}
	
	//״̬�� ��ʾ��Ϣ
	private void showMessage(final String msg){
		Display.getDefault().asyncExec(new Runnable(){
			public void run() {
				ApplicationActionBarAdvisor.getInstance().getStatusLineManager().setMessage(Activator.getImageDescriptor(IimageKeys.STATUS_WARN_TRAY).createImage(), msg);
			}
		});
	}
}

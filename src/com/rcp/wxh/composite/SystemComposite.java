package com.rcp.wxh.composite;

import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.CLabel;

import com.rcp.wxh.pojo.TSystem;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.CarEnterService;
import com.rcp.wxh.service.impl.SystemService;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import parkingsystem.Activator;
import parkingsystem.ApplicationActionBarAdvisor;

/**
 * ͣ����ϵͳ��Ϣչʾģ��
 * ϵͳʱ��
 * ʣ��ͣ��λ��
 * @author wuxuehong  2011-12-9
 *
 */
public class SystemComposite extends Composite {

	CLabel label,label_1;
	/**
	 * ϵͳ��Ϣģ���ʼ��
	 * @param parent
	 * @param style
	 */
	public SystemComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout gl = new GridLayout(1, true); 
		gl.verticalSpacing = 10;
		setLayout(gl);
		gl.marginTop = 10;
		gl.marginBottom = 10;
		gl.marginLeft = 15;
		gl.marginRight = 15;
		
		label = new CLabel(this, SWT.CENTER);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("������κ", 16, SWT.BOLD));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u5F53\u524D\u65F6\u95F4\uFF1A");
		label.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		label_1 = new CLabel(this, SWT.SHADOW_NONE | SWT.CENTER);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setFont(SWTResourceManager.getFont("������κ", 16, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setText("\u5269\u4F59\u8F66\u4F4D\u6570\uFF1A");
		label_1.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new Thread(new TimeThread()).start(); //�����̸߳���ʱ����Ϣ
		new Thread(new CarNumThread()).start(); //�����̸߳���ʣ�೵λ��Ϣ
	}
	
	/**
	 * ʱ�䶨ʱˢ��
	 */
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private void setTime(){
		if(!label.isDisposed())
		label.setText(sdf.format(new Date()));
	}
	
	/**
	 * ����ʣ�೵λ��Ϣ
	 * @param num
	 */
	public void setParkNum(int num){
		if(!label_1.isDisposed())
		label_1.setText("ʣ�೵λ��:"+num);
	}
	
	/**
	 * ��Ӧˢ��ʹ��
	 */
	public void updateParkNum(){
		CarEnterService ces = new CarEnterService();
		SystemService ss = new SystemService();
		int total = 0,usednum = 0;
		try {
			total = ss.getSystemInfo().getTotalnum();
			usednum = ces.getCarEnterNum();
		} catch (Exception e1) {
			showMessage("ʣ�೵λ��Ϣ��ȡ�쳣");
			return;
		}
		if(usednum<total)
		setParkNum(total-usednum);
	}
	
	/**
	 * ����ʱ���߳�
	 * @author wuxuehong  2011-12-9
	 *
	 */
	class TimeThread implements Runnable{
		public void run() {
			while(true){
				   if(SystemComposite.this.isDisposed())break;
				   SystemComposite.this.getDisplay().asyncExec(new Runnable() {
					public void run() {
						setTime();
					}
				   });
				   try {
					   Thread.sleep(1000);
				   } catch (InterruptedException e) {
				   }
			}
		}
	}
	
	/**
	 * ʣ�೵λ�����߳�
	 * @author wuxuehong
	 *
	 * 2012-1-29
	 */
	class CarNumThread implements Runnable{
		CarEnterService ces = new CarEnterService();
		SystemService ss = new SystemService();
		int total = 0;
		int usednum = 0;
		public void run(){
			while(true){
				if(SystemComposite.this.isDisposed())break;
				try {
					total = ss.getSystemInfo().getTotalnum();
					usednum = ces.getCarEnterNum();
				} catch (Exception e1) {
					e1.printStackTrace();
					 SystemComposite.this.getDisplay().asyncExec(new Runnable() {
							public void run() {
								showMessage("ʣ�೵λ��Ϣ��ȡ�쳣");
							}
						   });
				}
				if(SystemComposite.this.isDisposed())break;
				SystemComposite.this.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if(usednum<total)
							setParkNum(total-usednum);
					}
				   });
				try {
					Thread.sleep(5000);
				   } catch (InterruptedException e) {
				   }
			}
		}
	}
	
	//״̬�� ��ʾ��Ϣ
	private void showMessage(final String msg){
		Display.getDefault().asyncExec(new Runnable(){
			public void run() {
				ApplicationActionBarAdvisor.getInstance().getStatusLineManager().setMessage(Activator.getImageDescriptor(IimageKeys.LOCK_POP_MANAGE_TRAY).createImage(), msg);
			}
		});
	}
}

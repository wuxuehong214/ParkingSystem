package com.rcp.wxh.composite;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.rcp.wxh.pojo.TCarRecord;
import com.swtdesigner.SWTResourceManager;

/**
 * ��������������Ϣչʾ���
 * @author wuxuehong  2011-12-11
 *
 */
public class ExpenseComposite extends Composite {

	private CLabel label_10;
	private Text text,text_1,text_2;
	private Timer timer = new Timer();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ExpenseComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout(2, false);
//		gridLayout.marginRight = 15;
//		gridLayout.marginLeft = 15;
//		gridLayout.marginBottom = 15;
//		gridLayout.marginTop = 15;
		this.setLayout(gridLayout);
		
		CLabel label_9 = new CLabel(this, SWT.NONE);
		GridData gd_label_9 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_9.heightHint = 22;
		label_9.setLayoutData(gd_label_9);
		label_9.setText("\u505C\u8F66\u65F6\u95F4\uFF1A");
		
		label_10 = new CLabel(this, SWT.BORDER);
		GridData gd_label_10 = new GridData(GridData.FILL_HORIZONTAL);
		gd_label_10.heightHint = 22;
		label_10.setLayoutData(gd_label_10);
		
//		CLabel label_8 = new CLabel(this, SWT.NONE);
//		GridData gd_label_8 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
//		gd_label_8.heightHint = 22;
//		label_8.setLayoutData(gd_label_8);
//		label_8.setText("\u8F66\u724C\u53F7\u7801\uFF1A");
//		
//		text = new Text(this, SWT.BORDER);
//		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
//		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
//		text.setTextLimit(25);
		
		CLabel label_11 = new CLabel(this, SWT.NONE);
		GridData gd_label_11 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_11.heightHint = 22;
		label_11.setLayoutData(gd_label_11);
		label_11.setText("\u5E94\u6536\u8D39\u7528(\u5143)\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setText("0");
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 260;
		text_1.setLayoutData(gd_text_1);
		text_1.setTextLimit(8);
		
		CLabel label_12 = new CLabel(this, SWT.NONE);
		GridData gd_label_12 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_12.heightHint = 22;
		label_12.setLayoutData(gd_label_12);
		label_12.setText("\u5B9E\u6536\u8D39\u7528(\u5143)\uFF1A");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setText("0");
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_2.setTextLimit(8);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * ��ʾ������Ϣ
	 * @param carRecord
	 */
	public void setExpense(TCarRecord carRecord){
		if(carRecord == null) throw new NullPointerException("������ϢΪ��!");
		int time = carRecord.getStoptime();
		label_10.setText(time/60>0? time/60+"Сʱ"+time%60+"����":time%60+"����");
		text_1.setText(carRecord.getDueexpense()+""); //Ӧ�շ���
		text_2.setText(carRecord.getFactexpense()+""); //���շ���
		timer.schedule(new TimerTask() {
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						label_10.setText("");
					}
				});
			}
		}, 3000);
	}
	
	/**
	 * ��ȡʵ���շ�����Ϣ
	 * @return
	 */
	public String getFactExpense(){
		return text_2.getText().trim();
	}

}

package com.rcp.wxh.dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.service.impl.ChargeTypeService;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.swtdesigner.ResourceManager;
/**
 * �����շѷ�ʽ�Ի���
 * @author wuxuehong  2011-11-9
 *
 */
public class TChargeTypeDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Label label_1;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	private Label label_5,label_6,label_7;
	private Text text_4;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TChargeTypeDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		Display display = getParent().getDisplay();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.BORDER | SWT.CLOSE | SWT.APPLICATION_MODAL);
		shell.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/CHARGE_MANAGE.gif"));
		shell.setSize(353, 348);
		shell.setText("\u65B0\u589E\u6536\u8D39\u65B9\u5F0F");
		shell.setLocation(getParent().getLocation().x+300,getParent().getLocation().y+200);
		shell.setLayout(null);
		shell.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u586B\u5199\u6536\u8D39\u65B9\u5F0F\u4FE1\u606F");
		group.setBounds(10, 10, 311, 261);
		
		Label label = new Label(group, SWT.CENTER);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		label.setBounds(31, 20, 77, 18);
		label.setText("\u6536\u8D39\u540D\u79F0\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(114, 18, 158, 18);
		text.setTextLimit(30);
		
		label_1 = new Label(group, SWT.CENTER);
		label_1.setBounds(31, 71, 77, 18);
		label_1.setText("\u6536\u8D39\u65B9\u5F0F\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		label_1.setAlignment(SWT.CENTER);
		
		final Button button = new Button(group, SWT.RADIO);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //�����ӼƷ�
				if(button.getSelection()){
					text_1.setEnabled(true);
					text_2.setEnabled(true);
					text_3.setEnabled(true);
					text_4.setEnabled(true);
					label_5.setText("��λ:����");
					label_6.setText("��λ:����");
					label_7.setText("��λ:Ԫ");
				}
			}
		});
		button.setBounds(114, 70, 93, 16);
		button.setText("\u6309\u5206\u949F\u8BA1\u8D39");
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //��Сʱ�Ʒ�
				if(button_1.getSelection()){
					text_1.setEnabled(true);
					text_2.setEnabled(true);
					text_3.setEnabled(true);
					text_4.setEnabled(true);
					label_5.setText("��λ:Сʱ");
					label_6.setText("��λ:����");
					label_7.setText("��λ:Ԫ");
				}
			}
		});
		button_1.setBounds(114, 92, 93, 16);
		button_1.setText("\u6309\u5C0F\u65F6\u8BA1\u8D39");
		button_1.setSelection(true);
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.addSelectionListener(new SelectionAdapter() {  //�������Ʒ�
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_2.getSelection()){
					text_1.setEnabled(false);
					text_2.setEnabled(false);
					text_3.setEnabled(false);
					text_4.setEnabled(true);
					label_5.setText("");
					label_6.setText("");
					label_7.setText("");
				}
			}
		});
		button_2.setBounds(114, 114, 93, 16);
		button_2.setText("\u6309\u6B21\u6570\u8BA1\u8D39");
		
		final Button button_3 = new Button(group, SWT.RADIO);
		button_3.addSelectionListener(new SelectionAdapter() { //��ʱ����շ�
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_3.getSelection()){
					text_1.setEnabled(false);
					text_2.setEnabled(false);
					text_3.setEnabled(false);
					text_4.setEnabled(false);
					label_5.setText("");
					label_6.setText("");
					label_7.setText("");
				}
			}
		});
		button_3.setBounds(114, 135, 93, 16);
		button_3.setText("\u6309\u65F6\u95F4\u6BB5\u8BA1\u8D39");
		
		final Button button_4 = new Button(group, SWT.RADIO);
		button_4.addSelectionListener(new SelectionAdapter() {  //�������
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_4.getSelection()){
					text_1.setEnabled(false);
					text_2.setEnabled(false);
					text_3.setEnabled(false);
					text_4.setEnabled(false);
					label_5.setText("");
					label_6.setText("");
					label_7.setText("");
				}
			}
		});
		button_4.setBounds(114, 157, 93, 16);
		button_4.setText("\u6C38\u4E45\u514D\u8D39");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(31, 182, 84, 16);
		label_2.setText("\u8BA1\u8D39\u5355\u4F4D\u65F6\u95F4\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setBounds(124, 179, 77, 18);
		text_1.setTextLimit(4);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(31, 204, 84, 17);
		label_3.setText("\u8BA1\u8D39\u8D85\u65F6\u65F6\u95F4\uFF1A");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_2.setBounds(124, 203, 77, 18);
		text_2.setTextLimit(4);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBounds(54, 226, 54, 20);
		label_4.setText("\u8BA1\u8D39\u5355\u4EF7\uFF1A");
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_3.setBounds(124, 227, 77, 18);
		text_3.setTextLimit(5);
		
		label_5 = new Label(group, SWT.NONE);
		label_5.setBounds(207, 182, 54, 16);
		
		label_6 = new Label(group, SWT.NONE);
		label_6.setBounds(207, 205, 54, 16);
		
		label_7 = new Label(group, SWT.NONE);
		label_7.setBounds(207, 230, 54, 16);
		
		label_5.setText("��λ:Сʱ");
		label_6.setText("��λ:����");
		label_7.setText("��λ:Ԫ");
		
		Label label_8 = new Label(group, SWT.CENTER);
		label_8.setBounds(31, 44, 77, 18);
		label_8.setText("\u8D85\u65F6\u8BA1\u8D39\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		label_8.setAlignment(SWT.CENTER);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_4.setBounds(114, 43, 101, 18);
		text_4.setTextLimit(4);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setBounds(218, 46, 54, 18);
		label_9.setText("\u5206\u949F");
		
		Button button_5 = new Button(shell, SWT.NONE);
		button_5.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/confirm1.ico"));
		button_5.setLocation(177, 285);
		button_5.setSize(72, 22);
		button_5.addSelectionListener(new SelectionAdapter() {   //�����շѷ�ʽ�ύ
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText(); //�շѷ�ʽ����
				if("".equals(name)){
					MessageDialogUtil.showWarningMessage(shell, "�շѷ�ʽ���Ʋ�����Ϊ��!");
					return ;
				}
				TChargeType tct = new TChargeType();
				tct.setName(name);
				tct.setMinutecount(0);
				try{
				if(button.getSelection()){  //����������շ�
					tct.setType(TChargeType.MINUTE);
					tct.setMinute(Integer.parseInt(text_1.getText().trim()));
					tct.setOverminute(Integer.parseInt(text_2.getText().trim()));
					tct.setFee(Float.parseFloat(text_3.getText()));
					tct.setMinutecount(Integer.parseInt(text_4.getText()));
				}else if(button_1.getSelection()){
					tct.setType(TChargeType.HOUR); //��Сʱ�շ�
					tct.setHour(Integer.parseInt(text_1.getText().trim()));
					tct.setOverhour(Integer.parseInt(text_2.getText().trim()));
					tct.setFee(Float.parseFloat(text_3.getText()));
					tct.setMinutecount(Integer.parseInt(text_4.getText()));
				}else if(button_2.getSelection()){  //�������շ�
					tct.setMinutecount(Integer.parseInt(text_4.getText()));
					tct.setType(TChargeType.TIME); //�������Ʒ�
				}else if(button_3.getSelection()){
					tct.setType(TChargeType.PERIOD); //��ʱ����շ�
				}else if(button_4.getSelection()){
					tct.setType(TChargeType.FREE); //���
				}
				}catch (Exception ec){
					MessageDialogUtil.showWarningMessage(shell, "��������ȷ����Ϣ��ʽ!");
					return;
				}
				ChargeTypeService cts = new ChargeTypeService();
				try {
					cts.addChargeType(tct);
				} catch (Exception e1) {
					MessageDialogUtil.showErrorMessage(shell, "�շѷ�ʽ����ʧ��!");
					e1.printStackTrace();
					return;
				}
				result = tct;
				MessageDialogUtil.showInfoMessage(shell, "�շѷ�ʽ�����ɹ�!");
				shell.dispose();
			}
		});
		button_5.setText("\u786E\u5B9A");
		
		Button button_6 = new Button(shell, SWT.NONE);
		button_6.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/cancel.ico"));
		button_6.setLocation(260, 285);
		button_6.setSize(72, 22);
		button_6.addSelectionListener(new SelectionAdapter() {  //�رնԻ���
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_6.setText("\u53D6\u6D88");

	}
}

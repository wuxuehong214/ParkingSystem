package com.rcp.wxh.dialogs;

import java.awt.RenderingHints.Key;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.actions.ValidateStatusAction;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.service.impl.EmpService;
import com.rcp.wxh.utils.AutomLoginUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

import com.swtdesigner.ResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import parkingsystem.Activator;

/**
 * ��¼�Ի���
 * @author wuxuehong  2011-11-16
 *
 */
public class LoginDialog extends Dialog {

	protected Object result = 0;
	protected Shell shell;
	
	private StackLayout stackLayout;  //��Ƭ���ֶ���
	
	private Composite composite;  //��½�������
	private Composite composite_1;  //��¼���
	private Composite composite_2;  //��¼�ȴ� ���
	
	private Text text;   //�û���
	private Text text_1; //����
	private  Label label_3; //��Ϣ��ʾ��ǩ
	
	private Button button; //��ѡ��  ��ס����
	private Button button_1; //��ѡ�� �Զ���¼
	private Button button_4;//ȡ����¼��ť
	
	private TEmp emp = null;;
	
	private boolean isAutomLogin = false;
	
	private boolean isCancle = false; //�Ƿ�ȡ����¼
	
	private AutomLoginUtil alu;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("��¼");
		
		//emp ��Ϊ�� ���ʾ��Ҫ�Ѿ���ס���û�������  
		//priority 0-��ʾ  1-��ʾ��Ҫ�Զ���¼
		alu = new AutomLoginUtil();
		emp = (TEmp) alu.readObject();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		Display display = getParent().getDisplay();
		Rectangle displayBounds = display.getPrimaryMonitor().getBounds();
        Rectangle shellBounds = shell.getBounds();
        int x = displayBounds.x + (displayBounds.width - shellBounds.width)>>1;
        int y = displayBounds.y + (displayBounds.height - shellBounds.height)>>1;
        shell.setLocation(x, y);
		shell.open();
		shell.layout();
		// �Զ���¼����
		if(emp !=null && alu.isAutLogin(emp)){
				loginAction();
//				isAutomLogin = true;
		}
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/alt_window_16.gif"));
		shell.setSize(431, 343);
		shell.setText(getText());
		shell.setLayout(null);
		
		shell.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label lblLogoImage = new Label(shell, SWT.BORDER);
		lblLogoImage.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/LOGO.JPG"));
//		lblLogoImage.setImage(Activator.getImageDescriptor("icons/LOGO.jpg").createImage());
		lblLogoImage.setBounds(10, 10, 405, 101);
//		lblLogoImage.setText("LOGO image");
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 117, 405, 183);
		stackLayout = new StackLayout(); 
		composite.setLayout(stackLayout);
		
		composite_1 = new Composite(composite, SWT.BORDER);
		composite_2 = new Composite(composite, SWT.BORDER);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("������κ", 12, SWT.NORMAL));
		label.setText("\u767B\u5F55\u540D\uFF1A");
		label.setBounds(42, 31, 79, 18);
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(162, 28, 182, 22);
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13 || e.keyCode==16777296){  //����ǻس�
					loginAction();
				}
			}
		});
		if(emp != null && alu.isRePwd(emp))
			text.setText(emp.getOperatorid());  //����Ա��ID
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("������κ", 12, SWT.NORMAL));
		label_1.setText("\u5BC6  \u7801\uFF1A");
		label_1.setBounds(42, 74, 79, 18);
		
		label_3 = new Label(composite_1, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("������κ", 9, SWT.NORMAL));
		label_3.setBounds(40, 151, 152, 22);
		
		text_1 = new Text(composite_1, SWT.BORDER|SWT.PASSWORD);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		if(emp != null && alu.isRePwd(emp))
			text_1.setText(emp.getPassword());
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13 || e.keyCode==16777296){  //����ǻس�
//					MessageDialog.openWarning(shell, "��ʾ", "����������͵����ť��!");
					loginAction();
				}
			}
		});
		text_1.setBounds(162, 70, 182, 22);
		
		button = new Button(composite_1, SWT.CHECK);
		button.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		button.setText("\u8BB0\u4F4F\u5BC6\u7801");
		button.setBounds(42, 114, 93, 16);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //�Զ���¼��ť  ��ѡ��
				if(!button.getSelection()){
					button_1.setSelection(false);  //�����Ҫ�Զ���¼ ����Ҫ��ס����
				}
			}
		});
		if(emp != null && alu.isRePwd(emp)){
			button.setSelection(true);
		}
		
		button_1 = new Button(composite_1, SWT.CHECK);
		button_1.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		button_1.setText("\u81EA\u52A8\u767B\u5F55");
		button_1.setBounds(161, 114, 93, 16);
		if(emp != null && alu.isAutLogin(emp)){
			button_1.setSelection(true);
		}
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //�Զ���¼��ť  ��ѡ��
				if(button_1.getSelection()){
					button.setSelection(true);  //�����Ҫ�Զ���¼ ����Ҫ��ס����
				}
			}
		});
		
		Button button_2 = new Button(composite_1, SWT.RIGHT);
		button_2.setAlignment(SWT.LEFT);
		button_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/login.ico"));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //��¼��ť
				loginAction();
			}
		});
		button_2.setText("\u767B\u5F55");
		button_2.setBounds(198, 151, 72, 22);
		
		Button button_3 = new Button(composite_1, SWT.NONE);
		button_3.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/cancel_login.ico"));
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { //ȡ����ť
				shell.dispose();
			}
		});
		button_3.setText("\u53D6\u6D88");
		button_3.setBounds(302, 151, 72, 22);
		
		button_4 = new Button(composite_2, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //ȡ����¼��ť
				isCancle = true;
				stackLayout.topControl = composite_1;
				composite.layout();
			}
		});
		button_4.setBounds(165, 123, 72, 22);
		button_4.setText("\u53D6\u6D88\u767B\u5F55");
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/logining.gif"));
		label_2.setBounds(133, 77, 136, 22);
		
		
		/**
		 * �Զ���¼����
		 */
		
		stackLayout.topControl = composite_1;
		composite.layout();
	}
	
	/**
	 * ��¼��Ӧ
	 */
	public void loginAction(){
		isCancle = false;
		final String name = text.getText().trim();//�û���
		final String password = text_1.getText().trim();//����
		if(name.length()==0||password.length()==0){
			label_3.setText("�û��������벻��Ϊ��!");
			return;
		}
		stackLayout.topControl = composite_2;
		composite.layout();
		new Thread(new Runnable(){
			public void run() {
				EmpService emps = new EmpService();
				TEmp temp=null;
				try {
					temp = emps.getEmpByNamePwd(name, password);  //���ݿ��쳣 
				} catch (Exception e) {
					shell.getDisplay().asyncExec(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							stackLayout.topControl = composite_1;
							composite.layout();
							label_3.setText("��¼ʧ��!");
						}
					});
					return;
				}
				if(isCancle) return;  //���ȡ����¼���򷵻�
				if(temp==null){ //��֤ʧ��
					//���ص�һ����Ƭ����
					shell.getDisplay().asyncExec(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							stackLayout.topControl = composite_1;
							composite.layout();
							label_3.setText("��¼ʧ��!");
						}
					});
				}else{
					ValidateStatusAction.user = temp;
					result = 1;  //��֤�ɹ�
					
					shell.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if(!isAutomLogin){  //�������Զ���¼     �������Ҫ��������Ϣ���洦��
								if(emp == null) emp = new TEmp();
								emp.setOperatorid(name);
								emp.setPassword(password);
								alu.setRePwd(button.getSelection(), emp);
								alu.setAutLogin(button_1.getSelection(), emp);
								alu.writeObject(emp);
							}
							shell.dispose(); //�رմ���
						}
					});
				}
			}
		}).start();
	}
	
}

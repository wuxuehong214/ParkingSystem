package com.rcp.wxh.composite;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;

import com.rcp.wxh.communicate.CommunicateException;
import com.rcp.wxh.pojo.TCarEnter;
import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.CarEnterService;
import com.rcp.wxh.service.impl.CarRecordService;
import com.rcp.wxh.service.impl.CardService;
import com.rcp.wxh.utils.CardComm;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import parkingsystem.Activator;
import parkingsystem.ApplicationActionBarAdvisor;
import com.swtdesigner.ResourceManager;
import org.eclipse.swt.layout.RowData;

/**
 * ���������������
 * @author wuxuehong  2011-12-11
 *
 */
public class ExitControlComposite extends Composite{
	
	private ExpenseComposite expenseCom;
	private TCarRecord carRecord;
	
	/**
	 * ���õ�ǰ�����������Ķ���
	 * @param carRecord
	 */
	public void setCurInfor(TCarRecord carRecord){
		this.carRecord = carRecord;
	}

	public ExitControlComposite(final Composite parent, int style, ExpenseComposite expenseComposite) {
		super(parent, style);
		this.expenseCom = expenseComposite;
		setLayout(new GridLayout(1, true));
		
		Composite composite = new Composite(this, SWT.NONE);
		RowLayout rl = new RowLayout(SWT.HORIZONTAL);
		rl.marginTop = 5;
		rl.justify = true;
		composite.setLayout(rl);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(-1, 70));
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/up.gif"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {   //���ڿ�բ
				if(carRecord != null) {   //   ͣ������ǰ�� (1)ɾ���볡��¼  (2)�洢ͣ����¼��(3)�۳�ͣ������
					float factExp = 0;
					try{
						factExp = Float.parseFloat(expenseCom.getFactExpense());
					}catch(Exception e2){
						MessageDialogUtil.showWarningMessage(ExitControlComposite.this.getShell(), "��������ȷ��ʵ�ʽ��!");
						return;
					}
					TCarEnter carEnter = new TCarEnter();
					carEnter.setCardid(carRecord.getTCard().getCardid());
					delCarEnter(carEnter);   //ɾ�������볡��Ϣ
					addCarRecord(carRecord);  //�������������¼
					TCard card = carRecord.getTCard();
					int chargeType = card.getTCardType().getTChargeType().getType().intValue(); //�շ�����
					if(chargeType == TChargeType.HOUR || chargeType == TChargeType.MINUTE ){  //����ǰ�ʱ/���շ�
						card.setCardprice(card.getCardprice()-factExp);  //�۳�����
						updateCard(card);
					}
					if(chargeType == TChargeType.TIME){
						card.setCardtimes(card.getCardtimes()-1); //�۳�һ��ͣ������
						updateCard(card);
					}
					
					carRecord = null; //ע��  ���㣡����
				}
				boolean a = false;
				try {
					a = CardComm.stickUp(CardComm.EXITANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
					return;
				}
				System.out.println("���ڿ�բ:"+a);
			}
		});
		button.setText("\u4EBA\u5DE5\u62AC\u95F8");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setLayoutData(new RowData(-1, 70));
		button_1.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/down.gif"));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {        //���ڹ�բ
				boolean a = false;
				try {
					a = CardComm.stickDown(CardComm.EXITANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
					return;
				}
				System.out.println("���ڹ�բ:"+a);
			}
		});
		button_1.setText("\u4EBA\u5DE5\u843D\u95F8");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setLayoutData(new RowData(-1, 70));
		button_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/pause.gif"));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //����ͣբ
				boolean a = false;
				try {
					a = CardComm.stickStop(CardComm.EXITANCE);
				} catch (CommunicateException e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), e1.getMessage());
					showMessage(e1.getMessage());
					return; 
				}
				System.out.println("����ͣբ:"+a);
			}
		});
		button_2.setText("\u6682    \u505C");
	}
	
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	//״̬�� ��ʾ��Ϣ
	private void showMessage(final String msg){
		Display.getDefault().asyncExec(new Runnable(){
			public void run() {
				ApplicationActionBarAdvisor.getInstance().getStatusLineManager().setMessage(Activator.getImageDescriptor(IimageKeys.LOCK_POP_MANAGE_TRAY).createImage(), msg);
			}
		});
	}
	
	/**
	 * �����������볡��¼
	 * @param carRecord
	 */
	public void addCarRecord(TCarRecord carRecord){
		CarRecordService crs = new CarRecordService();
		try {
			crs.addCarRecord(carRecord);
		} catch (Exception e) {
			showMessage("�������������¼�쳣!");
		}
	}
	
	/**
	 * ɾ�������볡��¼
	 * @param carEnter
	 */
	private void delCarEnter(TCarEnter carEnter){
		CarEnterService ces = new CarEnterService();
		TCarEnter tce = null;
		try {
			ces.delCarEnter(carEnter);
		} catch (Exception e) {
			showMessage("�����볡��Ϣɾ���쳣!");
		}
	}
	
	/**
	 * ���¿�Ƭ��Ϣ
	 * @param card
	 */
	private void updateCard(TCard card){
		CardService cs = new CardService();
		try {
			cs.updateCard(card);
		} catch (Exception e) {
			showMessage("���¿�Ƭ��Ϣ�쳣!");
		}
	}
}

package com.rcp.wxh.editors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.TabExpander;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.internal.win32.TBBUTTON;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.actions.ExcelExportAction;
import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.dialogs.PayDialog;
import com.rcp.wxh.dialogs.TCardDialog;
import com.rcp.wxh.dialogs.TCardEditDialog;
import com.rcp.wxh.dialogs.TCardTypeDialog;
import com.rcp.wxh.dialogs.TCardTypeEditDialog;
import com.rcp.wxh.dialogs.TChargeTypeDialog;
import com.rcp.wxh.dialogs.TChargeTypeEditDialog;
import com.rcp.wxh.fortables.CardContentProvider;
import com.rcp.wxh.fortables.CardLabelProvider;
import com.rcp.wxh.fortables.CardSorter;
import com.rcp.wxh.fortables.CardTypeContentProvider;
import com.rcp.wxh.fortables.CardTypeLabelProvider;
import com.rcp.wxh.fortables.ChargeTypeContentProvider;
import com.rcp.wxh.fortables.ChargeTypeLabelProvider;
import com.rcp.wxh.fortables.TableListener;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TCardType;
import com.rcp.wxh.pojo.TChargeType;
import com.rcp.wxh.pojo.TMember;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.CardService;
import com.rcp.wxh.service.impl.CardTypeService;
import com.rcp.wxh.service.impl.ChargeTypeService;
import com.rcp.wxh.utils.CardUtil;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import com.swtdesigner.ResourceManager;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

import parkingsystem.Activator;

/**
 * 
 * ��Ƭ��Ϣ����༭��
 * @author wuxuehong  2011-11-2
 *
 */
public class CardEditor extends EditorPart {
	public CardEditor() {
	}
	
	public static final String ID = CardEditor.class.getName();
	private Table table;
	private Table table_1;
	private Table table_2;
	private TableViewer tableViewer;
	private TableViewer tableViewer_1;
	private TableViewer tableViewer_2;
	private  boolean a = true;
	
	private PageComposite pageComposite; //��ҳ���
	private int sizePage = 25;
	
	/////////////////////��Ƭ�Ҽ��˵�//////////////////
	private Action editAction;  // �༭��Ƭ
	private Action deleteAction; //ɾ����Ƭ
	private Action payAction; //�ɷ�
	private Action lockAction; //�⿨ ����
	private Text text;
	
	private Action editChargeAction; //�༭�շѷ�ʽ��Ϣ
	private Action delChargeAction; //ɾ���շѷ�ʽ��Ϣ
	
	private Action editCardTypeAction; //�༭��Ƭ������Ϣ
	private Action delCardTypeAction;  //ɾ����Ƭ������Ϣ
	
	private List<Object> cardTypes; //���п�Ƭ����
	private TCardType curCardType;  //��ǰ��Ƭ����

	public void doSave(IProgressMonitor monitor) {
		
	}

	public void doSaveAs() {
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * ��ʼ���༭������
	 */
	public void createPartControl(final Composite parent) {
		parent.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		parent.setBackgroundMode(SWT.INHERIT_FORCE);
		 //��ȡ��Ƭ����
		CardTypeService cts = new CardTypeService(); 
		try {
			cardTypes = cts.getAllCardTypes();
		} catch (Exception e) {
			MessageDialogUtil.showWarningMessage(new Shell(), "��ȡ��Ƭ����ʧ��");
			cardTypes = new ArrayList<Object>();
		}
		
		FillLayout fillLayout = (FillLayout) parent.getLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(new FillLayout());
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		
		Group group_1 = new Group(sashForm, SWT.NONE);
		group_1.setText("\u5361\u7247\u4FE1\u606F");
		group_1.setLayout(new GridLayout(1, true));
		
		Composite composite_2 = new Composite(group_1, SWT.NONE);
		composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite_2 = new GridData(GridData.FILL_HORIZONTAL);
		gd_composite_2.verticalAlignment = SWT.TOP;
		composite_2.setLayoutData(gd_composite_2);
		
		final Combo combo_1 = new Combo(composite_2, SWT.NONE);
		combo_1.setItems(new String[] {"\u6240\u6709\u5361\u7247\u7C7B\u578B"});
		combo_1.setText("\u6240\u6709\u5361\u7247\u7C7B\u578B");
		if(cardTypes != null){
			for(int i=0;i<cardTypes.size();i++){
				TCardType tct = (TCardType) cardTypes.get(i);
				combo_1.add(tct.getName());
				combo_1.setData((i+1)+"", tct);
			}
		}
		
		final Combo combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if("ȫ��".equals(combo.getText())){
					text.setText("");
					text.setEnabled(false);
				}else
				if("����".equals(combo.getText().trim())){
					text.setText("�뽫�������Ӧ��");
					text.setEnabled(false);
				}else{
					text.setText("");
					text.setEnabled(true);
				}
			}
		});
		combo.setItems(new String[] {"\u5168\u90E8", "\u5361\u53F7", "\u8F66\u724C\u53F7", "\u8F66\u4E3B\u59D3\u540D"});
		combo.setText("\u5168\u90E8");
		
		Label label = new Label(composite_2, SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("����", 12, SWT.NORMAL));
		label.setLayoutData(new RowData(13, 17));
		label.setText("=");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setLayoutData(new RowData(113, -1));
		text.setEnabled(false);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setLayoutData(new RowData(7, 12));
		
		Button button = new Button(composite_2, SWT.NONE);
		button.setLayoutData(new RowData(79, 20));
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//��ѯ���п�Ƭ��Ϣ
				//����б��е���Ϣ
				tableViewer.setInput(null);
				//��ȡ��ѯ��Ƭ����
				int index = combo_1.getSelectionIndex();
				curCardType = (TCardType) combo_1.getData(index+"");
				
				if("ȫ��".equals(combo.getText())){ //����ǲ�ѯ���п���Ϣ
					CardService cs = new CardService();
					List<Object> cards = null;
					try {
						if(curCardType == null){  //��ѯ���п�Ƭ����
								cards = cs.getAllCards();
						}else{
								cards = cs.getCardByCardTypeId(curCardType.getCardtypeid().intValue()); //���ݿ�Ƭ����ID��ѯ
						}
					} catch (Exception e1) {
						MessageDialogUtil.showWarningMessage(parent.getShell(), "��ȡ��Ƭ��Ϣʧ��!");
						return;
					}
					pageComposite.setData(cards, sizePage);
				}else if("����".equals(combo.getText())){  //����ǲ�ѯ����
					long cardID = CardUtil.getCardNum();
					if(cardID==-1){
						text.setText("�뽫�������Ӧ��");
						MessageDialogUtil.showWarningMessage(parent.getShell(), "�뽫�������Ӧ�������ٲ�ѯ!");
						return;
					}
					text.setText(cardID+"");
					CardService cs = new CardService();
					List<Object> cards = new ArrayList<Object>();
					try {
						if(curCardType == null){   //���п�Ƭ����
							TCard card = null;
								card = cs.getCardById(cardID+"");
							if(card != null)
								cards.add(card);
						}else{
								cards = cs.getCardByCardidAndType(cardID+"", curCardType.getCardtypeid().intValue());
						}
					} catch (Exception e1) {
						MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ƭ��Ϣ��ѯʧ��!");
						return;
					}
					if(cards.size() == 0){
						MessageDialogUtil.showWarningMessage(parent.getShell(), "�ÿ�������!");
						text.setText("�뽫�������Ӧ��");
						return;
					}else{
						pageComposite.setData(cards, sizePage);
					}
				}else if("���ƺ�".equals(combo.getText())){  //����ǲ�ѯ���ƺ�
					 String carnumber = text.getText().trim();
					 if("".equals(carnumber)){
						 MessageDialogUtil.showWarningMessage(parent.getShell(), "�����복�ƺ�!");
						 return;
					 }
					 CardService cs = new CardService();
					 List<Object> cards = new ArrayList<Object>();
					 try {
						 if(curCardType == null){
								cards = cs.getCardByCarnumber(carnumber);
						 }else{
								cards = cs.getCardByCarnumberAndType(carnumber, curCardType.getCardtypeid());
						 }
					  } catch (Exception e1) {
						 MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ƭ��Ϣ��ѯʧ��!");
						 return;
					 }
					 if(cards.size() == 0){
							MessageDialogUtil.showWarningMessage(parent.getShell(), "�ÿ�������!");
							return;
					 }else{
							pageComposite.setData(cards, sizePage);
					 }
				}else if("��������".equals(combo.getText())){ //����ǰ�����������ѯ
					String ownerName = text.getText().trim();
					 if("".equals(ownerName)){
						 MessageDialogUtil.showWarningMessage(parent.getShell(), "�����복������!");
						 return;
					 }
					 CardService cs = new CardService();
					 List<Object> cards = new ArrayList<Object>();
					 try {
						 if(curCardType == null){
								cards = cs.getCardByOwnerName("%"+ownerName+"%");
						 }else{
								cards = cs.getCardByOwnerNameAndType("%"+ownerName+"%", curCardType.getCardtypeid());
						 }
					 } catch (Exception e1) {
						 MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ƭ��Ϣ��ѯʧ��!");
						 return;
					 }
					 if(cards.size() == 0){
							MessageDialogUtil.showWarningMessage(parent.getShell(), "�ÿ�������!");
							return;
					 }else{
							pageComposite.setData(cards, sizePage);
					 }
				}
			}
		});
		button.setText("\u67E5  \u8BE2");
		
		Button button_9 = new Button(composite_2, SWT.NONE);
		button_9.setLayoutData(new RowData(84, 20));
		button_9.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/add.ico"));
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TCardDialog tcd = new TCardDialog(parent.getShell(), SWT.NONE);  //��ע�ῨƬ
				Object result = tcd.open();
				if(result != null){
					TCard card = (TCard)result;
					if(curCardType == null || (curCardType!=null && curCardType.getCardtypeid().intValue() == card.getTCardType().getCardtypeid().intValue())){
						pageComposite.updateAfterDelByAdd(result);
					}
				}
			}
		});
		button_9.setText("\u65B0\u5361\u6CE8\u518C");
		
		Button button_2 = new Button(composite_2, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableViewer.getInput() == null){
					MessageDialogUtil.showWarningMessage(parent.getShell(), "���޿ɵ�������!");
					return;
				}
				String title = combo.getText();
				String filename = combo_1.getText()+"-"+combo.getText()+"-��Ƭ��Ϣ";
				ExcelExportAction eea = new ExcelExportAction(parent.getShell(), filename, filename, pageComposite);
				try {
					eea.run();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showErrorMessage(parent.getShell(), "��Ƭ��Ϣ��������!");
					return;
				}
			}
		});
		button_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/excel.ico"));
		button_2.setLayoutData(new RowData(SWT.DEFAULT, 20));
		button_2.setText("\u5BFC  \u51FA");
		
		tableViewer = new TableViewer(group_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));//����table��ɫ�����ᱻ����ɫ����
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setLabelProvider(new CardLabelProvider());
		tableViewer.setContentProvider(new CardContentProvider());
		tableViewer.setSorter(new CardSorter());
		//table.setBackground(Color.WHITE)
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(96);
		tableColumn.setText("����");
		tableColumn.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -1:1);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(81);
		tableColumn_2.setText("������");
		tableColumn_2.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -2:2);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(78);
		tableColumn_1.setText("\u8F66\u724C\u53F7");
		tableColumn_1.addSelectionListener(new SelectionAdapter(){  //���ƺ�
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -3:3);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(85);
		tableColumn_3.setText("\u6301\u5361\u4EBA");
		tableColumn_3.addSelectionListener(new SelectionAdapter(){  //��������
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -4:4);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(85);
		tableColumn_10.setText("\u8F66\u578B");
		tableColumn_10.addSelectionListener(new SelectionAdapter(){  //����
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -5:5);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.NONE);
		tableColumn_11.setWidth(102);
		tableColumn_11.setText("\u8054\u7CFB\u65B9\u5F0F");
		
		TableColumn tableColumn_12 = new TableColumn(table, SWT.NONE);
		tableColumn_12.setWidth(176);
		tableColumn_12.setText("\u5F00\u5361\u65E5\u671F");
		tableColumn_12.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -6:6);
				tableViewer.refresh();
			}
		});
		
		TableColumn tableColumn_13 = new TableColumn(table, SWT.NONE);
		tableColumn_13.setWidth(109);
		tableColumn_13.setText("\u5361\u7247\u72B6\u6001");
		tableColumn_13.addSelectionListener(new SelectionAdapter(){  //��Ƭ״̬
			public void widgetSelected(SelectionEvent e) {
				a = !a;
				((CardSorter)tableViewer.getSorter()).doSort(a? -7:7);
				tableViewer.refresh();
			}
		});
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER | SWT.FLAT);
		tabFolder.setSimple(false);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tabItem_2 = new CTabItem(tabFolder, SWT.NONE);
		tabItem_2.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		tabItem_2.setImage(ResourceManager.getPluginImage("ParkingSystem", IimageKeys.CHARGE_MANAGE_TRAY));
		tabItem_2.setText("\u6536\u8D39\u65B9\u5F0F\u7BA1\u7406");
		tabFolder.setSelection(tabItem_2);
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_2.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, true));
		
		final Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite_3 = new GridData(GridData.FILL_HORIZONTAL);
		gd_composite_3.verticalAlignment = SWT.TOP;
		composite_3.setLayoutData(gd_composite_3);
		
		Button button_1 = new Button(composite_3, SWT.NONE);
		button_1.setLayoutData(new RowData(SWT.DEFAULT, 24));
		button_1.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/add.ico"));
		button_1.addSelectionListener(new SelectionAdapter() {  //�����շѷ�ʽ
			@Override
			public void widgetSelected(SelectionEvent e) {
				TChargeTypeDialog ttd = new TChargeTypeDialog(parent.getShell(), SWT.NONE);
				Object result = ttd.open(); //
				if(result != null){  //�շѷ�ʽ�����ɹ�
					List<Object> tcts = (List<Object>) tableViewer_1.getInput();
					tcts.add(result);
					tableViewer_1.refresh();
				}
			}
		});
		button_1.setText("\u65B0\u589E\u6536\u8D39\u65B9\u5F0F");
		
		Button button_4 = new Button(composite_3, SWT.NONE);
		button_4.setLayoutData(new RowData(95, 24));
		button_4.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button_4.setText("\u67E5\u8BE2\u6240\u6709");
		
		Button button_3 = new Button(composite_3, SWT.NONE);
		button_3.setLayoutData(new RowData(SWT.DEFAULT, 24));
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableViewer_1.getInput() == null){
					MessageDialogUtil.showWarningMessage(parent.getShell(), "���޿ɵ�������!");
					return;
				}
				String title = "�շѷ�ʽ��¼";
				String filename = "�շѷ�ʽ��¼";
				ExcelExportAction eea = new ExcelExportAction(parent.getShell(), title, filename, tableViewer_1);
				try {
					eea.run2();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showErrorMessage(parent.getShell(), "�շѷ�ʽ��¼��������!");
					return;
				}
			}
		});
		button_3.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/excel.ico"));
		button_3.setText("\u5BFC  \u51FA");
		
		tableViewer_1 = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		//table_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table_1.setLayoutData(new GridData(GridData.FILL_BOTH));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		tableViewer_1.setContentProvider(new ChargeTypeContentProvider()); 
		tableViewer_1.setLabelProvider(new ChargeTypeLabelProvider());
		
		button_4.addSelectionListener(new SelectionAdapter() {  //��ѯ�����շѷ�ʽ
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChargeTypeService cts = new ChargeTypeService();
				List<Object> input;
				try {
					input = cts.getAllChargeTypes();
				} catch (Exception e1) {
					MessageDialogUtil.showErrorMessage(parent.getShell(), "��ȡ�����շѷ�ʽʧ��!");
					return;
				} 
				tableViewer_1.setInput(input);
				tableViewer_1.refresh();
			}
		});
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u6536\u8D39\u540D\u79F0");
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u6536\u8D39\u65B9\u5F0F");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(373);
		tableColumn_6.setText("\u8BE6\u7EC6\u4FE1\u606F");
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		tabItem.setImage(ResourceManager.getPluginImage("ParkingSystem", IimageKeys.CARDTYPE_MANAGE_TRAY));
		tabItem.setText("\u5361\u7247\u7C7B\u578B\u7BA1\u7406");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_4);
		composite_4.setLayout(new GridLayout(1, true));
		
		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite_5.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button button_5 = new Button(composite_5, SWT.NONE);
		button_5.setLayoutData(new RowData(108, 24));
		button_5.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/add.ico"));
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {   //   ������Ƭ����
				TCardTypeDialog tctd = new TCardTypeDialog(parent.getShell(), SWT.NONE);
				Object result = tctd.open();
				if(result != null ){
					List<Object> cardTypes = (List<Object>) tableViewer_2.getInput();
					cardTypes.add(result);
					tableViewer_2.refresh();
				}
			}
		});
		button_5.setText("\u65B0\u589E\u5361\u7247\u7C7B\u578B");
		
		Button button_8 = new Button(composite_5, SWT.NONE);
		button_8.setLayoutData(new RowData(95, 24));
		button_8.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button_8.setText("\u67E5\u8BE2\u6240\u6709");
		
		Button button_6 = new Button(composite_5, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {   //������Ƭ����  Excel
				if(tableViewer_2.getInput() == null){
					MessageDialogUtil.showWarningMessage(parent.getShell(), "���޿ɵ�������!");
					return;
				}
				String title = "��Ƭ���ͼ�¼";
				String filename = "��Ƭ���ͼ�¼";
				ExcelExportAction eea = new ExcelExportAction(parent.getShell(), title, filename, tableViewer_2);
				try {
					eea.run2();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showErrorMessage(parent.getShell(), "��Ƭ���ͼ�¼��������!");
					return;
				}
			}
		});
		button_6.setLayoutData(new RowData(68, 24));
		button_6.setText("\u5BFC  \u51FA");
		button_6.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/excel.ico"));
		
		tableViewer_2 = new TableViewer(composite_4, SWT.BORDER | SWT.FULL_SELECTION|SWT.MULTI);
		table_2 = tableViewer_2.getTable();
		//table_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table_2.setLayoutData(new GridData(GridData.FILL_BOTH));
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		tableViewer_2.setLabelProvider(new CardTypeLabelProvider());
		tableViewer_2.setContentProvider(new CardTypeContentProvider());
		
		button_8.addSelectionListener(new SelectionAdapter() {  //��ѯ���п�Ƭ����
			@Override
			public void widgetSelected(SelectionEvent e) {
				CardTypeService cts = new CardTypeService();
				List<Object> input;
				try {
					input = cts.getAllCardTypes();
				} catch (Exception e1) {
					MessageDialogUtil.showErrorMessage(parent.getShell(), "��ȡ���п�Ƭ����ʧ��!");
					return;
				} 
				tableViewer_2.setInput(input);
				tableViewer_2.refresh();
			}
		});
		
		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u5361\u7247\u7C7B\u578B\u540D\u79F0");
		
		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("\u6536\u8D39\u65B9\u5F0F");
		
		TableColumn tableColumn_9 = new TableColumn(table_2, SWT.NONE);
		tableColumn_9.setWidth(377);
		tableColumn_9.setText("\u5907\u6CE8\u4FE1\u606F");

		composite_1.layout();
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		
		pageComposite = new PageComposite(group_1, SWT.NONE,tableViewer);  //������ҳģ��
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		
		TableListener.tableCellHeight(table_1);
		TableListener.tableColor(table_1);
		TableListener.tableCellHeight(table_2);
		TableListener.tableColor(table_2);
		sashForm.setWeights(new int[] {372, 207});
		//��Ƭ��Ϣ
		makeActions(parent);
		hookContextMenu();  
		hookDoubleClickAction(parent);
		
		//�շѷ�ʽ
	}
	
	/**
	 * ���˫��
	 */
	private void hookDoubleClickAction(final Composite parent) {
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {//���˫���༭��Ƭ��Ϣ
			public void doubleClick(DoubleClickEvent event) {
				editAction(parent);
			}
		});
		tableViewer_1.addDoubleClickListener(new IDoubleClickListener() { //���˫���༭�շѷ�ʽ 
			public void doubleClick(DoubleClickEvent event) {
				editChargeAction(parent);
			}
		});
		tableViewer_2.addDoubleClickListener(new IDoubleClickListener() {  //���༭˫����Ƭ����
			public void doubleClick(DoubleClickEvent event) {
				editCardTypeAction(parent);
			}
		});
	}
	/**
	 * �Ҽ�
	 */
	private void hookContextMenu() {
		////////////////////////////��Ƭ��Ϣ//////////////////////////////////////
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				int count = tableViewer.getTable().getSelectionCount();
				editAction.setEnabled(false);
				deleteAction.setEnabled(false);
				payAction.setEnabled(false);
				lockAction.setEnabled(false);
				if(count > 1){  //���ѡ���˶���
					 deleteAction.setEnabled(true);
				}else if(count == 1){ //���ѡ���˵���
					 deleteAction.setEnabled(true);
					 TCard card = (TCard) tableViewer.getTable().getSelection()[0].getData();
					 System.out.println(card.getCardid());
					 if(card.getCardtype() == TCard.LONG_CARD){  //����ǳ��ڿ�  ���Խɷ� ���Ա༭
						 editAction.setEnabled(true);
						 payAction.setEnabled(true);
					 }
					 if(card.getCardstatus() == TCard.AVILIABLE){
						 lockAction.setText("����");
					 }else{
						 lockAction.setText("����");
					 }
					 lockAction.setEnabled(true);
				}
				manager.add(editAction);
				manager.add(deleteAction);
				manager.add(new Separator());
				manager.add(payAction);
				manager.add(new Separator());
				manager.add(lockAction);
			}
		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, tableViewer);
		
		/////////////////////////�շѷ�ʽ/////////////////////////////////////////
		MenuManager menuMgr1 = new MenuManager("#PopupMenu");
		menuMgr1.setRemoveAllWhenShown(true);
		menuMgr1.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				int count = tableViewer_1.getTable().getSelectionCount();
				editChargeAction.setEnabled(false);
				delChargeAction.setEnabled(false);
				if(count == 1){
					editChargeAction.setEnabled(true);
					delChargeAction.setEnabled(true);
				}
				manager.add(editChargeAction);  //�༭
				manager.add(delChargeAction);   //ɾ��
			}
		});
		Menu menu1 = menuMgr1.createContextMenu(tableViewer_1.getControl());
		tableViewer_1.getControl().setMenu(menu1);
		getSite().registerContextMenu(menuMgr1, tableViewer_1);
		/////////////////////////��Ƭ����/////////////////////////////////////////
		MenuManager menuMgr2 = new MenuManager("#PopupMenu");
		menuMgr2.setRemoveAllWhenShown(true);
		menuMgr2.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				int count = tableViewer_2.getTable().getSelectionCount();
				editCardTypeAction.setEnabled(false);
				delCardTypeAction.setEnabled(false);
				if(count == 1){
					editCardTypeAction.setEnabled(true);
					delCardTypeAction.setEnabled(true);
				}
				manager.add(editCardTypeAction);  //�༭
				manager.add(delCardTypeAction);   //ɾ��
			}
		});
		Menu menu2 = menuMgr2.createContextMenu(tableViewer_2.getControl());
		tableViewer_2.getControl().setMenu(menu2);
		getSite().registerContextMenu(menuMgr2, tableViewer_2);
	}
	
	/**
	 * �༭��Ƭ�¼���Ӧ
	 * @param parent
	 */
	public void editAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection(); //��ȡѡ�е��� ����
		TCard card = (TCard) ti[0].getData();
		Iterator<TMember> it = card.getTMembers().iterator();
		TMember member = null; 
		if(it.hasNext()){
			member = it.next();
		}
		if(member == null){
			MessageDialogUtil.showWarningMessage(parent.getShell(), "�ÿ�Ƭδ���û���!");
			return;
		}
		TCardEditDialog tced = new TCardEditDialog(parent.getShell(), SWT.NONE, card);
		Object result = tced.open();
		if(result != null){  //���³ɹ�
			tableViewer.refresh();
		}
	}
	/**
	 * ɾ����Ƭ�¼���Ӧ
	 * @param parent
	 */
	public void deleteAction(Composite parent){
		int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "ȷ��ɾ����Ƭ��Ϣô?");
		if(a == SWT.CANCEL){
			 return;
		}
		TableItem[] ti = tableViewer.getTable().getSelection(); //��ȡѡ�е��� ����
		List<Object> removes = new ArrayList<Object>(); //ɾ���Ķ���
		CardService cs = new CardService();
			for(int i=0;i<ti.length;i++){
				try {
					cs.delCard(ti[i].getData());
					removes.add(ti[i].getData());
				} catch (Exception e) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ƭ��Ϣɾ����ʧ��!");
					return;
				}
			}
		pageComposite.updateAfterDelByRemove(removes);
	}
	/**
	 * ����/���� �¼���Ӧ
	 * @param parent
	 */
	public void lockAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection(); //��ȡѡ�е��� ����
		TCard card = (TCard) ti[0].getData();
		if(card.getCardstatus() == TCard.AVILIABLE){
			card.setCardstatus(TCard.LOCKED);
		}else{
			card.setCardstatus(TCard.AVILIABLE);
		}
		CardService cs = new CardService();
		try {
			cs.updateCard(card);
		} catch (Exception e) {
		  if(card.getCardstatus() == TCard.AVILIABLE)
			  MessageDialogUtil.showWarningMessage(parent.getShell(), "����ʧ��!");
		  else
			  MessageDialogUtil.showInfoMessage(parent.getShell(), "��Ƭ����ʧ��!");
		  return;
		}
		 if(card.getCardstatus() == TCard.AVILIABLE)
			 MessageDialogUtil.showInfoMessage(parent.getShell(), "��Ƭ����ɹ�!");
		  else
			  MessageDialogUtil.showInfoMessage(parent.getShell(), "�����ɹ�!");
		tableViewer.refresh();
	}
	/**
	 * �ɷ��¼���Ӧ
	 * @param parent
	 */
	public void payAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection(); //��ȡѡ�е��� ����
		TCard card = (TCard) ti[0].getData();
		Iterator<TMember> it = card.getTMembers().iterator();
		TMember member = null; 
		if(it.hasNext()){
			member = it.next();
		}
		if(member == null){
			MessageDialogUtil.showWarningMessage(parent.getShell(), "�ÿ�Ƭδ���û���!");
			return;
		}
		PayDialog pd = new PayDialog(parent.getShell(), SWT.NONE, card);
		pd.open();
	}
	/**
	 * �༭�շѷ�ʽ��Ϣ
	 */
	public void editChargeAction(Composite parent){
		TableItem[] ti = tableViewer_1.getTable().getSelection(); //��ȡѡ�е��� ����
		TChargeType tct = (TChargeType) ti[0].getData();
//		ChargeTypeService cts = new ChargeTypeService();
//		MessageDialog.openWarning(parent.getShell(), "��ʾ", "�༭�շѷ�ʽ��"+tct.getName());
		if(tct != null){
			TChargeTypeEditDialog tcted = new TChargeTypeEditDialog(parent.getShell(), SWT.NONE, tct);
			Object result = tcted.open();
			if(result != null){   //������³ɹ�
				tableViewer_1.refresh();
			}
		}
	}
	/**
	 * ɾ���շѷ�ʽ��Ϣ
	 * @param parent
	 */
	public void delChargeAction(Composite parent){
		int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "ȷ��ɾ����ɾ���շѷ�ʽô?");
		if(a == SWT.CANCEL) return;
		TableItem[] ti = tableViewer_1.getTable().getSelection(); //��ȡѡ�е��� ����
		TChargeType tct = (TChargeType) ti[0].getData();
		ChargeTypeService cts = new ChargeTypeService();
		try {
			cts.delChargeType(tct);
		} catch (Exception e) {
			MessageDialogUtil.showWarningMessage(parent.getShell(), "ɾ���շѷ�ʽʧ�ܣ�");
			return;
		}
		List<Object> tcts = (List<Object>) tableViewer_1.getInput();
		tcts.remove(tct);
//		tableViewer_1.setInput(tcts);
		tableViewer_1.refresh();
	}
	/**
	 * �༭������Ϣ
	 * @param parent
	 */
	public void editCardTypeAction(Composite parent){
		TableItem[] ti = tableViewer_2.getTable().getSelection(); //��ȡѡ�е��� ����
		TCardType tct = (TCardType) ti[0].getData();
		if(tct != null){
			TCardTypeEditDialog tced = new TCardTypeEditDialog(parent.getShell(), SWT.NONE, tct);
			Object result = tced.open();
			if(result != null){      //������³ɹ� 
				tableViewer_2.refresh(); //���±��
			}
		}
	}
	/**
	 * ɾ��������Ϣ
	 * @param parent
	 */
	public void delCardTypeAction(Composite parent){
		int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "��ɾ���ÿ�Ƭ����ô?");
		if( a == SWT.CANCEL) return;
		TableItem[] ti = tableViewer_2.getTable().getSelection(); //��ȡѡ�е��� ����
		TCardType tct = (TCardType) ti[0].getData();
		CardTypeService cts = new CardTypeService();
		try {
			cts.delCardType(tct);
		} catch (Exception e) {
			MessageDialogUtil.showWarningMessage(parent.getShell(), "ɾ����Ƭ����ʧ�ܣ�\n"+e.getMessage());
			return;
		}
		List<Object> tcts = (List<Object>) tableViewer_2.getInput();
		tcts.remove(tct);
		tableViewer_2.setInput(tcts);
		tableViewer_2.refresh();
	}
	
	/**
	 * ��ʼ��action����
	 */
	public void makeActions(final Composite parent){
		editAction = new Action() {
			public void run() {
				editAction(parent);
			}
		};
		editAction.setText("�༭");
		editAction.setToolTipText("�༭��Ƭ��Ϣ");
		editAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.EDIT_POP_MANAGE_TRAY));
		
		deleteAction = new Action() {
			public void run() {
				deleteAction(parent);
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setToolTipText("ɾ����Ƭ��Ϣ");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
		
		payAction = new Action() {
			public void run() {
				payAction(parent);
			}
		};
		payAction.setText("�ɷ�");
		payAction.setToolTipText("��Ա�ɷ�");
		payAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.PAY_POP_MANAGE_TRAY));
		
		lockAction = new Action() {
			public void run() {
				lockAction(parent);
			}
		};
		lockAction.setText("����");
		lockAction.setToolTipText("�����ÿ�");
		lockAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.LOCK_POP_MANAGE_TRAY));
		
		editChargeAction = new Action() {
			public void run() {
				editChargeAction(parent);
			}
		};
		editChargeAction.setText("�༭");
		editChargeAction.setToolTipText("�༭�շѷ�ʽ");
		editChargeAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.EDIT_POP_MANAGE_TRAY));
		
		delChargeAction = new Action() {
			public void run() {
				delChargeAction(parent);
			}
		};
		delChargeAction.setText("ɾ��");
		delChargeAction.setToolTipText("ɾ���շѷ�ʽ");
		delChargeAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
		
		editCardTypeAction = new Action() {
			public void run() {
				editCardTypeAction(parent);
			}
		};
		editCardTypeAction.setText("�༭");
		editCardTypeAction.setToolTipText("�༭��Ƭ����");
		editCardTypeAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.EDIT_POP_MANAGE_TRAY));
		
		delCardTypeAction = new Action() {
			public void run() {
				delCardTypeAction(parent);
			}
		};
		delCardTypeAction.setText("ɾ��");
		delCardTypeAction.setToolTipText("ɾ����Ƭ����");
		delCardTypeAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
	}

	public void setFocus() {
	}
}

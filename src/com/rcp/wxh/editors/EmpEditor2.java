package com.rcp.wxh.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.dialogs.TEmpDialog;
import com.rcp.wxh.dialogs.TEmpEditDialog;
import com.rcp.wxh.dialogs.Temp;
import com.rcp.wxh.fortables.EmpContentProvider;
import com.rcp.wxh.fortables.EmpLabelProvider;
import com.rcp.wxh.fortables.TableListener;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.pojo.TEmp;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.EmpService;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.hibernate.mapping.Array;

import parkingsystem.Activator;

import com.swtdesigner.ResourceManager;

/**
 * Ա����Ϣ����༭��
 * @author wuxuehong  2011-11-16
 *
 */
public class EmpEditor2 extends EditorPart {
	
	public static final String ID = EmpEditor2.class.getName();
	
	private Table table;
	private Text text;
	private TableViewer tableViewer;
	
	private Action editAction; //�༭
	private Action deleteAction; //ɾ��
	private Action resetAction; //��������
	
	private int sizePage = 25; //ÿҳ��ʾ��
	
	private PageComposite pageComposite; //��ҳ���
	
	
	public EmpEditor2() {
	}

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

	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(1, true));
		
		parent.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		parent.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Group group = new Group(parent, SWT.NONE);
		group.setText("\u5458\u5DE5\u4FE1\u606F");
		group.setLayout(new GridLayout(1, true));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite composite = new Composite(group, SWT.BORDER);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //combo�¼�
				if("ȫ��".equals(combo.getText())){
					text.setText("");
					text.setEnabled(false);
				}else{
					text.setEnabled(true);
				}
			}
		});
		combo.setItems(new String[] {"\u5168\u90E8", "\u5458\u5DE5ID", "\u5458\u5DE5\u59D3\u540D"});
		combo.setText("\u5168\u90E8");
		
		Label label = new Label(composite, SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("����", 14, SWT.NORMAL));
		label.setLayoutData(new RowData(16, 19));
		label.setText("=");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new RowData(120, 14));
		text.setEnabled(false);
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(72, 20));
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {   //��ѯ
				pageComposite.init(); //��ҳ��ʼ��  ������������
				String op = combo.getText().trim();
				EmpService es = new EmpService();
				try {
						if("ȫ��".equals(combo.getText().trim())){      //��ѯ����
							 List<Object> emps = null;
							emps = es.getAllEmp();
						     //��ȡ����Ա����Ϣ
							if(emps != null)
								pageComposite.setData(emps,sizePage);
						}else if("Ա��ID".equals(op)){
							TEmp emp = es.getEmpById(text.getText().trim());
							if(emp!=null){
								List<Object> emps = new ArrayList<Object>();
								emps.add(emp);
								pageComposite.setData(emps, sizePage);
							}else{
								MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ա��ID������!");
							}
						}else if("Ա������".equals(op)){
							List<Object> emps = es.getEmpByName(text.getText().trim());
							if(emps != null)
								pageComposite.setData(emps, sizePage);
							else
								MessageDialogUtil.showWarningMessage(parent.getShell(), "��Ա������������!");
						}
				} catch (Exception e1) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), "��ȡԱ����Ϣʧ��!");
					return;
				} 
			}
		});
		button.setText("\u67E5  \u8BE2");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setLayoutData(new RowData(84, 20));
		button_1.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/add.ico"));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //����Ա��  ��Ա���Ի���
				TEmpDialog ted = new TEmpDialog(parent.getShell(), SWT.NONE);
				Object o = ted.open();
				if(o != null)
					pageComposite.updateAfterDelByAdd(o);
			}
		});
		button_1.setText("\u65B0\u589E\u5458\u5DE5");
		
		tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		tableViewer.setContentProvider(new EmpContentProvider());
		tableViewer.setLabelProvider(new EmpLabelProvider());
		
		TableColumn tblclmnid = new TableColumn(table, SWT.NONE);
		tblclmnid.setWidth(100);
		tblclmnid.setText("\u5458\u5DE5ID");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(78);
		tableColumn_1.setText("\u5458\u5DE5\u59D3\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(91);
		tableColumn_2.setText("\u64CD\u4F5C\u6743\u9650");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u8054\u7CFB\u65B9\u5F0F");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(102);
		tableColumn_3.setText("\u8BC1\u4EF6\u53F7\u7801");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u4F4F\u5740");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(158);
		tableColumn_5.setText("\u5907\u6CE8\u4FE1\u606F");
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		
		pageComposite = new PageComposite(group, SWT.NONE,tableViewer);
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// TODO Auto-generated method stub

		makeActions(parent);   //��ʼ���¼���Ӧ action
		hookContextMenu(); //�Ҽ��˵�
		hookDoubleClickAction(parent);
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
				editAction.setEnabled(false);
				deleteAction.setEnabled(false);
				resetAction.setEnabled(false);
				int count = tableViewer.getTable().getSelectionCount();
				if(count ==1 ){
					editAction.setEnabled(true);
					deleteAction.setEnabled(true);
					resetAction.setEnabled(true);
				}
				manager.add(editAction);
				manager.add(deleteAction);
				manager.add(new Separator());
				manager.add(resetAction);
				
			}
		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, tableViewer);
	}
	/**
	 * �༭��Ӧ
	 * @param parent
	 */
	public void editAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection();
		if(ti.length == 1){  //���ѡ����һ��
			TEmp emp = (TEmp) ti[0].getData();
			if(emp != null){
				TEmpEditDialog teed = new TEmpEditDialog(parent.getShell(), SWT.NONE, emp);
				Object o = teed.open();
				if(o != null)
					tableViewer.refresh();
			}
		}
	}
	/**
	 * ɾ����Ӧ
	 * @param parent
	 */
	public void deleteAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection();
		if(ti.length == 1){  //���ѡ����һ��
			int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "ȷ��ɾ����Ա����Ϣô?,ɾ�����޷��ָ�!");
			if(a == SWT.CANCEL) return; 
			TEmp emp = (TEmp) ti[0].getData();
			EmpService es = new EmpService();
			if(emp.getPriority().intValue() == TEmp.ADMINSTRATOR){ //�����ɾ������Ա��Ϣ
				try {
					List<Object> admins = es.getAllAdmin();
					if(admins.size() == 1){
						MessageDialogUtil.showWarningMessage(parent.getShell(), "ϵͳ�б���Ҫ����һ������Ա!");
						return;
					}
				} catch (Exception e) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), "����Ա��Ϣ��ȡʧ��!");
					return;
				}
			}
			try {
				es.delEmp(emp);
			} catch (Exception e) {
				MessageDialogUtil.showWarningMessage(parent.getShell(), "Ա����Ϣɾ��ʧ��!");
				return;
			}
//			List<Object> input = (List<Object>) tableViewer.getInput();
//			if(input!=null){   //������ʾ�б�
//				input.remove(emp);
//				tableViewer.refresh();
//			}
			List<Object> emps = new ArrayList<Object>();
			if(emp != null){
				emps.add(emp);
				pageComposite.updateAfterDelByRemove(emps);
			}
		}
	}
	
	/**
	 * ����������Ӧ
	 * @param parent
	 */
	public void resetAction(Composite parent){
		TableItem[] ti = tableViewer.getTable().getSelection();
		if(ti.length == 1){
			int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "ȷ�����ø�Ա������ô?");
			if(a == SWT.CANCEL) return; 
			TEmp emp = (TEmp) ti[0].getData();
			EmpService es = new EmpService();
			if(emp != null){
				emp.setPassword(emp.getOperatorid());
				try {
					es.updateEmp(emp);
				} catch (Exception e) {
					MessageDialogUtil.showWarningMessage(parent.getShell(), "Ա����������ʧ��!");
					return;
				}
				MessageDialogUtil.showInfoMessage(parent.getShell(), "�������óɹ�!");
			}
		}
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
		editAction.setToolTipText("�༭Ա����Ϣ");
		editAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.EDIT_POP_MANAGE_TRAY));
		
		deleteAction = new Action() {
			public void run() {
				deleteAction(parent);
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setToolTipText("ɾ��Ա����Ϣ");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
		
		resetAction = new Action() {
			public void run() {
				resetAction(parent);
			}
		};
		resetAction.setText("��������");
		resetAction.setToolTipText("����Ա������");
		resetAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.RESET_POP_MANAGE_TRAY));
	}
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}

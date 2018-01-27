package com.rcp.wxh.editors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import parkingsystem.Activator;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.actions.ExcelExportAction;
import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.fortables.ExpenseContentProvider;
import com.rcp.wxh.fortables.ExpenseLabelProvider;
import com.rcp.wxh.fortables.TableListener;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.ExpenseRecordService;
import com.rcp.wxh.utils.CardUtil;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

/**
 * �ɷѼ�¼����༭��
 * @author wuxuehong  2011-12-2
 *
 */
public class ExpenseEditor extends EditorPart {
	public ExpenseEditor() {
	}
	
	//ΨһID
	public static final String ID = ExpenseEditor.class.getName();
	
	private int sizePage = 25; //ÿҳ��ʾ����
	private PageComposite pageComposite;  //��ҳģ��
	private Action deleteAction; //ɾ��������Ϣ
	private Table table;
	private TableViewer tableViewer;
	private Text text;

	public void doSave(IProgressMonitor monitor) {
	}

	public void doSaveAs() {
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	public void createPartControl(final Composite parent) {
		parent.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		parent.setBackgroundMode(SWT.INHERIT_FORCE);
		parent.setLayout(new GridLayout(1, true));
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		final DateTime dateTime = new DateTime(composite, SWT.BORDER);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("����", 14, SWT.NORMAL));
		label.setLayoutData(new RowData(16, 18));
		label.setText("\u2014");
		
		final DateTime dateTime_1 = new DateTime(composite, SWT.BORDER);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new RowData(22, 14));
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setLayoutData(new RowData(91, 20));
		combo.setItems(new String[] {"\u5168\u90E8", "\u5361\u53F7", "\u8F66\u724C\u53F7\u7801", "\u6301\u5361\u4EBA"});
		combo.setText("\u5168\u90E8");
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //������
				if("ȫ��".equals(combo.getText())){
					text.setText("");
					text.setEnabled(false);
				}else if("����".equals(combo.getText())){
					text.setEnabled(false);
					text.setText("�뽫�������Ӧ��!");
				}else{
					text.setEnabled(true);
					text.setText("");
				}
			}
		});
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("����", 14, SWT.NORMAL));
		label_2.setLayoutData(new RowData(18, 20));
		label_2.setText("=");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new RowData(114, 15));
		text.setEnabled(false);
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(75, 20));
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button.setText("\u67E5  \u8BE2");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableViewer.getInput() == null){
					MessageDialogUtil.showWarningMessage(parent.getShell(), "���޿ɵ�������!");
					return;
				}
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, dateTime.getYear());
				cal.set(Calendar.MONTH, dateTime.getMonth());
				cal.set(Calendar.DATE, dateTime.getDay());
				cal.set(Calendar.HOUR, -12);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date startTime = cal.getTime();
			 	
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, dateTime_1.getYear());
				cal2.set(Calendar.MONTH, dateTime_1.getMonth());
				cal2.set(Calendar.DATE, dateTime_1.getDay());
				cal2.set(Calendar.HOUR, 12);
				cal2.set(Calendar.MINUTE, 0);
				cal2.set(Calendar.SECOND, 0);
				Date endTime = cal2.getTime();
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				String title = "���ڿ��ɷѼ�¼";
				String filename = sdf.format(startTime)+"��"+sdf.format(endTime)+"���ڿ��ɷѼ�¼";
				ExcelExportAction eea = new ExcelExportAction(parent.getShell(), filename, filename, pageComposite);
				try {
					eea.run();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showErrorMessage(parent.getShell(), "�ɷѼ�¼��Ϣ��������!");
					return;
				}
			}
		});
		button_1.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/excel.ico"));
		button_1.setLayoutData(new RowData(SWT.DEFAULT, 20));
		button_1.setText("\u5BFC  \u51FA");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //��ѯ
				//��յ�ǰ�б��е���Ϣ
				tableViewer.setInput(null);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, dateTime.getYear());
				cal.set(Calendar.MONTH, dateTime.getMonth());
				cal.set(Calendar.DATE, dateTime.getDay());
				cal.set(Calendar.HOUR, -12);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date startTime = cal.getTime();
			 	
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, dateTime_1.getYear());
				cal2.set(Calendar.MONTH, dateTime_1.getMonth());
				cal2.set(Calendar.DATE, dateTime_1.getDay());
				cal2.set(Calendar.HOUR, 12);
				cal2.set(Calendar.MINUTE, 0);
				cal2.set(Calendar.SECOND, 0);
				Date endTime = cal2.getTime();
				
				ExpenseRecordService eps = new ExpenseRecordService();
				List<Object> expenses = null;
				try {
					if("ȫ��".equals(combo.getText())){  //��ѯȫ��
							expenses = eps.getAllExpRecords(startTime,endTime);
					}else if("����".equals(combo.getText())){
						long cardID = CardUtil.getCardNum();
						if(cardID == -1){
							text.setText("�뽫�������Ӧ��!");
							MessageDialogUtil.showWarningMessage(parent.getShell(), "�뽫�������Ӧ�������ٲ�ѯ!");
							return;
						}
							text.setText(cardID+"");
							expenses = eps.getExpRecordByCardId(cardID+"", startTime, endTime);
					}else if("���ƺ���".equals(combo.getText())){
						String carnumber = text.getText().trim();
						if("".equals(carnumber)){
							MessageDialogUtil.showWarningMessage(parent.getShell(), "������Ҫ��ѯ�ĳ��ƺ���!");
							return;
						}
						expenses = eps.getExpRecordByCarnumber(carnumber, startTime, endTime);
					}else if("�ֿ���".equals(combo.getText())){
						String ownername = text.getText().trim();
						if("".equals(ownername)){
							MessageDialogUtil.showWarningMessage(parent.getShell(), "������Ҫ��ѯ�ĳֿ�������!");
							return;
						}
						expenses = eps.getExpRecordByOwnername(ownername, startTime, endTime);
					}
					if(expenses == null) throw new Exception();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showWarningMessage(parent.getShell(), "���ü�¼��Ϣ��ȡʧ��!");
					return;
				}
				if(expenses.size() == 0){
					MessageDialogUtil.showWarningMessage(parent.getShell(), "��ǰû����ط��ü�¼!");
					return;
				}
				pageComposite.setData(expenses, sizePage);
			}
		});
		
		Group group = new Group(parent, SWT.NONE);
		group.setText("�ɷ���Ϣ��¼");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(1,true));
		
		tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		tableViewer.setContentProvider(new ExpenseContentProvider());
		tableViewer.setLabelProvider(new ExpenseLabelProvider());
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(77);
		tableColumn.setText("\u64CD\u4F5C\u5458");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(83);
		tableColumn_6.setText("\u5361\u578B");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(82);
		tableColumn_1.setText("\u6301\u5361\u4EBA");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(97);
		tableColumn_2.setText("\u8F66\u724C\u53F7\u7801");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(104);
		tableColumn_3.setText("\u5E94\u4ED8\u8D39\u7528(\u5143)");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(98);
		tableColumn_4.setText("\u5B9E\u4ED8\u8D39\u7528(\u5143)");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(186);
		tableColumn_5.setText("\u7F34\u8D39\u65E5\u671F");
		
		
		pageComposite = new PageComposite(group, SWT.NONE, tableViewer);
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(195);
		tableColumn_7.setText("\u5907\u6CE8\u4FE1\u606F");
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		hookContextMenu();
		makeAction(parent);
	}

	/**
	 * �Ҽ��˵�
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				int count = tableViewer.getTable().getSelectionCount();
				deleteAction.setEnabled(false);
				if(count > 0){
					deleteAction.setEnabled(true);
				}else
					deleteAction.setEnabled(false);
				manager.add(deleteAction);
			}
		});
		Menu menu = menuMgr.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
	}
	/**
	 * ��ʼ��actions
	 */
	public void makeAction(final Composite parent){
		deleteAction = new Action() {
			public void run() {
				int a = MessageDialogUtil.showConfirmMessage(parent.getShell(), "ȷ��Ҫɾ��ѡ�еķ��ü�¼ô?");
				if(a == SWT.CANCEL) 
					return;
				TableItem[] ti = tableViewer.getTable().getSelection();
				ExpenseRecordService expService = new ExpenseRecordService();
				List<Object> removes = new ArrayList<Object>();
				for(int i=0;i<ti.length;i++){
					Object expRecord = ti[i].getData();
					try {
						expService.delExpenseRecord((TExpenseRecord)expRecord);
						removes.add(expRecord);
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialogUtil.showErrorMessage(parent.getShell(), "���ü�¼ɾ��ʧ��!");
						return;
					}
				}
				pageComposite.updateAfterDelByRemove(removes); //��ҳ������
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setToolTipText("ɾ�����ü�¼");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
	}
	
	public void setFocus() {

	}
}

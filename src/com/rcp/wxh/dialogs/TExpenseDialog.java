package com.rcp.wxh.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.fortables.ExpenseContentProvider;
import com.rcp.wxh.fortables.ExpenseLabelProvider;
import com.rcp.wxh.fortables.TableListener;
import com.rcp.wxh.pojo.TExpenseRecord;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.ExpenseRecordService;
import com.rcp.wxh.utils.CardUtil;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import parkingsystem.Activator;

import com.swtdesigner.ResourceManager;

/**
 * ������Ϣ��¼�����ѯ
 * @author wuxuehong  2011-11-14
 *
 */
public class TExpenseDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Text text;
	private TableViewer tableViewer;
	
	private int sizePage = 25; //ÿҳ��ʾ����

	private PageComposite composite_1;  //��ҳģ��
	private Action deleteAction; //ɾ��������Ϣ
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TExpenseDialog(Shell parent, int style) {
		super(parent, style);
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		Rectangle parentBounds = getParent().getBounds(); 
		Rectangle shellBounds = shell.getBounds(); 
		shell.setLocation(parentBounds.x + (parentBounds.width - shellBounds.width)/2, parentBounds.y + (parentBounds.height - shellBounds.height)/2);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
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
		shell.setSize(676, 534);
		shell.setText("\u8D39\u7528\u8BB0\u5F55\u67E5\u8BE2");
		shell.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(0, 0, 670, 50);
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //������
				if("ȫ��".equals(combo.getText())){
					text.setText("");
					text.setEditable(false);
				}else if("����".equals(combo.getText())){
					text.setEditable(false);
					text.setText("�뽫�������Ӧ��!");
				}else{
					text.setEditable(true);
					text.setText("");
				}
			}
		});
		combo.setItems(new String[] {"\u5168\u90E8", "\u5361\u53F7", "\u8F66\u724C\u53F7\u7801", "\u8F66\u4E3B\u59D3\u540D"});
		combo.setBounds(182, 21, 93, 20);
		combo.setText("\u5168\u90E8");
		
		Label label = new Label(composite, SWT.HORIZONTAL | SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("����", 12, SWT.NORMAL));
		label.setBounds(271, 20, 28, 20);
		label.setText("=");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(300, 22, 124, 20);
		text.setEditable(false);
		text.setTextLimit(30);
		
		Button button = new Button(composite, SWT.NONE);
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {  //��ѯ
				//��յ�ǰ�б��е���Ϣ
				tableViewer.setInput(null);
				
				ExpenseRecordService eps = new ExpenseRecordService();
				List<Object> expenses = null;
				try {
					if("ȫ��".equals(combo.getText())){  //��ѯȫ��
							expenses = eps.getAllExpRecords(null,null);
					}else if("����".equals(combo.getText())){
						long cardID = CardUtil.getCardNum();
						if(cardID == -1){
							text.setText("�뽫�������Ӧ��!");
							MessageDialogUtil.showWarningMessage(shell, "�뽫�������Ӧ�������ٲ�ѯ!");
							return;
						}
							text.setText(cardID+"");
							expenses = eps.getExpRecordByCardId(cardID+"",null,null);
					}else if("���ƺ���".equals(combo.getText())){
						String carnumber = text.getText().trim();
						if("".equals(carnumber)){
							MessageDialogUtil.showWarningMessage(shell, "������Ҫ��ѯ�ĳ��ƺ���!");
							return;
						}
						expenses = eps.getExpRecordByCarnumber(carnumber,null,null);
					}else if("��������".equals(combo.getText())){
						String ownername = text.getText().trim();
						if("".equals(ownername)){
							MessageDialogUtil.showWarningMessage(shell, "������Ҫ��ѯ�ĳ�������!");
							return;
						}
						expenses = eps.getExpRecordByOwnername(ownername,null,null);
					}
					if(expenses == null) throw new Exception();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showWarningMessage(shell, "���ü�¼��Ϣ��ȡʧ��!");
					return;
				}
				if(expenses.size() == 0){
					MessageDialogUtil.showWarningMessage(shell, "��ǰû����ط��ü�¼!");
					return;
				}
				composite_1.setData(expenses, sizePage);
			}
		});
		button.setBounds(429, 20, 72, 22);
		button.setText("\u67E5\u8BE2");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u7F34\u8D39\u4FE1\u606F");
		group.setBounds(10, 56, 650, 436);
		
		tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION| SWT.MULTI);
		table = tableViewer.getTable();		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 20, 630, 375);
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		tableViewer.setContentProvider(new ExpenseContentProvider());
		tableViewer.setLabelProvider(new ExpenseLabelProvider());
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(62);
		tableColumn.setText("\u64CD\u4F5C\u5458");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(70);
		tableColumn_6.setText("\u5361\u578B");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(67);
		tableColumn_1.setText("\u7F34\u8D39\u4EBA");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(74);
		tableColumn_2.setText("\u8F66\u724C\u53F7\u7801");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(94);
		tableColumn_3.setText("\u5E94\u4ED8\u8D39\u7528(\u5143)");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(90);
		tableColumn_4.setText("\u5B9E\u4ED8\u8D39\u7528(\u5143)");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(111);
		tableColumn_5.setText("\u7F34\u8D39\u65E5\u671F");
		
		composite_1 = new PageComposite(group, SWT.NONE, tableViewer);
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u5907\u6CE8\u4FE1\u606F");
		composite_1.setBounds(10, 398, 630, 35);
		
		
		
		makeAction();
		hookContextMenu();
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
	public void makeAction(){
		deleteAction = new Action() {
			public void run() {
				int a = MessageDialogUtil.showConfirmMessage(shell, "ȷ��Ҫɾ��ѡ�еķ��ü�¼ô?");
				if(a == SWT.CANCEL) 
					return;
				TableItem[] ti = tableViewer.getTable().getSelection();
				ExpenseRecordService expService = new ExpenseRecordService();
				List<Object> removes = new ArrayList<Object>();
				for(int i=0;i<ti.length;i++){
					Object expRecord = ti[i].getData();
					try {
						expService.delExpenseRecord((TExpenseRecord)expRecord);
//						composite_1.removeObject(expRecord); //�ɷ�ҳ����������   �����Ƴ�һ������
						removes.add(expRecord);
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialogUtil.showErrorMessage(shell, "���ü�¼ɾ��ʧ��!");
						return;
					}
				}
				composite_1.updateAfterDelByRemove(removes); //��ҳ������
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setToolTipText("ɾ�����ü�¼");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
	}
}

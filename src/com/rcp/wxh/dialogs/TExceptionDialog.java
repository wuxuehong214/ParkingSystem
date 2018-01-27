package com.rcp.wxh.dialogs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.actions.ExcelExportAction;
import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.fortables.ExceptionContentProvider;
import com.rcp.wxh.fortables.ExceptionLabelProvider;
import com.rcp.wxh.fortables.TableListener;
import com.rcp.wxh.pojo.TExceptionRecord;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.service.impl.ExceptionRecordService;
import com.rcp.wxh.utils.MessageDialogUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import parkingsystem.Activator;

import com.swtdesigner.ResourceManager;

/**
 * �쳣��Ϣ��ѯ����
 * @author wuxuehong  2011-11-14
 *
 */
public class TExceptionDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private PageComposite composite_1; //��ҳģ��
	
	private int sizePage = 25; // ����ÿҳ��ʾ�ļ�¼��Ŀ
	
	private Action deleteAction ; //ɾ���쳣��Ϣ
	private TableViewer tableViewer; //����ͼ

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TExceptionDialog(Shell parent, int style) {
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
		shell.setSize(518, 501);
		shell.setText("\u5F02\u5E38\u4FE1\u606F\u8BB0\u5F55");
		shell.setLayout(null);
		shell.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(0, 0, 512, 45);
		
		final DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(26, 10, 91, 21);
		
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("����", 13, SWT.NORMAL));
		label.setBounds(123, 10, 17, 21);
		label.setText("\u2014");
		
		final DateTime dateTime_1 = new DateTime(composite, SWT.BORDER);
		dateTime_1.setBounds(146, 10, 91, 21);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/find.ico"));
		button_2.addSelectionListener(new SelectionAdapter() {   //��ѯ�쳣��Ϣ��¼
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setInput(null);
				
				ExceptionRecordService ers = new ExceptionRecordService();
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
//				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				List<Object> exceptions = null;
				try {
					exceptions = ers.getEcpRecordByPeriod(startTime, endTime);
				} catch (Exception e1) {
					MessageDialogUtil.showWarningMessage(shell, "�쳣��Ϣ��¼��ȡʧ��!");
					return;
				}
				if(exceptions==null){
					MessageDialogUtil.showWarningMessage(shell, "�쳣��Ϣ��¼��ȡʧ��");
					return;
				}
				if(exceptions.size()==0){
					MessageDialogUtil.showInfoMessage(shell, "���쳣��Ϣ��¼!");
					return;
				}
				composite_1.setData(exceptions, sizePage);
			}
		});
		button_2.setBounds(243, 10, 67, 22);
		button_2.setText("\u67E5  \u8BE2");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableViewer.getInput() == null){
					MessageDialogUtil.showWarningMessage(shell, "���޿ɵ�������!");
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
				String title = "�쳣��Ϣ��¼";
				String filename = sdf.format(startTime)+"��"+sdf.format(endTime)+"�쳣��Ϣ��¼";
				ExcelExportAction eea = new ExcelExportAction(shell, filename, filename, composite_1);
				try {
					eea.run();
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialogUtil.showErrorMessage(shell, "�ɷѼ�¼��Ϣ��������!");
					return;
				}
			}
		});
		button.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/excel.ico"));
		button.setBounds(316, 9, 72, 22);
		button.setText("\u5BFC  \u51FA");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u5F02\u5E38\u4FE1\u606F");
		group.setBounds(10, 48, 492, 411);
		
		tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		table.setBounds(10, 20, 472, 349);
		tableViewer.setLabelProvider(new ExceptionLabelProvider());
		tableViewer.setContentProvider(new ExceptionContentProvider());
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(202);
		tableColumn_1.setText("\u5F02\u5E38\u4E8B\u4EF6");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(143);
		tableColumn_2.setText("\u5F02\u5E38\u65F6\u95F4");
		
		composite_1 = new PageComposite(group, SWT.NONE,tableViewer);
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(122);
		tableColumn_3.setText("\u5907\u6CE8");
		composite_1.setBounds(10, 368, 472, 33);
		
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
//		getSite().registerContextMenu(menuMgr, tableViewer);
	}
	/**
	 * ��ʼ��actions
	 */
	public void makeAction(){
		deleteAction = new Action() {
			public void run() {
				int a = MessageDialogUtil.showConfirmMessage(shell, "ȷ��Ҫɾ��ѡ�е��쳣��Ϣô?");
				if(a == SWT.CANCEL) 
					return;
				TableItem[] ti = tableViewer.getTable().getSelection();
				ExceptionRecordService ecpService = new ExceptionRecordService();
				List<Object> removes = new ArrayList<Object>();
				for(int i=0;i<ti.length;i++){
					Object exception = ti[i].getData();
					try {
						ecpService.delExceptionRecord((TExceptionRecord)exception);
						removes.add(exception);
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialogUtil.showErrorMessage(shell, "�쳣��Ϣɾ��ʧ��!");
						return;
					}
				}
				composite_1.updateAfterDelByRemove(removes); //��ҳ������
			}
		};
		deleteAction.setText("ɾ��");
		deleteAction.setToolTipText("ɾ���쳣��Ϣ");
		deleteAction.setImageDescriptor(Activator.getImageDescriptor(IimageKeys.DELETE_POP_MANAGE_TRAY));
	}
}

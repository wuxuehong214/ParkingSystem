package com.rcp.wxh.composite;

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.rcp.wxh.utils.PageUtil;

/**
 * ��ҳ����ģ��
 * @author wuxuehong  2011-11-14
 *
 */
public class PageComposite extends Composite {
	
	private TableViewer tableView; //������
	private List<Object> objs; //��ʾ�б����
	private int sizePage = 20; //ÿҳ��ʾ����Ŀ
	private int curPage = 0; //��ǰ��ʾҳ��
	private int totalPage = 0; //�ܹ�ҳ��
	private int totalRecords = 0; // �ܼ�¼��
	
	private Button button,button_1,button_2,button_3;
	private Label label;
	/**
	 * ���ò���
	 * @param objs
	 */
	public void setData(List<Object> objs, int sizePage){
		this.objs = objs;
		totalRecords = objs.size();
		if(totalRecords == 0) return;//���û�м�¼�򷵻�
		totalPage = totalRecords%sizePage==0? totalRecords/sizePage:totalRecords/sizePage+1;
		curPage = 1;
		this.sizePage = sizePage;
		setButtonStatus(); //��ʼ����ť״̬
	}
	
	/**
	 * ��ʼ��  �������Ϣ���
	 */
	public void init(){
		if(objs == null) return;
		objs.clear();
		curPage = 0;
		totalRecords = objs.size();
		totalPage = 0;
		tableView.setInput(null);
		setButtonStatus();
	}
	
	/**
	 * ��ɾ��������б�
	 */
	public void updateAfterDelByRemove(List<Object> obj){
		if(objs == null) return;
		objs.removeAll(obj);
		totalRecords = objs.size();
		totalPage = totalRecords%sizePage==0? totalRecords/sizePage:totalRecords/sizePage+1;
		if(curPage>totalPage)curPage--;
		setButtonStatus();
	}
	
	/**
	 * ���������������б�
	 * @param obj
	 */
	public void updateAfterDelByAdd(Object obj){
		if(objs == null) return;
		objs.add(0,obj);
		totalRecords = objs.size();
		totalPage = totalRecords%sizePage==0? totalRecords/sizePage:totalRecords/sizePage+1;
		if(curPage>totalPage)curPage--;
		setButtonStatus();
	}

	/**
	 * ���ð�ť״̬
	 */
	private void setButtonStatus(){
		//��ȡ��ҳ���list����
		if(curPage !=0 ){
			List<Object> list = PageUtil.getPageContent(objs, curPage, sizePage);
			tableView.setInput(list);
			tableView.refresh();
		}
		
		//��ҳ
		if(curPage >1 ) button.setEnabled(true);
		else button.setEnabled(false);
		//��һҳ
		if(curPage >1 )button_1.setEnabled(true);
		else button_1.setEnabled(false);
		//��һҳ
		if(curPage<totalPage)button_2.setEnabled(true);
		else button_2.setEnabled(false);
		//βҳ
		if(curPage != totalPage)button_3.setEnabled(true);
		else button_3.setEnabled(false);
		
		label.setText("\u603B\u8BB0\u5F55-"+totalRecords+"-\uFF0C\u5171-"+totalPage+"-\u9875\uFF0C\u5F53\u524D\u7B2C-"+curPage+"-\u9875");
		this.layout();
	}
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PageComposite(Composite parent, int style, TableViewer tableView) {
		super(parent, style);
		this.tableView = tableView;
		GridLayout grid = new GridLayout();
		grid.horizontalSpacing = 0;
		grid.marginHeight = 0;
		setLayout(grid);
		
		Composite c = new Composite(this, SWT.NONE);
		GridLayout gl_c = new GridLayout(5, false);
		gl_c.verticalSpacing = 0;
		gl_c.marginHeight = 0;
		gl_c.horizontalSpacing = 0;
		gl_c.marginHeight = 0;
		gl_c.marginBottom = 0;
		gl_c.marginTop = 0;
		c.setLayout(gl_c);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.grabExcessVerticalSpace = false;
		gd.heightHint = 26;
		gd.horizontalAlignment = SWT.RIGHT;
		c.setLayoutData(gd);
		
		label = new Label(c, SWT.CENTER);
		label.setText("\u603B\u8BB0\u5F55-"+totalRecords+"-\uFF0C\u5171-"+totalPage+"-\u9875\uFF0C\u5F53\u524D\u7B2C-"+curPage+"-\u9875");
		
		button = new Button(c, SWT.CENTER);
		button.addSelectionListener(new SelectionAdapter() {  //��ҳ��Ӧ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(curPage == 1 ) return;
				curPage = 1;
				setButtonStatus();
			}
		});
		button.setEnabled(false);
		button.setText("\u9996\u9875");
		
		button_1 = new Button(c, SWT.CENTER);
		button_1.addSelectionListener(new SelectionAdapter() {  //��һҳ��Ӧ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(curPage-1 < 1)
					return;
				curPage--;
				setButtonStatus();
			}
		});
		button_1.setEnabled(false);
		button_1.setText("\u4E0A\u4E00\u9875");
		
		button_2 = new Button(c, SWT.CENTER);
		button_2.addSelectionListener(new SelectionAdapter() {  //��һҳ��Ӧ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(curPage+1>totalPage)
					return;
				curPage++;
				setButtonStatus();
			}
		});
		button_2.setEnabled(false);
		button_2.setText("\u4E0B\u4E00\u9875");
		
		button_3 = new Button(c, SWT.CENTER); 
		button_3.addSelectionListener(new SelectionAdapter() { //βҳ��Ӧ
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(curPage == totalPage)
					return;
				curPage = totalPage;
				setButtonStatus();
			}
		});
		button_3.setEnabled(false);
		button_3.setText("\u5C3E\u9875");
	}

	public TableViewer getTableView() {
		return tableView;
	}

	public void setTableView(TableViewer tableView) {
		this.tableView = tableView;
	}

	public List<Object> getObjs() {
		return objs;
	}

	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}

	protected void checkSubclass() {
	}

}

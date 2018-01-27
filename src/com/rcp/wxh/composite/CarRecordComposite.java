package com.rcp.wxh.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TableColumn;

import com.rcp.wxh.fortables.TableListener;

/**
 * ���������¼��Ϣ
 * @author Administrator
 *
 */
public class CarRecordComposite extends Composite {
	private TableViewer tableViewer;
	private Table table;
	private int showNum = 20; //��¼��ʾ����  Ĭ��20
	private List<CarEnter> carrecords = null;

	public CarRecordComposite(Composite parent, int style,List<CarEnter> carrecords) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		this.carrecords = carrecords;
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tableViewer.setLabelProvider(new CarLabelProvider());
		tableViewer.setContentProvider(new carContentProvider());
		TableListener.tableCellHeight(table);
		TableListener.tableColor(table);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(92);
		tableColumn.setText("\u5361\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(98);
		tableColumn_1.setText("\u5361\u7C7B\u578B");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(111);
		tableColumn_2.setText("\u8F66\u724C\u53F7\u7801");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(180);
		tableColumn_3.setText("\u5165\u573A\u65F6\u95F4");
		
		tableViewer.setInput(carrecords);
	}
	
	/**
	 * ����������¼
	 */
	public void addCarRecord(CarEnter carenter){
		int size = carrecords.size();
		if(size>showNum)
			carrecords.remove(size-2);
		carrecords.add(0, carenter);
		tableViewer.refresh();
	}
	
	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}



	class CarLabelProvider implements ITableLabelProvider{
		public org.eclipse.swt.graphics.Image getColumnImage(Object element,
				int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			CarEnter  card = (CarEnter) element;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			switch (columnIndex) {
			case 0:  //����
				return card.cardid;
			case 1:  //������
				return card.cartype;
			case 2: //���ƺ���
				return card.carnum;
			case 3:
				return sdf.format(card.date);
			default:
				break;
			}
			return null;
		}
		public void addListener(ILabelProviderListener listener) {
		}
		public void dispose() {
		}
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}
		public void removeListener(ILabelProviderListener listener) {
		}
	}
	class carContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return ((List)inputElement).toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	class CarEnter{
		String cardid;
		String cartype;
		String carnum;
		Date date;
	}

}

package com.rcp.wxh.actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.rcp.wxh.composite.PageComposite;
import com.rcp.wxh.pojo.TCarRecord;
import com.rcp.wxh.pojo.TCard;
import com.rcp.wxh.utils.MessageDialogUtil;

/**
 * excel ���ݵ���
 * @author wuxuehong  2011-12-5
 *
 */
public class ExcelExportAction {
	
	private  List<Object> objs;
	private String title;
	private TableViewer tv; 
	private  Shell shell;
	private String filename;
	private PageComposite page;
	
	public ExcelExportAction(Shell shell,String title, String filename, PageComposite page){
			this.title = title;
			this.shell = shell;
			this.filename = filename;
			this.page = page;
	}
	
	public ExcelExportAction(Shell shell, String title, String filename, TableViewer tv){
		  	this.title = title;
		  	this.shell = shell;
		  	this.filename = filename;
		  	this.tv = tv;
	}
	
	
	
	/**
	 * ������д��excel�ļ�
	 * ���ʹ�õ�һ�����캯��  ��ʹ�ø÷���
	 * object : pageComposote
	 * @throws Exception
	 */
	public void run() throws Exception{
		tv = page.getTableView();  //�ӷ�ҳģ���л�ȡ�����ͼ
		objs = page.getObjs();    //�ӷ�ҳģ���л�ȡ��ǰ���е����ݼ���
		if(tv == null || objs==null){
			MessageDialogUtil.showWarningMessage(shell, "���޿ɵ�������!");
			return;
		}
		//���ļ�ѡ��Ի���
		org.eclipse.swt.widgets.FileDialog fd = new org.eclipse.swt.widgets.FileDialog(shell, SWT.SAVE);
		fd.setFileName(filename);
		fd.setFilterExtensions(new String[]{"*.xls"});
		filename = fd.open();
		if(filename==null || "".equals(filename))return;  //���ȡ���ļ��Ի����򷵻�
		
		WritableWorkbook book = Workbook.createWorkbook(new File(filename));
		WritableSheet sheet = book.createSheet(title, 0);
		WritableFont font = new WritableFont(WritableFont.createFont("����_GB2312"),17,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		//��ͷ
		int count = tv.getTable().getColumnCount();  //����
		TableColumn[] tc = tv.getTable().getColumns();
		for(int i=0;i<count;i++){
			jxl.write.Label label = new jxl.write.Label(i,0,tc[i].getText());
			label.setCellFormat(format);
			sheet.addCell(label);
		}
		
		//��ȡ����е���������
		int size = objs.size();
		ITableLabelProvider lp = (ITableLabelProvider) tv.getLabelProvider();  //����ǩ�ṩ��
		for(int i=0;i<size;i++){          //���� 
			 for(int j=0;j<count;j++){     //����
				 sheet.addCell(new jxl.write.Label(j,i+1,lp.getColumnText(objs.get(i), j)));
			 }
		}
		book.write();
		book.close();
		MessageDialogUtil.showInfoMessage(shell, filename+"�����ɹ�!");
	}
	
	/**
	 * object: tableViewer
	 * ���ʹ�õڶ������캯�� ��ʹ�ø÷�������
	 */
	public void run2()throws Exception{
		if(tv == null || tv.getTable().getItems().length==0){
			MessageDialogUtil.showWarningMessage(shell, "���޿ɵ�������!");
			return;
		}
		
		//���ļ�ѡ��Ի���
		org.eclipse.swt.widgets.FileDialog fd = new org.eclipse.swt.widgets.FileDialog(shell, SWT.SAVE);
		fd.setFileName(filename);
		fd.setFilterExtensions(new String[]{"*.xls"});
		filename = fd.open();
		if(filename==null || "".equals(filename))return;  //���ȡ���ļ��Ի����򷵻�
		
		WritableWorkbook book = Workbook.createWorkbook(new File(filename));
		WritableSheet sheet = book.createSheet(title, 0);
		WritableFont font = new WritableFont(WritableFont.createFont("����_GB2312"),17,WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		//��ͷ
		int count = tv.getTable().getColumnCount();  //����
		TableColumn[] tc = tv.getTable().getColumns();
		for(int i=0;i<count;i++){
			jxl.write.Label label = new jxl.write.Label(i,0,tc[i].getText());
			label.setCellFormat(format);
			sheet.addCell(label);
		}
		
		TableItem[] ti = tv.getTable().getItems();
		TableItem tii = null;
		//��ȡ����е���������
		for(int i=0;i<ti.length;i++){  //����
			tii = ti[i];
			for(int j=0;j<count;j++){     //����
				 sheet.addCell(new jxl.write.Label(j,i+1,tii.getText(j)));
			 }
		}
		book.write();
		book.close();
		MessageDialogUtil.showInfoMessage(shell, filename+"�����ɹ�!");
	}
	

}

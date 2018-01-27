package com.rcp.wxh.fortables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

import com.swtdesigner.SWTResourceManager;

public class TableListener {//���������ͳһ��ĸ�ʽ���õ�
	
	public static void tableCellHeight(final Table table) {//���еĸ߶ȣ����б��ͳһ�趨
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				 event.height = 20;
			}
        });
	}
	
	public static void tableColor(final Table table){  //ͳһ������ɫ
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	}

}

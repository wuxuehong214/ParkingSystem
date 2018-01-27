package com.rcp.wxh.editors;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import com.rcp.wbw.demo.ChartComposite;
import com.rcp.wbw.demo.CopyOfParkingSystemChartZhu_test;
import com.rcp.wbw.demo.ParkingSystemChartQu;
import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.dao.impl.TCarRecordDao;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import com.swtdesigner.ResourceManager;



/**
 * 
 * ͳ�ƹ���༭��
 * @author wuxuehong  2011-11-2
 *
 */
public class StatisticEditor extends EditorPart {
	public StatisticEditor() {
	}
	
	public static final String ID = StatisticEditor.class.getName();
	private String timefrom;
	private String timeto;
	private Composite composite_1;

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
		parent.setBackgroundImage(LookAndFeel.getDefault().getContentBgImage());
		parent.setBackgroundMode(SWT.INHERIT_FORCE);
		parent.pack();
		parent.setLayout(new GridLayout(1, true));
		
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.add("����ͳ��",0);
		combo.add("����ͳ��",1);
		combo.select(0);	
	
		final DateTime dateTime_from = new DateTime(composite, SWT.BORDER);
		Label label_2 = new Label(composite, SWT.CENTER);
		label_2.setFont(SWTResourceManager.getFont("����", 12, SWT.NORMAL));
		label_2.setLayoutData(new RowData(14, 18));
		label_2.setText("\u2014");
		
		final DateTime dateTime_to = new DateTime(composite, SWT.BORDER);
		
		combo.addSelectionListener(new SelectionAdapter() {//COMBO��ѡ����������ͳ�� ��0��ʱ����������
			//����ͳ��ʱ��1����������
			@Override
			public void widgetSelected(SelectionEvent e) {
					timefrom =dateTime_from.getYear()+"-"+(dateTime_from.getMonth()+1)+"-"+dateTime_from.getDay();
					timeto =dateTime_to.getYear()+"-"+(dateTime_to.getMonth()+1)+"-"+dateTime_to.getDay();
				
				
			}
		});
		
		Button button_area_qu = new Button(composite, SWT.NONE);
		button_area_qu.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/diagram.ico"));
		button_area_qu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(composite_1.getChildren().length!=0){
						composite_1.getChildren()[0].dispose();
						composite_1.layout();
					}
					Object[][] c;
					Object[][] d;
			    	TCarRecordDao tdao = new TCarRecordDao();
			    	java.sql.Date datefrom; 
			    	java.sql.Date dateto;	    
			    	int co_se = combo.getSelectionIndex();
			    	//ȡʱ���String
			    	timefrom =dateTime_from.getYear()+"-"+(dateTime_from.getMonth()+1)+"-"+dateTime_from.getDay();
					timeto =dateTime_to.getYear()+"-"+(dateTime_to.getMonth()+1)+"-"+dateTime_to.getDay();
			
			    	if(timefrom == null | timefrom == "" | timeto == null | timeto == "")
			    		MessageDialog.openInformation(parent.getShell(), "�Բ���",
						"��ѡ��ʱ��Σ�");
			    	else{
			    		//ȡ�����ڶ���������
				        datefrom = java.sql.Date.valueOf(timefrom); 
				        dateto= java.sql.Date.valueOf(timeto);
				        double daycount_r = ( dateto.getTime()-datefrom.getTime()) / (24 * 60 * 60 * 1000);   //���ڼ��
				        if(daycount_r<0 )
				        	MessageDialog.openInformation(parent.getShell(), "�Բ���",
				        		"����ʱ��С�ڿ�ʼʱ�䣬������ѡ��");
				        else if(co_se == 0 & daycount_r  >45){//����ͳ����ʱ��δ�����
				        	MessageDialog.openInformation(parent.getShell(), "�Բ���",
								"ʱ���ѡ�������������ѡ��");
				        }else{
				        	//����Ϊ�յĴ���	���ǵô���	
				        		        	
							ParkingSystemChartQu parkingsystemchartqu = new ParkingSystemChartQu("ʱ����ڵ�����ͼ",timefrom,timeto,co_se);
							JFreeChart chart;						
							XYDataset dataset = parkingsystemchartqu.createDataset();						
							chart = parkingsystemchartqu.createChartQu(dataset);					
							//JFreeChart chart = createChart(createDataset());
							ChartComposite frame = new ChartComposite(composite_1, SWT.NONE, chart,
				             true);						
							FillLayout fillLayout = new FillLayout();
							fillLayout.type = SWT.VERTICAL;
							System.out.println("55");
							frame.setLayout(fillLayout);
							frame.setSize(670, 460);
							frame.setDisplayToolTips(true);
							frame.setHorizontalAxisTrace(false);
							frame.setVerticalAxisTrace(false);
							composite_1.layout();
				        }
			    	}
			    	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_area_qu.setText("\u66F2\u7EBF\u56FE\u7EDF\u8BA1");
		
		Button button_area_zhu = new Button(composite, SWT.NONE); //ʱ�������״ͼ��ť
		button_area_zhu.setImage(ResourceManager.getPluginImage("ParkingSystem", "icons/column.ico"));
		button_area_zhu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				try {
					if(composite_1.getChildren().length!=0){
						composite_1.getChildren()[0].dispose();
						composite_1.layout();
					}
					Object[][] c;
					Object[][] d;
					//TCarRecordDao tdao = new TCarRecordDao();
			    	java.sql.Date datefrom; 
			    	java.sql.Date dateto;	    
			    	int co_se = combo.getSelectionIndex();
			    	//ȡʱ���String
			    	timefrom =dateTime_from.getYear()+"-"+(dateTime_from.getMonth()+1)+"-"+dateTime_from.getDay();
					timeto =dateTime_to.getYear()+"-"+(dateTime_to.getMonth()+1)+"-"+dateTime_to.getDay();
			
			    	if(timefrom == null | timefrom == "" | timeto == null | timeto == "")
			    		MessageDialog.openInformation(parent.getShell(), "�Բ���",
						"��ѡ��ʱ��Σ�");
			    	else{
			    		//ȡ�����ڶ���������
				        datefrom = java.sql.Date.valueOf(timefrom); 
				        dateto= java.sql.Date.valueOf(timeto);
				        double daycount_r = ( dateto.getTime()-datefrom.getTime()) / (24 * 60 * 60 * 1000);   //���ڼ��			       
				        if(daycount_r<0 )
				        	MessageDialog.openInformation(parent.getShell(), "�Բ���",
				        		"����ʱ��С�ڿ�ʼʱ�䣬������ѡ��");
				        else if(co_se == 0 & daycount_r  >45){//����ͳ����ʱ��δ���45��
				        	MessageDialog.openInformation(parent.getShell(), "�Բ���",
								"ʱ���ѡ�������������ѡ��");
				        }else{
				        	CopyOfParkingSystemChartZhu_test parkingsystemchart = new CopyOfParkingSystemChartZhu_test("ʱ����ڵ�����ͼ",timefrom,timeto,co_se);
							JFreeChart chart;
							//createChartZhu(createDatasetBar())
							chart = parkingsystemchart.createChartZhu(parkingsystemchart.createDatasetBar());
							//JFreeChart chart = createChart(createDataset());
							ChartComposite frame = new ChartComposite(composite_1, SWT.NONE, chart,
				             true);
							FillLayout fillLayout = new FillLayout();
							fillLayout.type = SWT.VERTICAL;
							frame.setLayout(fillLayout);
							frame.setSize(670, 460);
							frame.setDisplayToolTips(true);
							frame.setHorizontalAxisTrace(false);
							frame.setVerticalAxisTrace(false);
							composite_1.layout();
					}
			    	}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		button_area_zhu.setBounds(361, 6, 72, 22);
		button_area_zhu.setText("\u67F1\u72B6\u56FE\u7EDF\u8BA1");
		composite_1 = new Composite(parent, SWT.BORDER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(GridData.FILL_BOTH));

	}
      

	public void setFocus() {
	}
}

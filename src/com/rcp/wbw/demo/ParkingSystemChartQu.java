package com.rcp.wbw.demo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import com.rcp.wxh.dao.impl.TCarRecordDao;

public class ParkingSystemChartQu extends ApplicationFrame{
	private static String timefrom;
	private static String timeto;
	private static int type;
	private static int highValue_Y;
	private static int minValue_Y;
	private static Date min,max;
	private static double daycount;
	//��Ϊ���ǳ��볡����ͼ�����Դ����typeû���õ�,���������type����˼�ǰ���ͳ�ƣ�0�����ǰ���ͳ�ƣ�1��
    public ParkingSystemChartQu(String title,String timefrom ,String timeto,int type) {
        super(title);
        this.timefrom = timefrom;
        this.timeto = timeto;
        this.type = type;
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    @SuppressWarnings("deprecation")
	public static JFreeChart createChartQu(XYDataset dataset) {
    	//��ʼʱ���д�������ʹ������Ŀ�ʼ����ʱ�䲻һ�����������ݵĿ�ʼ����ʱ��
    	//String f = (min.getYear()+1900)+"��"+(min.getMonth()+1)+"��"+min.getDay()+"��";
    	//String t = (max.getYear()+1900)+"��"+(max.getMonth()+1)+"��"+max.getDay()+"��";
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "ʱ����ڳ�������ͳ��ͼ",  // title
            "�������ڴ�"+min+"��"+max,             // x-axis label
            "������������",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setFont(new Font("����", Font.BOLD, 15));
        XYPlot plot = (XYPlot) chart.getPlot();
       
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));//�趨��������ͼ��������ʾ���־���
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYLineAndShapeRenderer xylinerenderer=(XYLineAndShapeRenderer)plot.getRenderer();
        //���·ֱ��ͬһ������ͼ��3������������ɫ,0Ϊ��һ��1Ϊ�ڶ���,......
        xylinerenderer.setSeriesPaint(0, Color.RED);
        xylinerenderer.setSeriesPaint(1, Color.black);
        plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 15));
        //�������ı�������
        //plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT, true);
        chart.getLegend().setItemFont(new Font("����", Font.ITALIC, 10));
        //�����б�����
        plot.getDomainAxis().setTickLabelFont(new Font("������", 1, 10));
        //����С��������
        plot.getDomainAxis().setLabelFont(new Font("������", 1, 10));
        plot.setNoDataMessage("û������"); 
                
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true); //���ݵ�ɼ�
            renderer.setBaseShapesFilled(true);//���ݵ���ʵ�ĵ�
            renderer.setDrawSeriesLineAsPath(true);
            renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));//���ݵ���Բ��
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        // domainAxis.setUpperMargin(0.05); // ���þ���ͼƬ��˾���
        // domainAxis.setLowerMargin(0.05); // ���þ���ͼƬ�Ҷ˾���
        if(type == 0)    //type = 0����1��Ϊ������С���꣬Ϊ1����1��Ϊ������С����
        {
        	axis.setDateFormatOverride(new SimpleDateFormat("dd"));//����ʱ���ʽ
        	axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY,1));
        }else{
        	axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM"));
        	axis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH,1));
        }
        axis.setLabelFont(new Font("����", Font.TRUETYPE_FONT, 12));
        axis.setAutoTickUnitSelection(false);//ʱ��������ݱ�ǩ�Ƿ��Զ�ȷ
        //������һ���������������ʱ�����ģ����ܻ���֣�����ʱ���Լ��账���ھ�����
        int a =1;
        a = (int) (daycount/10)+1;
        if(a == 0) a =1;
        //axis.setVerticalTickLabels(true);    
        NumberAxis numberaxis = (NumberAxis)plot.getRangeAxis();
        numberaxis.setAxisLineVisible(false);//�Ƿ���ʾ������
        numberaxis.setTickMarksVisible(false);//�Ƿ���ʾ������
        numberaxis.setAutoRangeIncludesZero(false);// �Ƿ��Զ�����0���? Ĭ��Ϊtrue 
        numberaxis.setAutoTickUnitSelection(false);//����������ݱ�ǩ�Ƿ��Զ�ȷ��
        //  numberaxis.setRange(2.000D,4.400D); 
        numberaxis.setTickUnit(new NumberTickUnit(1D));
        int space_y =(int)((highValue_Y*1.1-minValue_Y*0.9)/10);
        if(space_y == 0) space_y =1;
        System.out.println("spacespace sapce space"+space_y);
        numberaxis.setTickUnit(new NumberTickUnit(space_y));// ���ÿ̶���ʾ���ܶ�
        numberaxis.setAutoRangeMinimumSize(2D);      
        return chart;

    }
   
    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    public static XYDataset createDataset() throws Exception{

    	Object[][] chart = null;
    	Object[][] chart_qu = null;
    	TCarRecordDao tdao = new TCarRecordDao();
    	TimeSeries s1,s2;
    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	try {
    		if(type == 0){
    			//��DAO�������ݣ�����Ϊͳ�Ƶ�λ������������Ϊ��λ
	    		chart = tdao.getCarRecodebyTimearea_day(timefrom,timeto,1);
	    		chart_qu = tdao.getCarRecodebyTimearea_day(timefrom,timeto,0);
	    		 s1 = new TimeSeries("�볡����",Day.class);
	    		 s2 = new TimeSeries("��������",Day.class);
    		}else
    		{
    			
    			  //����DAO������������£����������µĵ�λ
    			  java.sql.Date date = java.sql.Date.valueOf(timefrom);
    			  java.sql.Date date_t = java.sql.Date.valueOf(timeto); 
    			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
    			  String newdate_from = sdf.format(date) ;
    			  String timefrom_temp = newdate_from.substring(0, 7);
    			  //System.out.println("jieȡ���ʱ�䣺"+timefrom);
    			  String newdate_to = sdf.format(date_t);
    			  String timeto_temp = newdate_to.substring(0,7);//��ȡ���ʱ���ʽΪ��2010-05����2010-11��
    		      //System.out.println(timefrom+"�ĺ�" +""+timeto);
    		      chart = tdao.getCarRecodebyTimearea_month(timefrom_temp,timeto_temp,1);
    		      chart_qu = tdao.getCarRecodebyTimearea_month(timefrom_temp,timeto_temp,0);
    		      s1 = new TimeSeries("�볡����",Month.class);
    		      s2 = new TimeSeries("��������",Month.class);
    		}
    	
    		
    		s1.clear();
    		Date d ,min_r= null,min_c = null,max_r= null,max_c= null;
    		double h;
    		if( chart != null && chart.length > 0){	//���ݲ�Ϊ�յĴ���
	    		min_r = (Date) chart[0][1];
	    		max_r = (Date) chart[0][1];
	    		for(int i =0;i<chart.length;i++)
	    		{
					 d = (Date) chart[i][1]; 
					 if(d.before(min_r)) min_r = d;				 
					 if(d.after(max_r)) max_r = d;
					 h =   new Double( chart[i][0].toString());
					 //������ݣ�0������Ϊ��λ��1������Ϊ��λ
					 if(type == 0)
						 s1.add(new Day(d.getDate(),(d.getMonth()+1),(d.getYear()+1900)),h);
					 else
						 s1.add(new Month((d.getMonth()+1),(d.getYear()+1900)),h);
	    		}
	    		  dataset.addSeries(s1);
    		}
    		else
    			s1 = null;

    		s2.clear();  		
    		if(chart_qu != null && chart_qu.length>0){
    			min_c = (Date) chart_qu[0][1];
    			max_c = (Date) chart_qu[0][1];
	    		for(int i =0;i<chart_qu.length;i++)
	    		{
					 d = (Date) chart_qu[i][1];
					 if(d.before(min_c)) min_c = d;
					 if(d.after(max_c)) max_c = d;
					 h =   new Double( chart_qu[i][0].toString());
					 if(type == 0)
						 s2.add(new Day(d.getDate(),(d.getMonth()+1),(d.getYear()+1900)),h);
					 else
						 s2.add(new Month((d.getMonth()+1),(d.getYear()+1900)),h);
	    		}
	    		  dataset.addSeries(s2);
    		}
    		else
    			s2 = null;

    		//ȡ�������ݵ�ʱ��εĿ�ʼ�ͽ���
    		if(min_c != null && min_r !=null )
    			min = min_c.before(min_r)?min_c:min_r;
    		else if(min_c != null) min = min_c;
    		else if(min_r != null) min = min_r;
    		else min = null ;
    		
    		if(max_c != null && max_r !=null )
    			max = max_c.before(max_r)?max_r:max_c;
    		else if(max_c != null) max = max_c;
    		else if(max_r != null) max = max_r;
    		else max = null ;
    		

	        int seriesCount = dataset.getSeriesCount();// һ���ж��ٸ����У�ĿǰΪһ��
	        for (int i = 0; i < seriesCount; i++) {
	        	int itemCount = dataset.getItemCount(i);// ÿһ�������ж��ٸ�������
	        	for (int j = 0; j < itemCount; j++) {
	        		if (highValue_Y < dataset.getYValue(i, j)) {// ȡ��i�������еĵ�j������������ֵ
	        			highValue_Y = (int) dataset.getYValue(i, j);
	        		}
	        		if (minValue_Y > dataset.getYValue(i, j)) {// ȡ��i�������еĵ�j�����������Сֵ
	        			minValue_Y = (int) dataset.getYValue(i, j);
	        		}
	        	}
	        }
	        return dataset;
        
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		return dataset;
    	}	
    }
       
    public static JPanel createDemoPanel() {
        JFreeChart chart;
		ChartPanel panel = null;
		try {
			chart = createChartQu(createDataset());
	        panel = new ChartPanel(chart);
	        panel.setFillZoomRectangle(true);
	        panel.setMouseWheelEnabled(true);
	        return panel;
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return panel;
		}
       
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
    	ParkingSystemChartQu demo = new ParkingSystemChartQu(
                "Time Series Chart Demo 1","2011-10-25","2011-12-25",0);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}

package com.rcp.wxh.composite;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;

import com.lti.civil.CaptureException;
import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.swt.CaptureControl;
import com.lti.civil.swt.CaptureControlListener;
import com.lti.civil.swt.DefaultCaptureControlListener;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.video.VideoControlFactory;

import parkingsystem.Activator;

/**
 * 视频显示模块
 * @author Administrator
 *
 */
public class VideoComposite extends Composite {
	
	private Canvas canvas;
	private CaptureControl video;

	public VideoComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		canvas = new Canvas(this, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				gc.drawImage(Activator.getImageDescriptor(IimageKeys.VIDEO_MANAGE_TRAY).createImage(), 0, 0);
				org.eclipse.swt.graphics.Image image = Activator.getImageDescriptor("icons/video.png").createImage();
				gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
			}
		});
	}
	
	public void dispose() {
		super.dispose();
		System.out.println("Video composite dispose!");
	}
	/**
	 * 显示视频
	 */
	public void showVideo(){
		 canvas.dispose();
		 CaptureSystemFactory factory = VideoControlFactory.getCaptureSystemFacotry();
		 video = new CaptureControl(this, 0, factory,0);
		 CaptureControlListener listener = new DefaultCaptureControlListener();
		 video.setListener(listener);
		 this.layout();
	}
	
	/**
	 * 关闭视频
	 */
	public void closeVideo(){
		if(video != null){
			 try {
				 video.dispose();
				 video.doDispose();
			} catch (CaptureException e1) {
				e1.printStackTrace();
			}
			canvas = new Canvas(this, SWT.DOUBLE_BUFFERED|SWT.EMBEDDED);
			canvas.addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent e) {
					GC gc = e.gc;
					gc.drawImage(Activator.getImageDescriptor(IimageKeys.VIDEO_MANAGE_TRAY).createImage(), 0, 0);
					org.eclipse.swt.graphics.Image image = Activator.getImageDescriptor("icons/video.png").createImage();
					gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, canvas.getBounds().width, canvas.getBounds().height);
				}
			});
			this.layout();
			video = null;
		 }
	}

	/**
	 * 抓取图片
	 * @param times
	 * @return
	 */
	public boolean captureImage(long times){
		boolean r = video.saveImage("images/"+times+".jpg");
		return r;
	}
	
	/**
	 * 设置视频是否翻转
	 * @param t
	 */
	public void setImageTurnOver(boolean t){
		video.setImageOverturn(t);
	}
}

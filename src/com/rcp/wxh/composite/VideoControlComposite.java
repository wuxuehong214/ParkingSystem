package com.rcp.wxh.composite;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 视频控制模块
 * @author wuxuehong
 *
 * 2012-1-19
 */
public class VideoControlComposite extends Composite {
	
	//所需要控制的视频模块
	private VideoComposite video;

	public VideoControlComposite(Composite parent, int style, VideoComposite vc) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		video = vc;
		final Button button = new Button(this, SWT.CHECK);         //开启视频
		final Button button_1 = new Button(this, SWT.CHECK);       //视频翻转
		button_1.setEnabled(false);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(video == null) return;
				if(button.getSelection()){
					video.showVideo();
					button_1.setEnabled(true);
				}else{
					video.closeVideo();
					button_1.setSelection(false);
					button_1.setEnabled(false);
				}
			}
		});
		button.setText("\u5F00\u542F\u89C6\u9891");
		
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(video == null) return;
				if(button_1.getSelection()){
					video.setImageTurnOver(true);
				}else
					video.setImageTurnOver(false);
			}
		});
		button_1.setText("\u89C6\u9891\u7FFB\u8F6C");
	}

}

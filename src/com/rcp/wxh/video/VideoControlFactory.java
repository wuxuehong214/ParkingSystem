package com.rcp.wxh.video;

import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;

/**
 * ��Ƶ���ƹ���
 * @author Administrator
 *
 */
public class VideoControlFactory {
	
	/**
	 * ��ȡ��Ƶ���񹤳�
	 * @return
	 */
	public static CaptureSystemFactory getCaptureSystemFacotry(){
		return DefaultCaptureSystemFactorySingleton.instance();
	}

}

package com.rcp.wxh.video;

import com.lti.civil.CaptureSystemFactory;
import com.lti.civil.DefaultCaptureSystemFactorySingleton;

/**
 * 视频控制工厂
 * @author Administrator
 *
 */
public class VideoControlFactory {
	
	/**
	 * 获取视频捕获工厂
	 * @return
	 */
	public static CaptureSystemFactory getCaptureSystemFacotry(){
		return DefaultCaptureSystemFactorySingleton.instance();
	}

}

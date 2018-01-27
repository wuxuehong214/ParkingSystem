package com.rcp.wxh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/**
 * ���øù����� ȷ��ϵͳΨһʵ�� ������ϵͳֻ������һ�Ρ�
 * 
 * @author wuxuehong 2011-11-3
 * 
 */
public class InstanceControlUtil {

	FileLock lock = null;

	// �жϸ�Ӧ���Ƿ�������
	public boolean isRunning() {
		try {
			// ���ʵ����־�ļ�
			File flagFile = new File("instance");
			// ��������ھ��½�һ��
			if (!flagFile.exists())
				flagFile.createNewFile();
			// ����ļ���
			lock = new FileOutputStream("instance").getChannel().tryLock();
			// ���ؿձ�ʾ�ļ��ѱ����е�ʵ������
			if (lock == null)
				return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}

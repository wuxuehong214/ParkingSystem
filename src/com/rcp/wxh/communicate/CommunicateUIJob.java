package com.rcp.wxh.communicate;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.progress.UIJob;

import parkingsystem.Activator;

/**
 * ���ڸ���UI�����job����
 * ��Ҫ�������ն�ͨ�ŵ�ʱ�� ����ȡ������Ϣͬ��������
 * @author wuxuehong  2011-11-20
 *
 */
public class CommunicateUIJob extends UIJob {

	public CommunicateUIJob(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
		return null;
	}

}

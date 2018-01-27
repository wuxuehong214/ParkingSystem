package parkingsystem;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.rcp.wxh.dialogs.LoginDialog;
import com.rcp.wxh.event.CarParkListener;
import com.rcp.wxh.event.ExceptionListener;
import com.rcp.wxh.event.ExpenseListener;
import com.rcp.wxh.event.Notifier;
import com.rcp.wxh.event.SystemOperationListener;
import com.rcp.wxh.utils.InstanceControlUtil;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		//����¼�������
		CarParkListener parkListener = new CarParkListener();
		ExceptionListener exceptionListener = new ExceptionListener();
		ExpenseListener expenseListener = new ExpenseListener();
		SystemOperationListener systemListener = new SystemOperationListener();
		Notifier.getInstance().addListener(parkListener);
		Notifier.getInstance().addListener(exceptionListener);
		Notifier.getInstance().addListener(expenseListener);
		Notifier.getInstance().addListener(systemListener);
		
		//����ǰ���жϸ�ϵͳ�Ƿ��Ѿ����� ������Ѿ��������˳���
		InstanceControlUtil icu = new InstanceControlUtil();
		if(icu.isRunning()){
			MessageDialog.openWarning(new Shell(), "��ʾ", "ϵͳ�Ѿ���������!");
			return IApplication.EXIT_OK;
		}
		
		//��ϵͳ����̨����ǰ �򿪵�¼����
		LoginDialog loginDialog = new LoginDialog(new Shell(),SWT.NONE);
		int R = (Integer)loginDialog.open();
		//���ȡ����¼�� 
		if(R!=1){
			return IApplication.EXIT_OK;  //�˳�ϵͳ
		}
		
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}

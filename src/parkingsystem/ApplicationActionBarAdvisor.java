package parkingsystem;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;

import com.rcp.wxh.actions.EnterManageAction;
import com.rcp.wxh.actions.LeaveManageAction;
import com.rcp.wxh.actions.ValidateStatusAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction; //ϵͳ�˳���Ӧ
	private IWorkbenchAction passwordManageAction; //�������
	private IWorkbenchAction loginoutManageAction; //ע����½
	
    private IWorkbenchAction loginAction;
    private IWorkbenchAction enterManageAction; //�����볡�����Ӧ
    private IWorkbenchAction leaveManageAction; //�������������Ӧ
    private IWorkbenchAction enterExitManageAction; //�������볡���
    private IWorkbenchAction statisticManageAction; //����ͳ����Ӧ
    private IWorkbenchAction empManageAction; //Ա����Ϣ����
    private IWorkbenchAction cardManageAction; //��Ƭ������Ӧ
    private IWorkbenchAction systemManageAction; //ϵͳ������Ӧ
    private IWorkbenchAction exceptionManageAction; //�쳣��¼������Ӧ
    private IWorkbenchAction expenseManageAction; //���ü�¼������Ӧ
    private IWorkbenchAction carRecordManageAction; //���������¼������Ӧ
    private IWorkbenchAction carEnterManageAction; //��ǰͣ��������Ϣ��ѯ��Ӧ
    
    private IWorkbenchAction aboutAction; //���ڶԻ���
   
    private static ApplicationActionBarAdvisor instance;
    private static IActionBarConfigurer configure;
    private static IMenuManager menubar;
    
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		this.configure = configurer;
	}

	protected void makeActions(IWorkbenchWindow window) {
		try{
			passwordManageAction = ApplicationActionFactory.PASSWORD_MANAGE.create(window);
			register(passwordManageAction);
			loginoutManageAction = ApplicationActionFactory.LOGINOUT_MANAGE.create(window);
			register(loginoutManageAction);
			loginAction = ApplicationActionFactory.LOGIN_WINDOW.create(window);
			register(loginAction);
			enterManageAction = ApplicationActionFactory.ENTER_MANAGE.create(window);
			register(enterManageAction);
			leaveManageAction = ApplicationActionFactory.LEAVE_MANAGE.create(window);
			register(leaveManageAction);
			enterExitManageAction = ApplicationActionFactory.ENTER_EXIT_MANAGE.create(window);
			register(enterExitManageAction);
			statisticManageAction = ApplicationActionFactory.STATISTIC_MANAGE.create(window);
			register(statisticManageAction);
			empManageAction = ApplicationActionFactory.EMP_MANAGE.create(window);
			register(empManageAction);
			cardManageAction = ApplicationActionFactory.CARD_MANAGE.create(window);
			register(cardManageAction);
			systemManageAction = ApplicationActionFactory.SYSTEM_MANAGE.create(window);
			register(systemManageAction);
			exceptionManageAction = ApplicationActionFactory.EXCEPTION_MANAGE.create(window);
			register(exceptionManageAction);
			expenseManageAction = ApplicationActionFactory.EXPENSE_MANAGE.create(window);
			register(expenseManageAction);
			carRecordManageAction = ApplicationActionFactory.CARRECORD_MANAGE.create(window);
			register(carRecordManageAction);
			carEnterManageAction = ApplicationActionFactory.CARENTER_MANAGE.create(window);
			register(carEnterManageAction);
			aboutAction = ApplicationActionFactory.ABOUT.create(window);
			register(aboutAction);
			aboutAction.setText("����...");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * �˵�����ʼ��
	 */
	protected void fillMenuBar(IMenuManager menuBar) {
		try{
			
			MenuManager personalMenu = new MenuManager("������Ϣ����(P)", "personal");
			menuBar.add(personalMenu);
			personalMenu.add(passwordManageAction);
			personalMenu.add(loginoutManageAction);
			
			MenuManager carMenu = new MenuManager("ͣ������(C)", "parking");
			menuBar.add(carMenu);
			carMenu.add(enterManageAction);
			carMenu.add(leaveManageAction);
			carMenu.add(enterExitManageAction);
			
			MenuManager recordMenu = new MenuManager("��Ϣ��¼����(C)", "parking");
			menuBar.add(recordMenu);
			recordMenu.add(carEnterManageAction);
			recordMenu.add(carRecordManageAction);
			recordMenu.add(expenseManageAction);
			recordMenu.add(exceptionManageAction);
			recordMenu.add(statisticManageAction);
			
			MenuManager sysMenu = new MenuManager("ϵͳ����(C)", "parking");
			menuBar.add(sysMenu);
			sysMenu.add(cardManageAction);
			sysMenu.add(empManageAction);
			sysMenu.add(systemManageAction);
			
			MenuManager helpMenu = new MenuManager("����(H)", IWorkbenchActionConstants.M_HELP);
			menuBar.add(helpMenu);
			// Help
			//helpMenu.add(loginAction);
			helpMenu.add(aboutAction);
			menubar = menuBar;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������ʼ��
	 */
	protected void fillCoolBar(ICoolBarManager coolBar) {
		try{
			IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());
			coolBar.add(toolbar);
			toolbar.add(enterManageAction);
			toolbar.add(leaveManageAction);
			toolbar.add(enterExitManageAction);
//			toolbar.add(passwordManageAction);
			
//			IToolBarManager toolbar4 = new ToolBarManager(coolBar.getStyle()); //��Ϣ��ѯ����
//			coolBar.add(toolbar4);
//			toolbar4.add(exceptionManageAction);
//			toolbar4.add(expenseManageAction);
//			toolbar4.add(carRecordManageAction);
//			toolbar4.add(statisticManageAction);
//			
//			IToolBarManager toolbar3 = new ToolBarManager(coolBar.getStyle());
//			coolBar.add(toolbar3);
//			toolbar3.add(empManageAction);
//			toolbar3.add(cardManageAction);
//			toolbar3.add(systemManageAction);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//��ʼ��״̬��
	protected void fillStatusLine(IStatusLineManager statusLine) {
		super.fillStatusLine(statusLine);
		StatusLineContributionItem statusItem = new StatusLineContributionItem("msg");
		statusLine.getProgressMonitor();
		statusItem.setText("��ǰ��¼:"+ValidateStatusAction.user.getOperatorname());
		statusLine.add(statusItem);
		statusLine.setMessage("����ʲô��Ϣ�� ��");
	}

	public static IActionBarConfigurer getInstance(){
		return configure;
	}
	
	public static ApplicationActionBarAdvisor getDefault() {//����ʵ��
		return instance;
	}

	public static IMenuManager getMenubar() {//���ز˵�������
		return menubar;
	}
}

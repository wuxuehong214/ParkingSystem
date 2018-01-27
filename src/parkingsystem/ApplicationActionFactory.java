package parkingsystem;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.rcp.wxh.actions.CarEnterManageAction;
import com.rcp.wxh.actions.CarRecordManageAction;
import com.rcp.wxh.actions.CardManageAction;
import com.rcp.wxh.actions.EmpManageAction;
import com.rcp.wxh.actions.EnterExitManageAction;
import com.rcp.wxh.actions.EnterManageAction;
import com.rcp.wxh.actions.ExceptionManageAction;
import com.rcp.wxh.actions.ExpenseManageAction;
import com.rcp.wxh.actions.LeaveManageAction;
import com.rcp.wxh.actions.LoginAction;
import com.rcp.wxh.actions.LoginOutAction;
import com.rcp.wxh.actions.PasswordResetAction;
import com.rcp.wxh.actions.PersonalManageAction;
import com.rcp.wxh.actions.StatisticManageAction;
import com.rcp.wxh.actions.SystemManageAction;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.resource.Messages;

/**
 * Action����
 * ���ڴ������е�action��Ӧ���
 * @author wuxuehong
 *
 */
public abstract class ApplicationActionFactory extends ActionFactory {

	protected ApplicationActionFactory(String actionId) {
		super(actionId);
	}
	
	/**
	 * ��¼��Ӧ���
	 */
	public static final ActionFactory LOGIN_WINDOW = new ActionFactory(LoginAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new LoginAction(window,Messages.LOGIN_LABEL);
			return action;
		}
	};
	/**
	 * �����޸���Ӧ
	 */
	public static final ActionFactory PASSWORD_MANAGE = new ActionFactory(PasswordResetAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new PasswordResetAction(window,Messages.PASSWORD_MANAGE_LABEL,IimageKeys.PASSWORD_MANAGE_TRAY	);
			return action;
		}
	};
	
	/**
	 * ע����¼��Ӧ
	 */
	public static final ActionFactory LOGINOUT_MANAGE = new ActionFactory(LoginOutAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new LoginOutAction(window,Messages.LOGINOUT_MANAGE_LABEL,	IimageKeys.LOGINOUT_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * �鿴��ǰͣ����Ϣ��Ӧ
	 */
	public static final ActionFactory CARENTER_MANAGE = new ActionFactory(CarEnterManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new CarEnterManageAction(window,Messages.CARENTER_MANAGE_LABEL,	IimageKeys.ENTER_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * �����볡������Ӧ���
	 */
	public static final ActionFactory ENTER_MANAGE = new ActionFactory(EnterManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new EnterManageAction(window,Messages.ENTER_MANAGE_LABEL,IimageKeys.ENTER_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ��������������Ӧ���
	 */
	public static final ActionFactory LEAVE_MANAGE = new ActionFactory(LeaveManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new LeaveManageAction(window,Messages.LEAVE_MANAGE_LABEL,IimageKeys.LEAVE_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * �������볡������Ӧ���
	 */
	public static final ActionFactory ENTER_EXIT_MANAGE = new ActionFactory(EnterExitManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new EnterExitManageAction(window,Messages.ENTER_EXIT_MANAGE_LABEL,IimageKeys.LEAVE_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ��������ͳ��
	 */
	public static final ActionFactory STATISTIC_MANAGE = new ActionFactory(StatisticManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new StatisticManageAction(window,Messages.STATISTIC_MANAGE_LABEL,IimageKeys.STATISTIC_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ������Ϣ����
	 */
	public static final ActionFactory PERSONAL_MANAGE = new ActionFactory(PersonalManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new StatisticManageAction(window,Messages.PERSONAL_MANAGE_LABEL,IimageKeys.PERSONAL_MANAGE_TRAY);
			return action;
		}
	};

	/**
	 * ��Ƭ��Ϣ����
	 */
	public static final ActionFactory CARD_MANAGE = new ActionFactory(CardManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new CardManageAction(window,Messages.CARD_MANAGE_LABEL,IimageKeys.CARD_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ϵͳ��Ϣ����
	 */
	public static final ActionFactory SYSTEM_MANAGE = new ActionFactory(SystemManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new SystemManageAction(window,Messages.SYSTEM_MANAGE_LABEL,IimageKeys.SYSTEM_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * �쳣��¼����
	 */
	public static final ActionFactory EXCEPTION_MANAGE = new ActionFactory(ExceptionManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new ExceptionManageAction(window, Messages.EXCEPTION_MANAGE_LABEL, IimageKeys.EXCEPTION_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ���ü�¼����
	 */
	public static final ActionFactory EXPENSE_MANAGE = new ActionFactory(ExpenseManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new ExpenseManageAction(window, Messages.EXPENSE_MANAGE_LABEL, IimageKeys.EXPENSE_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * Ա����Ϣ����
	 */
	public static final ActionFactory EMP_MANAGE = new ActionFactory(EmpManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new EmpManageAction(window,Messages.EMP_MANAGE_LABEL,IimageKeys.EMP_MANAGE_TRAY);
			return action;
		}
	};
	
	/**
	 * ���������¼ ����
	 */
	public static final ActionFactory CARRECORD_MANAGE = new ActionFactory(CarRecordManageAction.ID){
		public IWorkbenchAction create(IWorkbenchWindow window) {
			if(window == null){
				throw new IllegalArgumentException();
			}
			IWorkbenchAction action = new CarRecordManageAction(window, Messages.CARRECORD_MANAGE_LABEL, IimageKeys.CARRECORD_MANAGE_TRAY);
			return action;
		}
		
	};

}

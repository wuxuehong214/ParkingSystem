package parkingsystem;

import java.lang.reflect.Method;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.rcp.wbw.skin.LookAndFeel;
import com.rcp.wxh.communicate.CommunicateJob;
import com.rcp.wxh.editors.EnterEditor;
import com.rcp.wxh.editors.EnterEditorInput;
import com.rcp.wxh.resource.IimageKeys;
import com.rcp.wxh.resource.Messages;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private TrayItem trayItem;
	private Image trayImage;
	private IWorkbenchWindow window;
	
    @Override
	public boolean preWindowShellClose() {
		// TODO Auto-generated method stub
    	boolean confirm = MessageDialog.openConfirm(getWindowConfigurer().getWindow().getShell(), "��ʾ", "ȷ���˳�ϵͳô?");
    	if(confirm)
			return super.preWindowShellClose();
//    		return true;
    	else
    		return false;
	}

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1100, 750));
        //�˵����ɼ�
        configurer.setShowMenuBar(true);
        //�������ɼ�
        configurer.setShowCoolBar(true);
        //����״̬���ɼ�
        configurer.setShowStatusLine(true);
        //���ù������ȿɼ�
        configurer.setShowProgressIndicator(true);
        //���ÿ�����ͼ�ɼ�
        configurer.setShowFastViewBars(true);
        IPreferenceStore preStore = PrefUtil.getAPIPreferenceStore();
        preStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        configurer.setTitle(Messages.SOFT_NAME);
    }
    
    

    // ���ò˵�����ͼƬ����Ƭ��
    public void setMenuBG(Image mimage) {
    	System.out.println("11��");
		org.eclipse.jface.action.MenuManager mm = (MenuManager) 
				ApplicationActionBarAdvisor.getDefault().getMenubar();
				//.getDefault().getMenubar();
		Menu menu = mm.getMenu();
		invoke("setBackgroundImage", menu, new Class[] { Image.class },
				new Image[] { mimage });

	}
	Object invoke(String methodName, Object object, Class<?>[] argsTypes,
			Object[] args) {
		Object result = null;

		try {
			Method m = object.getClass().getDeclaredMethod(methodName,
					argsTypes);
			m.setAccessible(true);
			result = m.invoke(object, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    
	//��������������Ƭ��,�����ñ���ͼƬ
	public void setToorbarBG(Image timage) {
		Object[] childrens = getWindowConfigurer().getWindow().getShell()
				.getChildren();
		for (int i = 0; i < childrens.length; i++) {
			String clazz = childrens[i].getClass().getName();
			if (clazz.endsWith("CBanner")) {
				((Composite) childrens[i]).setBackgroundImage(timage);
				((Composite) childrens[i]).setBackgroundMode(SWT.INHERIT_FORCE);
			}
		}
	}
    
	
	public void setEditorTabFolderColor(Color color) {

		if (getWindowConfigurer().getWindow() == null) {
			return;
		}
		if (getWindowConfigurer().getWindow().getActivePage() == null) {
			return;
		}
		WorkbenchPage page = (WorkbenchPage) getWindowConfigurer().getWindow()
				.getActivePage();
		Composite client = page.getClientComposite();

		Control[] children = client.getChildren();
		Composite child = (Composite) children[0];
		Control[] controls = child.getChildren();
		for (final Control control : controls) {
			if (control instanceof CTabFolder) {
				control.setBackground(color);

			}
		}
	}

	public void setEditorAreaBG(Image image) {

		if (getWindowConfigurer().getWindow() == null) {
			return;
		}
		if (getWindowConfigurer().getWindow().getActivePage() == null) {
			return;
		}
		WorkbenchPage page = (WorkbenchPage) getWindowConfigurer().getWindow()
				.getActivePage();
		Composite client = page.getClientComposite();
		Control[] children = client.getChildren();
		Composite child = (Composite) children[0];
		Control[] controls = child.getChildren();

		for (final Control control : controls) {
			if (control instanceof CTabFolder) {
				CTabFolder tabfolder = (CTabFolder) control;
				Listener[] listeners = tabfolder.getListeners(SWT.MenuDetect);
				if (listeners != null) {
					for (int i = 0; i < listeners.length; i++) {
						// ����ϵͳ�Ҽ��˵�
						tabfolder.removeListener(SWT.MenuDetect, listeners[i]);
					}
				}
				Listener[] listeners2 = tabfolder.getListeners(SWT.DragDetect);
				if (listeners2 != null) {
					for (int i = 0; i < listeners2.length; i++) {
						// ���α༭��Ĭ�Ͽ��϶�������
						tabfolder.removeListener(SWT.DragDetect, listeners2[i]);
					}
				}
				tabfolder.setBackgroundImage(image);
				tabfolder.setBackgroundMode(SWT.INHERIT_FORCE);
			}
		}

	}

	public void addPartListener(final Color color) {
		getWindowConfigurer().getWindow().getActivePage().addPartListener(
				new IPartListener() {

					public void partActivated(IWorkbenchPart part) {
						if (part instanceof EditorPart) {
							setEditorTabFolderColor(color);
						}
					}

					public void partBroughtToTop(IWorkbenchPart part) {
						if (part instanceof EditorPart) {
							setEditorTabFolderColor(color);
						}
					}

					public void partClosed(IWorkbenchPart part) {
						if (part instanceof EditorPart) {
							setEditorTabFolderColor(color);
						}
					}

					public void partDeactivated(IWorkbenchPart part) {
						if (part instanceof EditorPart) {
							setEditorTabFolderColor(color);
						}
					}

					public void partOpened(IWorkbenchPart part) {
						if (part instanceof EditorPart) {
							setEditorTabFolderColor(color);
						}
					}
				});
	}

	public void setProgressIndicatorBG(Image image) {
		Object[] childrens = getWindowConfigurer().getWindow().getShell()
				.getChildren();
		for (int i = 0; i < childrens.length; i++) {
			String clazz = childrens[i].getClass().getName();

			if (clazz.endsWith("ProgressRegion$1")) {
				((Composite) childrens[i]).setBackgroundImage(image);
				((Composite) childrens[i]).setBackgroundMode(SWT.INHERIT_FORCE);
			}
		}
	}

	public void setStausLineBG(Image image) {
		Object[] childrens = getWindowConfigurer().getWindow().getShell()
				.getChildren();
		for (int i = 0; i < childrens.length; i++) {
			String clazz = childrens[i].getClass().getName();

			if (clazz.endsWith("StatusLine")) {
				((Composite) childrens[i]).setBackgroundImage(image);
				((Composite) childrens[i]).setBackgroundMode(SWT.INHERIT_FORCE);
			}
		}
	}

    
    
    /**
     * ������������Ӧ
     */
	public void postWindowOpen() {
		super.postWindowOpen();
		//getWindowConfigurer().getWindow().getShell().setVisible(false);
		setMenuBG(LookAndFeel.getDefault().getMenuImage());//���ò˵�����ͼƬ
		setToorbarBG(LookAndFeel.getDefault().getToolBarImage());//���ù���������ͼƬ
		setStausLineBG(LookAndFeel.getDefault().getToolBarImage());
		setProgressIndicatorBG(LookAndFeel.getDefault().getToolBarImage());
		
		setEditorAreaBG(LookAndFeel.getDefault().getContentBgImage());
		setEditorTabFolderColor(LookAndFeel.getDefault().getTabFolderColor());
		addPartListener(LookAndFeel.getDefault().getTabFolderColor());
		
		getWindowConfigurer().getWindow().getShell().setBackground(
				LookAndFeel.getDefault().getShellColor());
//		
		getWindowConfigurer().getWindow().getShell().setBackgroundMode(
				SWT.INHERIT_FORCE);
		
		getWindowConfigurer().getWindow().getShell().redraw();	
		//getWindowConfigurer().getWindow().getShell().setVisible(true);
		window = getWindowConfigurer().getWindow();
		trayItem = initTaskItem(window);
		if(trayItem != null){
				careateMinimize();
		}
		
		//�������������ر༭��
		IWorkbenchPage workbenchPage = window.getActivePage();
		IEditorPart editor = workbenchPage.findEditor(EnterEditorInput.eei);  //��ȡ�༭��
		if(editor != null){      //����༭���Ѿ���
			workbenchPage.bringToTop(editor);   //����ʾ�ñ༭��
		}else{  //���³�ʼ���ñ༭��
			try{
				editor = workbenchPage.openEditor(EnterEditorInput.eei, EnterEditor.ID);
			}catch(PartInitException ei){
				ei.printStackTrace();
			}
		}
		
		//ͨ�ų�ʼ��
		CommunicateJob job = new CommunicateJob("���ն�ͨ����...");
		job.schedule();  
	}
    //��ʼ��ϵͳ���̶���
    private TrayItem initTaskItem(IWorkbenchWindow window){
    	final Tray tray = window.getShell().getDisplay().getSystemTray();
    	TrayItem trayItem = new TrayItem(tray,SWT.NONE);
    	try{
    		trayImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, IimageKeys.SYSTEM_TRAY).createImage();
    	}catch(Exception e){
    		System.out.println("�����ļ����ش���");
    	}
    	trayItem.setImage(trayImage);
    	trayItem.setToolTipText("");
    	return trayItem;
    }
    /**
     * Ϊ��ǰ�������Ӽ���  ������С���¼����Լ��������ͼ����Ӧ�¼�
     */
    private void careateMinimize(){
    	window.getShell().addShellListener(new ShellAdapter() {
    		public void shellIconified(ShellEvent e){
    			window.getShell().setVisible(false);
    		}
		});
    	trayItem.addListener(SWT.DefaultSelection, new Listener(){
			public void handleEvent(Event arg0) {
				Shell shell = window.getShell();
				if(!shell.isVisible()){
					shell.setVisible(true);
					shell.setMinimized(false);
				}
			}
    	});
    }
    
}

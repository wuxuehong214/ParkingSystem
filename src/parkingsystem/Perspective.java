package parkingsystem;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rcp.wxh.views.NavigationView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
	//	layout.addView(NavigationView.ID, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
	//	layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}

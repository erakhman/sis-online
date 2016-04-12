package com.beesinergi.action;

import javax.annotation.security.PermitAll;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.beesinergi.model.AppMenu;
import com.beesinergi.util.SystemConstant;

@UrlBinding("/action/home")
@PermitAll
public class HomeActionBean extends BaseActionBean {

	@Override
	public Resolution show() {
		if (getUserInfo().getChangePassword().equals(SystemConstant.YES)){
			return new ForwardResolution(ChangePasswordActionBean.class);
		}
		AppMenu menu = getUserInfo().getMenuList().isEmpty() ? null : getUserInfo().getMenuList().get(0);
		return new RedirectResolution("/"+menu.getPageName().replace("ActionBean", ".action"));
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}

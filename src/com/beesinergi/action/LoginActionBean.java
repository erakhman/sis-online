package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.exception.SystemException;
import com.beesinergi.model.AppUser;
import com.beesinergi.service.AppParameterService;
import com.beesinergi.service.UserService;
import com.beesinergi.util.LoaderFactory;
import com.beesinergi.util.SystemConstant;

@UrlBinding("/action/login")
public class LoginActionBean extends BaseActionBean {
	
	private static final Log log = LogFactory.getLog(LoginActionBean.class);
	
	private String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";
	
	@SpringBean
	private UserService userService;
	@SpringBean
	private AppParameterService appParameterService;

	private AppUser user;

	@Override
	public Resolution show() {
		if (getUserInfo() != null){
			return new ForwardResolution(HomeActionBean.class);
		}
		return new ForwardResolution(LOGIN_PAGE);
	}
	
	public Resolution doLogin() {
		try {
			if (getUserInfo() == null){
				user = userService.login(user);
				if (user != null){
					setUserInfo(user);
				}
			}
			LoaderFactory.initializeAppParameter(appParameterService);
			return new ForwardResolution(HomeActionBean.class);
		} catch (SystemException e) {
			addSimpleError(e.getErrors().get(0).getError());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return show();
	}
	
	public Resolution doLogout() {
		getContext().getSession().invalidate();
		return show();
	}
	
	@ValidationMethod(on = "doLogin")
	public void validate() {
		if (user == null){
			addLocalizableError("err.required", "Username");
			addLocalizableError("err.required", "Password");
		} else if (user.getUserName() == null) {
			addLocalizableError("err.required", "Username");
		} else if (user.getPassword() == null) {
			addLocalizableError("err.required", "Password");
		}
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav.login");
	}

}

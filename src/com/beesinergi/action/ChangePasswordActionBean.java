package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.model.AppUser;
import com.beesinergi.service.AppParameterService;
import com.beesinergi.service.UserService;
import com.beesinergi.util.PasswordUtil;
import com.beesinergi.util.SystemConstant;

@UrlBinding("/action/changePassword")
public class ChangePasswordActionBean extends BaseActionBean {
	
	private static final Log log = LogFactory.getLog(LoginActionBean.class);
	
	private String CHANGE_PASSWORD_PAGE = "/WEB-INF/pages/changePassword.jsp";
	
	@SpringBean
	private UserService userService;
	@SpringBean
	private AppParameterService appParameterService;

	private AppUser user;
	private String newPassword;
	private String confirmPassword;

	@Override
	public Resolution show() {
		return new ForwardResolution(CHANGE_PASSWORD_PAGE);
	}
	
	public Resolution doChangePassword() {
		try {
			AppUser userDB = userService.login(user);
			if (userDB != null){
				userDB.setPassword(PasswordUtil.getEncryptPassword(newPassword));
				userDB.setChangePassword(SystemConstant.NO);
				userService.save(userDB);
				setUserInfo(userDB);
				return new ForwardResolution(HomeActionBean.class);
			} else{
				addSimpleError("Password lama salah.");
			}
		} catch (Exception e) {
			log.error(e);
		}
		return show();
	}
	
	@ValidationMethod(on = "doChangePassword")
	public Resolution validate() {
		if (user.getPassword() == null) {
			addLocalizableError("err.required", "Password Lama");
		} 
		if (newPassword == null) {
			addLocalizableError("err.required", "Password Baru");
		} 
		if (confirmPassword == null) {
			addLocalizableError("err.required", "Konfirmasi Password Baru");
		}
		if (newPassword != null && confirmPassword != null){
			if (!newPassword.equals(confirmPassword)){
				addSimpleError("Pasword Baru dan Konfirmasi Password Baru tidak sama.");
			}
		}
		return show();
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav.changePassword");
	}

}

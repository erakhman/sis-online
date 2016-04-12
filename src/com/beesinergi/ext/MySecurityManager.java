package com.beesinergi.ext;

import java.lang.reflect.Method;

import net.sourceforge.stripes.action.ActionBean;

import org.stripesstuff.plugin.security.J2EESecurityManager;

import com.beesinergi.action.BaseActionBean;
import com.beesinergi.model.AppUser;

public class MySecurityManager extends J2EESecurityManager {

	@Override
	protected Boolean isUserAuthenticated(ActionBean bean, Method handler) {
		return getUser(bean) != null;
	}

	@Override
	protected Boolean hasRole(ActionBean actionBean, Method handler, String role) {
		AppUser user = getUser(actionBean);
		if (user != null) {
			// Collection<Role> roles = user.getRoles();
			// return roles != null && roles.contains(new Role(role));
//			return user.getRole().equals(role);
		}
		return false;
	}

	private AppUser getUser(ActionBean bean) {
		return ((BaseActionBean) bean).getContext().getUserInfo();
	}

}

package com.beesinergi.ext;

import java.util.Arrays;
import java.util.List;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.UrlBinding;
import net.sourceforge.stripes.controller.UrlBindingFactory;

import com.beesinergi.action.BaseActionBean;
import com.beesinergi.action.BaseActionBeanContext;
import com.beesinergi.action.LoginActionBean;

@Intercepts(LifecycleStage.HandlerResolution)
public class LoginInterceptor implements Interceptor {
	
	private static final List<String> ALLOW_URL = Arrays.asList(
		"/action/login"
	);

	public Resolution intercept(ExecutionContext execContext) throws Exception {
		Resolution resolution = execContext.proceed();
		BaseActionBeanContext ctx = (BaseActionBeanContext) execContext.getActionBeanContext();
		BaseActionBean actionBean = (BaseActionBean) execContext.getActionBean();
		UrlBinding urlBinding = UrlBindingFactory.getInstance().getBindingPrototype(actionBean.getClass());
		if (ctx.getUserInfo() == null && !isAllow(urlBinding.getPath())) {
			actionBean.addLocalizableMessage("err.loginFirst");
			resolution = new RedirectResolution(LoginActionBean.class);
		}
		return resolution;
	}
	
	public boolean isAllow(String urlBinding) {
        for (String url : ALLOW_URL) {
            if (urlBinding.startsWith(url)) {
                return true;
            }
        }
        return false;
    }
}

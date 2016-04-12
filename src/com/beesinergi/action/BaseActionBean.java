package com.beesinergi.action;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.localization.LocalizationUtility;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.SimpleError;

import com.beesinergi.model.AppUser;
import com.beesinergi.util.DateTimeFunction;
import com.beesinergi.util.LocalPicker;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

public abstract class BaseActionBean implements ActionBean {
	
	protected String AJAX_RESULT_PAGE = "/WEB-INF/pages/layout/ajaxResult.jsp";
	
	private BaseActionBeanContext context;
	private Paging paging;
	
	@DefaultHandler
	public abstract Resolution show();
	
	public abstract String getPageTitle();
	
	protected void setSessionAttribute(String key, Object value) {
		getContext().getRequest().getSession().setAttribute(key, value);
	}
	
	protected void removeSessionAttribute() {
		getContext().getRequest().getSession().invalidate();
	}

	public Object getSessionAttribute(String key) {
		return (Object) getContext().getRequest().getSession().getAttribute(key);
	}
	
	public void addSimpleMessage(String msg, Object... params) {
		context.getMessages().add(new SimpleMessage(msg, params));
	}
	
	public void addLocalizableMessage(String key, Object... params) {
		context.getMessages().add(new LocalizableMessage(key, params));
	}
	
	public void addSimpleError(String msg) {
		context.getValidationErrors().addGlobalError(new SimpleError(msg));
	}
	
	public void addLocalizableError(String key, String param) {
		context.getValidationErrors().addGlobalError(new LocalizableError(key,getLocalizeableMessage(param)));
	}
	
	public void addLocalizableErrorSimpleParam(String key, Object... params) {
		context.getValidationErrors().addGlobalError(new LocalizableError(key,params));
	}
	
	public String getLocalizeableMessage(String key) {
		Locale locale = context.getRequest().getLocale();
		return LocalizationUtility.getErrorMessage(locale, key);
	}
	
	public String getLastUrl() {
		HttpServletRequest req = getContext().getRequest();
		StringBuilder sb = new StringBuilder();

		String uri = (String) req.getAttribute("javax.servlet.forward.request_uri");
		if (uri == null) {
			uri = req.getRequestURI();
		}
		sb.append(uri);
		sb.append('?');
		Map<String, String[]> map = new HashMap<String, String[]>(req.getParameterMap());

		map.remove(LocalPicker.LOCALE);

		for (String key : map.keySet()) {
			String[] values = map.get(key);
			for (String value : values) {
				sb.append(key).append('=').append(value).append('&');
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public String getBeanClass() {
		return this.getClass().getName();
	}
	
	public Paging populatePaging(int count){
		int totalPage = 0;
		if (count <= paging.getLimit()){
			totalPage = 1;
		} else if (count % paging.getLimit() == 0){
			totalPage = count/paging.getLimit(); 
		} else {
			totalPage = (count/paging.getLimit())+1;
		}
		paging.setTotalPage(totalPage);
		paging.setTotalRecord(count);
		return paging;
	}
	
	public void setUserInfo(AppUser user) {
		setSessionAttribute(SystemConstant.SESSION_USER, user);
	}
	
	public AppUser getUserInfo() {
		AppUser user = (AppUser) getSessionAttribute(SystemConstant.SESSION_USER);
		return user;
	}
	
	public int getCurrentSemester() {
		int currentMonth = DateTimeFunction.getCurrentMonth();
		return currentMonth >= 7 && currentMonth <= 12 ? 1 : 2;
	}
	
	public BaseActionBeanContext getContext() {
		return context;
	}

	public void setContext(ActionBeanContext arg0) {
		context = (BaseActionBeanContext) arg0;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}

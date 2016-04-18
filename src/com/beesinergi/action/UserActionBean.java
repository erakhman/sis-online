package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beesinergi.model.AppRole;
import com.beesinergi.model.AppUser;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.RoleService;
import com.beesinergi.service.UserService;
import com.beesinergi.util.SystemConstant;

import flexjson.test.mock.Employee;

public class UserActionBean extends BaseMaintenanceActionBean<AppUser> {
	
	private static final Log log = LogFactory.getLog(UserActionBean.class);
	
    private String LIST_PAGE = "/WEB-INF/pages/user/userList.jsp";
    private String DETAIL_PAGE = "/WEB-INF/pages/user/userDetail.jsp";

	@SpringBean
	private UserService userService;
	@SpringBean
	private RoleService roleService;
	
	private List<Integer> pkUserList;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public Resolution doDelete() {
		try {
			userService.delete(pkUserList);
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
	}
	
	public Resolution doResetPassword() {
		try {
			userService.resetPassword(pkUserList);
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
	}
	
	public List<AppRole> getRoleList() {
		List<AppRole> list = roleService.findAll(new AppRole());
		return list;
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		AppUser model = getModel();
		if (model.getUserName() == null){ 
			addLocalizableError("err.required", "label.userName");
		}
		if (model.getFkEmployee() == null){ 
			addLocalizableError("err.required", "label.employeeName");
		}
		if (model.getFkRoleList() == null){
			addLocalizableError("err.required", "label.roleName");
		}
	}
	
	@Override
	protected CommonService<AppUser> getCommonService() {
		return userService;
	}

	public List<Integer> getPkUserList() {
		return pkUserList;
	}

	public void setPkUserList(List<Integer> pkUserList) {
		this.pkUserList = pkUserList;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.USER);
	}

}

package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.AppMenu;
import com.beesinergi.model.AppRole;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.MenuService;
import com.beesinergi.service.RoleService;
import com.beesinergi.util.SystemConstant;

public class RoleActionBean extends BaseMaintenanceActionBean<AppRole> {
	
	private String LIST_PAGE = "/WEB-INF/pages/role/roleList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/role/roleDetail.jsp";
	
	@SpringBean
	private RoleService roleService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private MenuService menuService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		AppRole model = getModel();
		if (model.getRoleName() == null) { 
			addLocalizableError("err.required", "label.roleName");
		}
		if (model.getRoleCode() == null) { 
			addLocalizableError("err.required", "label.roleCode");
		}
		if (model.getFkMenuList() == null) { 
			addLocalizableError("err.required", "label.menu");
		}
	}
	
	public List<AppMenu> getMenuList() {
		List<AppMenu> list = menuService.findAll(new AppMenu());
		return list;
	}

	@Override
	protected CommonService<AppRole> getCommonService() {
		return roleService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.ROLE);
	}

}

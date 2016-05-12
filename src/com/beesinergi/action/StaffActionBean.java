package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Staff;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.StaffService;
import com.beesinergi.util.SystemConstant;

public class StaffActionBean extends BaseMaintenanceActionBean<Staff> {

	private String LIST_PAGE = "/WEB-INF/pages/master/staff/staffList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/staff/staffDetail.jsp";
	
	@SpringBean 
	private StaffService staffService;
	
	@Override
	protected CommonService<Staff> getCommonService() {
		return staffService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.STAFF);
	}

}

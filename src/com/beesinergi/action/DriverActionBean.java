package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Driver;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.DriverService;
import com.beesinergi.util.SystemConstant;

public class DriverActionBean extends BaseMaintenanceActionBean<Driver> {
	
	private String LIST_PAGE = "/WEB-INF/pages/driver/driverList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/driver/driverDetail.jsp";
	
	@SpringBean
	private DriverService driverService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Driver model = getModel();
		if (model.getDriverName() == null){ 
			addLocalizableError("err.required", "label.driverName");
		}
		if (model.getAddress() == null){
			addLocalizableError("err.required", "label.address");
		}
		if (model.getPhoneNo() == null){
			addLocalizableError("err.required", "label.phoneNo");
		}
	}

	@Override
	protected CommonService<Driver> getCommonService() {
		return driverService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.VENDOR);
	}

}

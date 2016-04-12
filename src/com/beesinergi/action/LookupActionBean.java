package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Lookup;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.util.SystemConstant;

public class LookupActionBean extends BaseMaintenanceActionBean<Lookup> {
	
    private String LIST_PAGE = "/WEB-INF/pages/lookup/lookupList.jsp";
    private String DETAIL_PAGE = "/WEB-INF/pages/lookup/lookupDetail.jsp";

	@SpringBean
	private LookupService lookupService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Lookup model = getModel();
		if (model.getLookupName() == null) { 
			addLocalizableError("err.required", "label.lookupName");
		}
	}
	
	@Override
	protected CommonService<Lookup> getCommonService() {
		return lookupService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.LOOKUP);
	}

}

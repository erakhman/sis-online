package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.PenaltyType;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PenaltyTypeService;
import com.beesinergi.util.SystemConstant;

public class PenaltyTypeActionBean extends BaseMaintenanceActionBean<PenaltyType> {

	private String LIST_PAGE = "/WEB-INF/pages/master/penaltyType/penaltyTypeList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/penaltyType/penaltyTypeDetail.jsp";
	
	@SpringBean 
	private PenaltyTypeService penaltyTypeService;
	
	@Override
	protected CommonService<PenaltyType> getCommonService() {
		return penaltyTypeService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PENALTY_TYPE);
	}

}

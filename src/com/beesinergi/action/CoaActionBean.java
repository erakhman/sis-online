package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Coa;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.CoaService;
import com.beesinergi.util.SystemConstant;

public class CoaActionBean extends BaseMaintenanceActionBean<Coa> {

	private String LIST_PAGE = "/WEB-INF/pages/master/coa/coaList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/coa/coaDetail.jsp";
	
	@SpringBean 
	private CoaService coaService;
	
	@Override
	protected CommonService<Coa> getCommonService() {
		return coaService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.TAHUN_AJARAN);
	}

}

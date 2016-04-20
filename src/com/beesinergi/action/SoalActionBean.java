package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Soal;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.SoalService;
import com.beesinergi.util.SystemConstant;

public class SoalActionBean extends BaseMaintenanceActionBean<Soal> {

	private String LIST_PAGE = "/WEB-INF/pages/master/soal/soalList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/soal/soalDetail.jsp";
	
	@SpringBean 
	private SoalService soalService;
	
	@Override
	protected CommonService<Soal> getCommonService() {
		return soalService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.SOAL);
	}

}

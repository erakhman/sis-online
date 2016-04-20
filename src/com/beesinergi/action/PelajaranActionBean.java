package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Pelajaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.util.SystemConstant;

public class PelajaranActionBean extends BaseMaintenanceActionBean<Pelajaran> {

	private String LIST_PAGE = "/WEB-INF/pages/master/pelajaran/pelajaranList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/pelajaran/pelajaranDetail.jsp";
	
	@SpringBean 
	private PelajaranService pelajaranService;
	
	@Override
	protected CommonService<Pelajaran> getCommonService() {
		return pelajaranService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.PELAJARAN);
	}

}

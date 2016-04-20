package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

public class TahunAjaranActionBean extends BaseMaintenanceActionBean<TahunAjaran> {

	private String LIST_PAGE = "/WEB-INF/pages/master/tahunAjaran/tahunAjaranList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/tahunAjaran/tahunAjaranDetail.jsp";
	
	@SpringBean 
	private TahunAjaranService tahunAjaranService;
	
	@Override
	protected CommonService<TahunAjaran> getCommonService() {
		return tahunAjaranService;
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

package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Siswa;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.SiswaService;
import com.beesinergi.util.SystemConstant;

public class SiswaActionBean extends BaseMaintenanceActionBean<Siswa> {

	private String LIST_PAGE = "/WEB-INF/pages/master/siswa/siswaList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/siswa/siswaDetail.jsp";
	
	@SpringBean 
	private SiswaService siswaService;
	
	@Override
	protected CommonService<Siswa> getCommonService() {
		return siswaService;
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

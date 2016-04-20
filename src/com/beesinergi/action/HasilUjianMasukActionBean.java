package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.HasilUjianMasuk;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.HasilUjianMasukService;
import com.beesinergi.util.SystemConstant;

public class HasilUjianMasukActionBean extends BaseMaintenanceActionBean<HasilUjianMasuk> {
	
	private String LIST_PAGE = "/WEB-INF/pages/hasilUjianMasuk/hasilUjianMasukList.jsp";
	
	@SpringBean
	private HasilUjianMasukService hasilUjianMasukService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@Override
	protected CommonService<HasilUjianMasuk> getCommonService() {
		return hasilUjianMasukService;
	}

	@Override
	protected String getDetailPage() {
		return null;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.HASIL_UJIAN_MASUK);
	}

}

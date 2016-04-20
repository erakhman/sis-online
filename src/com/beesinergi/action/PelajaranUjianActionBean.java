package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Kelas;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.PelajaranUjian;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.KelasService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.PelajaranUjianService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

public class PelajaranUjianActionBean extends BaseMaintenanceActionBean<PelajaranUjian> {

	private String LIST_PAGE = "/WEB-INF/pages/master/pelajaranUjian/pelajaranUjianList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/pelajaranUjian/pelajaranUjianDetail.jsp";
	
	@SpringBean 
	private PelajaranUjianService pelajaranUjianService;
	@SpringBean 
	private PelajaranService pelajaranService;
	@SpringBean 
	private TahunAjaranService tahunAjaranService;
	
	@Override
	protected CommonService<PelajaranUjian> getCommonService() {
		return pelajaranUjianService;
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
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
	}
	
	public List<TahunAjaran> getTahunAjaranList() {
		List<TahunAjaran> list = tahunAjaranService.findAll(null);
		return list;
	}

}

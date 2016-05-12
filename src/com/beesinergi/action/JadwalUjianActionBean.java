package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.JadwalUjian;
import com.beesinergi.model.Kelas;
import com.beesinergi.model.Lookup;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.JadwalUjianService;
import com.beesinergi.service.KelasService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

public class JadwalUjianActionBean extends BaseMaintenanceActionBean<JadwalUjian> {

	private String LIST_PAGE = "/WEB-INF/pages/master/jadwalUjian/jadwalUjianList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/jadwalUjian/jadwalUjianDetail.jsp";
	
	@SpringBean 
	private JadwalUjianService jadwalUjianService;
	@SpringBean 
	private PelajaranService pelajaranService;
	@SpringBean 
	private KelasService kelasService;
	@SpringBean 
	private TahunAjaranService tahunAjaranService;
	@SpringBean 
	private LookupService lookupService;
	
	@Override
	protected CommonService<JadwalUjian> getCommonService() {
		return jadwalUjianService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.JADWAL_UJIAN);
	}
	
	public List<Pelajaran> getPelajaranList() {
		List<Pelajaran> list = pelajaranService.findAll(null);
		return list;
	}
	
	public List<Kelas> getKelasList() {
		List<Kelas> list = kelasService.findAll(null);
		return list;
	}
	
	public List<TahunAjaran> getTahunAjaranList() {
		List<TahunAjaran> list = tahunAjaranService.findAll(null);
		return list;
	}
	
	public List<Lookup> getTypeList() {
		List<Lookup> list = lookupService.findAllByGroup(SystemConstant.LookupGroup.JADWAL_UJIAN);
		return list;
	}

}

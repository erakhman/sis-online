package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.beesinergi.model.ClassHistory;
import com.beesinergi.model.Kelas;
import com.beesinergi.model.Siswa;
import com.beesinergi.service.ClassHistoryService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.KelasService;
import com.beesinergi.service.SiswaService;
import com.beesinergi.util.SystemConstant;

public class SiswaActionBean extends BaseMaintenanceActionBean<Siswa> {

	private String LIST_PAGE = "/WEB-INF/pages/master/siswa/siswaList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/siswa/siswaDetail.jsp";
	private String STUDENT_CLASS = "/WEB-INF/pages/master/siswa/studentClass.jsp";
	
	@SpringBean 
	private SiswaService siswaService;
	@SpringBean 
	private KelasService kelasService;
	@SpringBean 
	private ClassHistoryService classHistoryService;
	
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
	
	public Resolution showStudentClass() {
		return new ForwardResolution(STUDENT_CLASS);
	}
	
	public List<Kelas> getKelasList() {
		List<Kelas> list = kelasService.findAll(null);
		return list;
	}
	
	public List<ClassHistory> getClassHistoryList() {
		List<ClassHistory> list = classHistoryService.findAll(null);
		return list;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.SISWA);
	}

}

package com.beesinergi.action;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.model.ClassHistory;
import com.beesinergi.model.Kelas;
import com.beesinergi.service.ClassHistoryService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.KelasService;
import com.beesinergi.util.SystemConstant;

public class KelasActionBean extends BaseMaintenanceActionBean<Kelas> {

	private String LIST_PAGE = "/WEB-INF/pages/master/kelas/kelasList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/kelas/kelasDetail.jsp";
	
	@SpringBean 
	private KelasService kelasService;
	@SpringBean 
	private ClassHistoryService classHistoryService;
	
	private ClassHistory classHistory;
	
	@Override
	protected CommonService<Kelas> getCommonService() {
		return kelasService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.KELAS);
	}
	
	public List<ClassHistory> getClassHistoryList() {
		List<ClassHistory> list = classHistoryService.findAll(classHistory);
		return list;
	}

	public ClassHistory getClassHistory() {
		return classHistory;
	}

	public void setClassHistory(ClassHistory classHistory) {
		this.classHistory = classHistory;
	}

}

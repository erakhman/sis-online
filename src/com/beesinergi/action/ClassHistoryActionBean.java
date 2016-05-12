package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.beesinergi.model.ClassHistory;
import com.beesinergi.model.Staff;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.ClassHistoryService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.StaffService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

public class ClassHistoryActionBean extends BaseMaintenanceActionBean<ClassHistory> {

	private String CLASS_HISTORY = "/WEB-INF/pages/classHistory/classHistory.jsp";
	
	@SpringBean 
	private ClassHistoryService classHistoryService;
	@SpringBean 
	private StaffService staffService;
	@SpringBean 
	private TahunAjaranService tahunAjaranService;

	@Override
	public Resolution show() {
		return new ForwardResolution(CLASS_HISTORY);
	}
	
	public Resolution doDelete() {
		try {
			classHistoryService.deleteByPrimaryKey(getModel().getPkClassHistory());
			getContext().getResponse().setHeader("X-Stripes-Success","true");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return new ForwardResolution(AJAX_RESULT_PAGE);
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.CLASS_HISTORY);
	}

	@Override
	protected CommonService<ClassHistory> getCommonService() {
		return classHistoryService;
	}

	@Override
	protected String getDetailPage() {
		return null;
	}
	
	public List<ClassHistory> getClassHistoryList() {
		List<ClassHistory> list = classHistoryService.findAll(getModel());
		return list;
	}
	
	public List<Staff> getTeacherList() {
		List<Staff> list = staffService.findAllTeacher();
		return list;
	}
	
	public List<TahunAjaran> getTahunAjaranList() {
		List<TahunAjaran> list = tahunAjaranService.findAll(null);
		return list;
	}

}

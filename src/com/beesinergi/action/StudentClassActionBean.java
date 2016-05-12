package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.beesinergi.model.ClassHistory;
import com.beesinergi.model.Staff;
import com.beesinergi.model.StudentClass;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.ClassHistoryService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.StudentClassService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

public class StudentClassActionBean extends BaseMaintenanceActionBean<StudentClass> {

	private String STUDENT_CLASS = "/WEB-INF/pages/studentClass/studentClass.jsp";
	private String HISTORY_CLASS = "/WEB-INF/pages/studentClass/studentClassHistory.jsp";
	
	@SpringBean 
	private StudentClassService studentClassService;
	@SpringBean 
	private ClassHistoryService classHistoryService;

	@Override
	public Resolution show() {
		return new ForwardResolution(STUDENT_CLASS);
	}
	
	public Resolution showHistory() {
		return new ForwardResolution(HISTORY_CLASS);
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.CLASS_HISTORY);
	}

	@Override
	protected CommonService<StudentClass> getCommonService() {
		return studentClassService;
	}

	@Override
	protected String getDetailPage() {
		return null;
	}
	
	public List<ClassHistory> getClassHistoryList() {
		ClassHistory param = new ClassHistory();
		param.setIsCurrentYear(SystemConstant.YES);
		List<ClassHistory> list = classHistoryService.findAll(param);
		return list;
	}

}

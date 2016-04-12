package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.Lookup;
import com.beesinergi.model.Employee;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.EmployeeService;
import com.beesinergi.util.SystemConstant;

public class EmployeeActionBean extends BaseMaintenanceActionBean<Employee> {
	
	private String LIST_PAGE = "/WEB-INF/pages/employee/employeeList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/employee/employeeDetail.jsp";
	
	@SpringBean
	private EmployeeService employeeService;
	@SpringBean
	private LookupService lookupService;

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		Employee model = getModel();
		if (model.getEmployeeCode() == null) { 
			addLocalizableError("err.required", "label.employeeCode");
		}
		if (model.getEmployeeName() == null) { 
			addLocalizableError("err.required", "label.employeeName");
		}
		if (model.getAddress() == null) { 
			addLocalizableError("err.required", "label.address");
		}
		if (model.getPhoneNo() == null) { 
			addLocalizableError("err.required", "label.phoneNo");
		}
	}
	
	public List<Lookup> getDepartmentList() {
		List<Lookup> list = lookupService.findAllByType(SystemConstant.LookupType.DEPARTMENT);
		return list;
	}

	@Override
	protected CommonService<Employee> getCommonService() {
		return employeeService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.EMPLOYEE);
	}

}

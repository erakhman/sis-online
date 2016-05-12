package com.beesinergi.action;

import java.util.List;

import com.beesinergi.model.LibraryAnnualFee;
import com.beesinergi.model.TahunAjaran;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LibraryAnnualFeeService;
import com.beesinergi.service.LookupService;
import com.beesinergi.service.TahunAjaranService;
import com.beesinergi.util.SystemConstant;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

public class LibraryAnnualFeeActionBean extends BaseMaintenanceActionBean<LibraryAnnualFee> {
	
	private String LIST_PAGE = "/WEB-INF/pages/master/libraryAnnualFee/libraryAnnualFeeList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/libraryAnnualFee/libraryAnnualFeeDetail.jsp";
	
	@SpringBean
	private LibraryAnnualFeeService libraryAnnualFeeService;
	@SpringBean
	private LookupService lookupService;
	@SpringBean
	private TahunAjaranService tahunAjaranService;
	

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@ValidationMethod(on = "doSave")
	public void validate() {
		LibraryAnnualFee model = getModel();
	}
	
	public List<TahunAjaran> getTahunAjaranList() {
		return tahunAjaranService.findAll(null);
	}
	

	@Override
	protected CommonService<LibraryAnnualFee> getCommonService() {
		return libraryAnnualFeeService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.LIBRARY_ANNUAL_FEE);
	}

}

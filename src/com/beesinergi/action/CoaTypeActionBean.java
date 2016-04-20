package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.beesinergi.model.CoaType;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.service.CoaTypeService;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.PelajaranService;
import com.beesinergi.service.PendaftaranService;
import com.beesinergi.util.JSONUtil;
import com.beesinergi.util.SystemConstant;

public class CoaTypeActionBean extends BaseMaintenanceActionBean<CoaType> {
	
	private String LIST_PAGE = "/WEB-INF/pages/master/coaType/coaTypeList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/coaType/coaTypeDetail.jsp";
	
	@SpringBean
	private CoaTypeService coaTypeService;
	

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.COA_TYPE);
	}

	@Override
	protected CommonService<CoaType> getCommonService() {
		// TODO Auto-generated method stub
		return coaTypeService;
	}
	
}

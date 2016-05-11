package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.LibraryMember;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.LibraryMemberService;
import com.beesinergi.util.SystemConstant;

public class LibraryMemberActionBean extends BaseMaintenanceActionBean<LibraryMember> {

	private String LIST_PAGE = "/WEB-INF/pages/master/librarymember/libraryMemberList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/librarymember/libraryMemberDetail.jsp";
	
	@SpringBean 
	private LibraryMemberService libraryMemberService;
	
	@Override
	protected CommonService<LibraryMember> getCommonService() {
		return libraryMemberService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.LIBRARY_MEMBER);
	}

}

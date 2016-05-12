package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.BookLocation;
import com.beesinergi.model.BookShelf;
import com.beesinergi.service.BookLocationService;
import com.beesinergi.service.BookShelfService;
import com.beesinergi.service.CommonService;
import com.beesinergi.util.SystemConstant;

public class BookLocationActionBean extends BaseMaintenanceActionBean<BookLocation> {

	private String LIST_PAGE = "/WEB-INF/pages/master/bookLocation/bookLocationList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/bookLocation/bookLocationDetail.jsp";
	
	@SpringBean 
	private BookLocationService bookLocationService;
	@SpringBean 
	private BookShelfService bookShelfService;
	
	@Override
	protected CommonService<BookLocation> getCommonService() {
		return bookLocationService;
	}

	@Override
	protected String getDetailPage() {
		return DETAIL_PAGE;
	}

	@Override
	public Resolution show() {
		return new ForwardResolution(LIST_PAGE);
	}
	
	public List<BookShelf> getBookShelfList() {
		List<BookShelf> list = bookShelfService.findAll(null);
		return list;
	}

	@Override
	public String getPageTitle() {
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.BOOK_LOCATION);
	}

}

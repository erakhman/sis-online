package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.BookShelf;
import com.beesinergi.service.BookShelfService;
import com.beesinergi.service.CommonService;
import com.beesinergi.util.SystemConstant;

public class BookShelfActionBean extends BaseMaintenanceActionBean<BookShelf> {

	private String LIST_PAGE = "/WEB-INF/pages/master/bookShelf/bookShelfList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/bookShelf/bookShelfDetail.jsp";
	
	@SpringBean 
	private BookShelfService bookShelfService;
	
	@Override
	protected CommonService<BookShelf> getCommonService() {
		return bookShelfService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.BOOK_SHELF);
	}

}

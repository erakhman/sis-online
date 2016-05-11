package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.BookPublisher;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.BookPublisherService;
import com.beesinergi.util.SystemConstant;

public class BookPublisherActionBean extends BaseMaintenanceActionBean<BookPublisher> {

	private String LIST_PAGE = "/WEB-INF/pages/master/bookpublisher/bookPublisherList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/bookpublisher/bookPublisherDetail.jsp";
	
	@SpringBean 
	private BookPublisherService bookPublisherService;
	
	@Override
	protected CommonService<BookPublisher> getCommonService() {
		return bookPublisherService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.BOOK_PUBLISHER);
	}

}

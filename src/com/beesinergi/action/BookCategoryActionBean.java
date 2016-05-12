package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.BookCategory;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.BookCategoryService;
import com.beesinergi.util.SystemConstant;

public class BookCategoryActionBean extends BaseMaintenanceActionBean<BookCategory> {

	private String LIST_PAGE = "/WEB-INF/pages/master/bookcategory/bookCategoryList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/bookcategory/bookCategoryDetail.jsp";
	
	@SpringBean 
	private BookCategoryService bookCategoryService;
	
	@Override
	protected CommonService<BookCategory> getCommonService() {
		return bookCategoryService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.BOOK_CATEGORY);
	}

}

package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.ReturnAndBorrowBook;
import com.beesinergi.service.ReturnAndBorrowBookService;
import com.beesinergi.service.CommonService;
import com.beesinergi.util.SystemConstant;

public class ReturnAndBorrowBookActionBean extends BaseMaintenanceActionBean<ReturnAndBorrowBook> {

	private String LIST_PAGE = "/WEB-INF/pages/returnAndBorrow/returnAndBorrowBookList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/returnAndBorrow/returnAndBorrowBookDetail.jsp";
	
	@SpringBean 
	private ReturnAndBorrowBookService returnAndBorrowBookService;
	
	@Override
	protected CommonService<ReturnAndBorrowBook> getCommonService() {
		return returnAndBorrowBookService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.RETURN_AND_BORROW_BOOK);
	}

}

package com.beesinergi.action;

import java.util.List;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Book;
import com.beesinergi.model.BookCategory;
import com.beesinergi.model.BookLocation;
import com.beesinergi.model.BookPublisher;
import com.beesinergi.service.BookCategoryService;
import com.beesinergi.service.BookLocationService;
import com.beesinergi.service.BookPublisherService;
import com.beesinergi.service.BookService;
import com.beesinergi.service.CommonService;
import com.beesinergi.util.SystemConstant;

public class BookActionBean extends BaseMaintenanceActionBean<Book> {

	private String LIST_PAGE = "/WEB-INF/pages/master/book/bookList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/book/bookDetail.jsp";
	
	@SpringBean 
	private BookService bookService;
	@SpringBean 
	private BookCategoryService bookCategoryService;
	@SpringBean 
	private BookPublisherService bookPublisherService;
	@SpringBean 
	private BookLocationService bookLocationService;
	
	@Override
	protected CommonService<Book> getCommonService() {
		return bookService;
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
		return getLocalizeableMessage("nav."+SystemConstant.MenuCode.BOOK);
	}
	
	public List<BookLocation> getBookLocationList(){
		return bookLocationService.findAll(null);
	}
	
	public List<BookCategory> getBookCategoryList(){
		return bookCategoryService.findAll(null);
	}
	
	public List<BookPublisher> getBookPublisherList(){
		return bookPublisherService.findAll(null);
	}

}

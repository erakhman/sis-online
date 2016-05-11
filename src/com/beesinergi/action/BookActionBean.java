package com.beesinergi.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.beesinergi.model.Book;
import com.beesinergi.service.CommonService;
import com.beesinergi.service.BookService;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.model.BookCategory;
import com.beesinergi.service.BookCategoryService;

import com.beesinergi.model.BookPublisher;
import com.beesinergi.service.BookPublisherService;

import java.util.List;

public class BookActionBean extends BaseMaintenanceActionBean<Book> {

	private String LIST_PAGE = "/WEB-INF/pages/master/book/bookList.jsp";
	private String DETAIL_PAGE = "/WEB-INF/pages/master/book/bookDetail.jsp";
	
	@SpringBean 
	private BookService bookService;
	
	@SpringBean 
	private BookCategoryService bookCategoryService;
	
	@SpringBean 
	private BookPublisherService bookPublisherService;
	
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
	
	public List<BookCategory> getBookCategoryList(){
		return bookCategoryService.findAll(null);
	}
	
	public List<BookPublisher> getBookPublisherList(){
		return bookPublisherService.findAll(null);
	}

}

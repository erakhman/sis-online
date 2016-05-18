package com.beesinergi.model;

import java.util.Date;

import com.beesinergi.util.SystemConstant;

public class Book {

    private Integer pkBook;
    private String bookIsbn;
    private String bookCode;
    private String bookTitle;
    private Integer bookCategory;
    private String bookAuthor;
    private Integer bookPublisher;
    private Integer bookLocation;
    private Integer bookStatus;
    private Date receivedDate;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private String isActive;
    private Double price;
    
    private String publisherName;
    private String categoryDescription;
    private String locationDescription;
    
    public String getStatusDescr() {
    	return SystemConstant.BookStatus().get(bookStatus);
    }
    
	public Integer getPkBook() {
		return pkBook;
	}
	public void setPkBook(Integer pkBook) {
		this.pkBook = pkBook;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Integer getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(Integer bookCategory) {
		this.bookCategory = bookCategory;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public Integer getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(Integer bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public Integer getBookLocation() {
		return bookLocation;
	}
	public void setBookLocation(Integer bookLocation) {
		this.bookLocation = bookLocation;
	}
	public Integer getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
    
    
}
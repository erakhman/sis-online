package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class Purchase {

    private Integer pkPurchase;
    private String purchaseNo;
    private Integer fkPoOut;
    private Integer fkVendor;
    private Date purchaseDate;
    private String remarks;
    private Double payment;
    private Date paidDate;
    private Date dueDate;
    private Double outstanding;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    
    private String poOutNo;
    private String vendorName;
    
    private List<PurchaseDetail> purchaseDetailList;
    
	public Integer getPkPurchase() {
		return pkPurchase;
	}
	public void setPkPurchase(Integer pkPurchase) {
		this.pkPurchase = pkPurchase;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public Integer getFkPoOut() {
		return fkPoOut;
	}
	public void setFkPoOut(Integer fkPoOut) {
		this.fkPoOut = fkPoOut;
	}
	public Integer getFkVendor() {
		return fkVendor;
	}
	public void setFkVendor(Integer fkVendor) {
		this.fkVendor = fkVendor;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public List<PurchaseDetail> getPurchaseDetailList() {
		return purchaseDetailList;
	}
	public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
		this.purchaseDetailList = purchaseDetailList;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getPoOutNo() {
		return poOutNo;
	}
	public void setPoOutNo(String poOutNo) {
		this.poOutNo = poOutNo;
	}
    

}
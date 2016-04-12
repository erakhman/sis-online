package com.beesinergi.model;

import java.util.Date;

public class CustomerVoucher {

    private Integer pkCustomerVoucher;
    private Integer fkCustomer;
    private String namaVoucher;
    private Double amount;
    private Date createdDate;
    private String createdBy;
    private Double sisaVoucher;
    
	public Integer getPkCustomerVoucher() {
		return pkCustomerVoucher;
	}
	public void setPkCustomerVoucher(Integer pkCustomerVoucher) {
		this.pkCustomerVoucher = pkCustomerVoucher;
	}
	public Integer getFkCustomer() {
		return fkCustomer;
	}
	public void setFkCustomer(Integer fkCustomer) {
		this.fkCustomer = fkCustomer;
	}
	public String getNamaVoucher() {
		return namaVoucher;
	}
	public void setNamaVoucher(String namaVoucher) {
		this.namaVoucher = namaVoucher;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	public Double getSisaVoucher() {
		return sisaVoucher;
	}
	public void setSisaVoucher(Double sisaVoucher) {
		this.sisaVoucher = sisaVoucher;
	}
    

}
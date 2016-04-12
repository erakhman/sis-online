package com.beesinergi.model;

import java.util.Date;

public class CustomerClaim {
 
	Integer pkCustomerClaim;
    private Integer fkCustomer;
    private Date tanggal;
    private Double amount;
    private Date createdDate;
    private String createdBy;
    private Integer type;
    
    public static class Type {
    	public static Integer BONUS = 1;
    	public static Integer SIMPAN = 2;
    	public static Integer PINJAM = 3;
    }
    
	public Integer getPkCustomerClaim() {
		return pkCustomerClaim;
	}
	public void setPkCustomerClaim(Integer pkCustomerClaim) {
		this.pkCustomerClaim = pkCustomerClaim;
	}
	public Integer getFkCustomer() {
		return fkCustomer;
	}
	public void setFkCustomer(Integer fkCustomer) {
		this.fkCustomer = fkCustomer;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    

}
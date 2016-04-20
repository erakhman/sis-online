package com.beesinergi.model;

import java.util.Date;

public class Coa {

    private Integer pkCoa;
    private String acctNo;
    private String acctName;
    private String descr;
    private Integer fkCoaType;
    private Double balance;
    private Integer naturalBalance;
    private Integer parentCoa;
    private Integer status;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
	public Integer getPkCoa() {
		return pkCoa;
	}
	public void setPkCoa(Integer pkCoa) {
		this.pkCoa = pkCoa;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getFkCoaType() {
		return fkCoaType;
	}
	public void setFkCoaType(Integer fkCoaType) {
		this.fkCoaType = fkCoaType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getNaturalBalance() {
		return naturalBalance;
	}
	public void setNaturalBalance(Integer naturalBalance) {
		this.naturalBalance = naturalBalance;
	}
	public Integer getParentCoa() {
		return parentCoa;
	}
	public void setParentCoa(Integer parentCoa) {
		this.parentCoa = parentCoa;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
       
}
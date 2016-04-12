package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class PoOut {

    private Integer pkPoOut;
    private String poOutNo;
    private Integer fkPo;
    private Integer fkVendor;
    private Date poOutDate;
    private String remarks;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    private String status;
    
    private String statusName;
    private String poNo;
    private String vendorName;
    private List<PoOutDetail> poOutDetailList;
    
	public Integer getPkPoOut() {
		return pkPoOut;
	}
	public void setPkPoOut(Integer pkPoOut) {
		this.pkPoOut = pkPoOut;
	}
	public String getPoOutNo() {
		return poOutNo;
	}
	public void setPoOutNo(String poOutNo) {
		this.poOutNo = poOutNo;
	}
	public Integer getFkPo() {
		return fkPo;
	}
	public void setFkPo(Integer fkPo) {
		this.fkPo = fkPo;
	}
	public Integer getFkVendor() {
		return fkVendor;
	}
	public void setFkVendor(Integer fkVendor) {
		this.fkVendor = fkVendor;
	}
	public Date getPoOutDate() {
		return poOutDate;
	}
	public void setPoOutDate(Date poOutDate) {
		this.poOutDate = poOutDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public List<PoOutDetail> getPoOutDetailList() {
		return poOutDetailList;
	}
	public void setPoOutDetailList(List<PoOutDetail> poOutDetailList) {
		this.poOutDetailList = poOutDetailList;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
    

}
package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class Po {

    private Integer pkPo;
    private String poNo;
    private Date poDate;
    private String status;
    private String remarks;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    private Integer fkEmployee;
    
    private String statusName;
    private Integer fkDepartment;
    private List<PoDetail> poDetailList;
    private List<String> statusList;
    
	public Integer getPkPo() {
		return pkPo;
	}
	public void setPkPo(Integer pkPo) {
		this.pkPo = pkPo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public List<PoDetail> getPoDetailList() {
		return poDetailList;
	}
	public void setPoDetailList(List<PoDetail> poDetailList) {
		this.poDetailList = poDetailList;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getFkEmployee() {
		return fkEmployee;
	}
	public void setFkEmployee(Integer fkEmployee) {
		this.fkEmployee = fkEmployee;
	}
	public Integer getFkDepartment() {
		return fkDepartment;
	}
	public void setFkDepartment(Integer fkDepartment) {
		this.fkDepartment = fkDepartment;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
    

}
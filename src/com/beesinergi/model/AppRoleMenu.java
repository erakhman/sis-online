package com.beesinergi.model;

import java.util.Date;

public class AppRoleMenu {

    private Integer pkRoleMenu;
    private Integer fkRole;
    private Integer fkMenu;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    
	public Integer getPkRoleMenu() {
		return pkRoleMenu;
	}
	public void setPkRoleMenu(Integer pkRoleMenu) {
		this.pkRoleMenu = pkRoleMenu;
	}
	public Integer getFkRole() {
		return fkRole;
	}
	public void setFkRole(Integer fkRole) {
		this.fkRole = fkRole;
	}
	public Integer getFkMenu() {
		return fkMenu;
	}
	public void setFkMenu(Integer fkMenu) {
		this.fkMenu = fkMenu;
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

}
package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class AppRole {

    private Integer pkRole;
    private String roleCode;
    private String roleName;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    
    private List<AppRoleMenu> menuList;
    private List<Integer> fkMenuList;
    
	public Integer getPkRole() {
		return pkRole;
	}
	public void setPkRole(Integer pkRole) {
		this.pkRole = pkRole;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public List<AppRoleMenu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<AppRoleMenu> menuList) {
		this.menuList = menuList;
	}
	public List<Integer> getFkMenuList() {
		return fkMenuList;
	}
	public void setFkMenuList(List<Integer> fkMenuList) {
		this.fkMenuList = fkMenuList;
	}
	
    

}
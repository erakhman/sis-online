package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class AppUser {

    private Integer pkUser;
    private String userName;
    private String password;
    private String fullName;
    private Integer wrongPassword;
    private String isLocked;
    private String changePassword;
    private Date lastLoginDate;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private Integer fkEmployee;
    
    private String roleName;
    private String employeeName;
    private List<AppMenu> menuList;
    private List<AppUserRole> userRoleList;
    private List<Integer> fkRoleList;
    
    public boolean isHasRolePoApproval() {
    	boolean hasRole = false;
    	for (AppMenu menu:menuList){
    		if (menu.getPageName().equals("PoApprovalActionBean")){
    			hasRole = true;
    		}
    	}
    	return hasRole;
    }

	public Integer getPkUser() {
		return pkUser;
	}

	public void setPkUser(Integer pkUser) {
		this.pkUser = pkUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getWrongPassword() {
		return wrongPassword;
	}

	public void setWrongPassword(Integer wrongPassword) {
		this.wrongPassword = wrongPassword;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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

	public List<AppMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AppMenu> menuList) {
		this.menuList = menuList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Integer> getFkRoleList() {
		return fkRoleList;
	}

	public void setFkRoleList(List<Integer> fkRoleList) {
		this.fkRoleList = fkRoleList;
	}

//	public List<AppRole> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<AppRole> roleList) {
//		this.roleList = roleList;
//	}

	public Integer getFkEmployee() {
		return fkEmployee;
	}

	public void setFkEmployee(Integer fkEmployee) {
		this.fkEmployee = fkEmployee;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<AppUserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<AppUserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

}
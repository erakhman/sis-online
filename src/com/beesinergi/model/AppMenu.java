package com.beesinergi.model;

import java.util.Date;

public class AppMenu {

    private Integer pkMenu;
    private String mainMenu;
    private String subMenu;
    private String pageName;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isActive;
    private String menuCode;
    private String menuOrder;
    
	public Integer getPkMenu() {
		return pkMenu;
	}
	public void setPkMenu(Integer pkMenu) {
		this.pkMenu = pkMenu;
	}
	public String getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}
	public String getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
    
   

}
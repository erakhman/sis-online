package com.beesinergi.model;

import java.util.List;

public class ClassHistory {
	
	private Integer pkClassHistory;
	private Integer fkStaff;
	private Integer fkClass;
	private Integer fkTahunAjaran;
	
	private String tahunAjaran;
	private String staffName;
	private String className;
	private String isCurrentYear;
	
	public Integer getPkClassHistory() {
		return pkClassHistory;
	}
	public void setPkClassHistory(Integer pkClassHistory) {
		this.pkClassHistory = pkClassHistory;
	}
	public Integer getFkStaff() {
		return fkStaff;
	}
	public void setFkStaff(Integer fkStaff) {
		this.fkStaff = fkStaff;
	}
	public Integer getFkTahunAjaran() {
		return fkTahunAjaran;
	}
	public void setFkTahunAjaran(Integer fkTahunAjaran) {
		this.fkTahunAjaran = fkTahunAjaran;
	}
	public Integer getFkClass() {
		return fkClass;
	}
	public void setFkClass(Integer fkClass) {
		this.fkClass = fkClass;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getIsCurrentYear() {
		return isCurrentYear;
	}
	public void setIsCurrentYear(String isCurrentYear) {
		this.isCurrentYear = isCurrentYear;
	}
}

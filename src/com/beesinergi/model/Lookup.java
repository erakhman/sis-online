package com.beesinergi.model;

import java.util.Date;

public class Lookup {

    private Integer pkLookup;
    private String lookupName;
    private String lookupValue;
    private Integer lookupType;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private String isActive;
    
    public static class LookupName {
    	public static String CATEGORY_BARANG = "Kategori Barang";
    }
    
	public Integer getPkLookup() {
		return pkLookup;
	}
	public void setPkLookup(Integer pkLookup) {
		this.pkLookup = pkLookup;
	}
	public String getLookupName() {
		return lookupName;
	}
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	public String getLookupValue() {
		return lookupValue;
	}
	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}
	public Integer getLookupType() {
		return lookupType;
	}
	public void setLookupType(Integer lookupType) {
		this.lookupType = lookupType;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
    

}
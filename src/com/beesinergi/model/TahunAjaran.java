package com.beesinergi.model;

import java.util.Date;

public class TahunAjaran {

    private Integer pkTahunAjaran;
    private String tahunAjaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
	public Integer getPkTahunAjaran() {
		return pkTahunAjaran;
	}
	public void setPkTahunAjaran(Integer pkTahunAjaran) {
		this.pkTahunAjaran = pkTahunAjaran;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
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
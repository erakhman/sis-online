package com.beesinergi.model;

import java.util.Date;

public class Pelajaran {

    private Integer pkPelajaran;
    private String kodePelajaran;
    private String namaPelajaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
	public Integer getPkPelajaran() {
		return pkPelajaran;
	}
	public void setPkPelajaran(Integer pkPelajaran) {
		this.pkPelajaran = pkPelajaran;
	}
	public String getKodePelajaran() {
		return kodePelajaran;
	}
	public void setKodePelajaran(String kodePelajaran) {
		this.kodePelajaran = kodePelajaran;
	}
	public String getNamaPelajaran() {
		return namaPelajaran;
	}
	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
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
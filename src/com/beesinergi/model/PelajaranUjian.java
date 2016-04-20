package com.beesinergi.model;

import java.util.Date;

public class PelajaranUjian {

    private Integer pkPelajaranUjian;
    private Integer fkPelajaran;
    private String fkTahunAjaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private String isActive;
    
    private String namaPelajaran;
    private String tahunAjaran;
    
	public Integer getPkPelajaranUjian() {
		return pkPelajaranUjian;
	}
	public void setPkPelajaranUjian(Integer pkPelajaranUjian) {
		this.pkPelajaranUjian = pkPelajaranUjian;
	}
	public Integer getFkPelajaran() {
		return fkPelajaran;
	}
	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
	}
	public String getFkTahunAjaran() {
		return fkTahunAjaran;
	}
	public void setFkTahunAjaran(String fkTahunAjaran) {
		this.fkTahunAjaran = fkTahunAjaran;
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
	public String getNamaPelajaran() {
		return namaPelajaran;
	}
	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
    
}
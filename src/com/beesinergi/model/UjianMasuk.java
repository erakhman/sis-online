package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class UjianMasuk {

    private Integer pkUjianMasuk;
    private Integer fkTahunAjaran;
    private Integer fkPelajaran;
    private Integer fkPendaftaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private String tahunAjaran;
    
    private List<UjianMasukDetail> ujianMasukDetailList;
    
	public Integer getPkUjianMasuk() {
		return pkUjianMasuk;
	}
	public void setPkUjianMasuk(Integer pkUjianMasuk) {
		this.pkUjianMasuk = pkUjianMasuk;
	}
	public Integer getFkTahunAjaran() {
		return fkTahunAjaran;
	}
	public void setFkTahunAjaran(Integer fkTahunAjaran) {
		this.fkTahunAjaran = fkTahunAjaran;
	}
	public Integer getFkPelajaran() {
		return fkPelajaran;
	}
	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
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
	public Integer getFkPendaftaran() {
		return fkPendaftaran;
	}
	public void setFkPendaftaran(Integer fkPendaftaran) {
		this.fkPendaftaran = fkPendaftaran;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
	public List<UjianMasukDetail> getUjianMasukDetailList() {
		return ujianMasukDetailList;
	}
	public void setUjianMasukDetailList(List<UjianMasukDetail> ujianMasukDetailList) {
		this.ujianMasukDetailList = ujianMasukDetailList;
	}
    
}
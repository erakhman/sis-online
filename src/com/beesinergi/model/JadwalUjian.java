package com.beesinergi.model;

import java.util.Date;

public class JadwalUjian {

    private Integer pkJadwalUjian;
    private Integer fkPelajaran;
    private Integer fkKelas;
    private Date startDate;
    private Date endDate;
    private Integer fkLookupType;
    private Integer fkTahunAjaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
    private String namaPelajaran;
    private String namaKelas;
    private String tahunAjaran;
    private String lookupName;
    private String isActive;
    
	public Integer getPkJadwalUjian() {
		return pkJadwalUjian;
	}
	public void setPkJadwalUjian(Integer pkJadwalUjian) {
		this.pkJadwalUjian = pkJadwalUjian;
	}
	public Integer getFkPelajaran() {
		return fkPelajaran;
	}
	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
	}
	public Integer getFkKelas() {
		return fkKelas;
	}
	public void setFkKelas(Integer fkKelas) {
		this.fkKelas = fkKelas;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getFkTahunAjaran() {
		return fkTahunAjaran;
	}
	public void setFkTahunAjaran(Integer fkTahunAjaran) {
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
	public String getNamaPelajaran() {
		return namaPelajaran;
	}
	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
	}
	public String getNamaKelas() {
		return namaKelas;
	}
	public void setNamaKelas(String namaKelas) {
		this.namaKelas = namaKelas;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
	public Integer getFkLookupType() {
		return fkLookupType;
	}
	public void setFkLookupType(Integer fkLookupType) {
		this.fkLookupType = fkLookupType;
	}
	public String getLookupName() {
		return lookupName;
	}
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
    
}
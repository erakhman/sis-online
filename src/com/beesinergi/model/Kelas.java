package com.beesinergi.model;

import java.util.Date;

public class Kelas {

    private Integer pkKelas;
    private String kodeKelas;
    private String namaKelas;
    private Integer kuota;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
	public Integer getPkKelas() {
		return pkKelas;
	}
	public void setPkKelas(Integer pkKelas) {
		this.pkKelas = pkKelas;
	}
	public String getKodeKelas() {
		return kodeKelas;
	}
	public void setKodeKelas(String kodeKelas) {
		this.kodeKelas = kodeKelas;
	}
	public String getNamaKelas() {
		return namaKelas;
	}
	public void setNamaKelas(String namaKelas) {
		this.namaKelas = namaKelas;
	}
	public Integer getKuota() {
		return kuota;
	}
	public void setKuota(Integer kuota) {
		this.kuota = kuota;
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
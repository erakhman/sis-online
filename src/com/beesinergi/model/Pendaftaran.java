package com.beesinergi.model;

import java.util.Date;
import java.util.List;
import com.beesinergi.model.Siswa;

public class Pendaftaran {

    private Integer pkPendaftaran;
    private Integer fkSiswa;
    private String namaSiswa;
    private String userName;
    private Date tglLahir;
    private Double nem;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
    private Siswa siswa;
    private List<PendaftaranDetail> pendaftaranDetailList;
    
	public Integer getPkPendaftaran() {
		return pkPendaftaran;
	}
	public void setPkPendaftaran(Integer pkPendaftaran) {
		this.pkPendaftaran = pkPendaftaran;
	}
	public Integer getFkSiswa() {
		return fkSiswa;
	}
	public void setFkSiswa(Integer fkSiswa) {
		this.fkSiswa = fkSiswa;
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
	public Siswa getSiswa() {
		return siswa;
	}
	public void setSiswa(Siswa siswa) {
		this.siswa = siswa;
	}
	public List<PendaftaranDetail> getPendaftaranDetailList() {
		return pendaftaranDetailList;
	}
	public void setPendaftaranDetailList(
			List<PendaftaranDetail> pendaftaranDetailList) {
		this.pendaftaranDetailList = pendaftaranDetailList;
	}
	public String getNamaSiswa() {
		return namaSiswa;
	}
	public void setNamaSiswa(String namaSiswa) {
		this.namaSiswa = namaSiswa;
	}
	public Date getTglLahir() {
		return tglLahir;
	}
	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}
	public Double getNem() {
		return nem;
	}
	public void setNem(Double nem) {
		this.nem = nem;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
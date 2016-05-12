package com.beesinergi.model;

import java.util.Date;
import java.util.List;
import com.beesinergi.model.Siswa;

import com.beesinergi.util.SystemConstant;

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
    private Integer fkTahunAjaran;
    private Integer status;
    private String kodePendaftaran;
    private Integer biayaPendaftaran;
    
    private Double totalNilaiUjian;
    private String tahunAjaran;
    private String orderBy;
    
    private Siswa siswa;
    private List<PendaftaranDetail> pendaftaranDetailList;
    
    public String getStatusDescr() {
		return SystemConstant.RegistrationStatus().get(status);
	}
    
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
	public Double getTotalNilaiUjian() {
		return totalNilaiUjian;
	}
	public void setTotalNilaiUjian(Double totalNilaiUjian) {
		this.totalNilaiUjian = totalNilaiUjian;
	}
	public Integer getFkTahunAjaran() {
		return fkTahunAjaran;
	}
	public void setFkTahunAjaran(Integer fkTahunAjaran) {
		this.fkTahunAjaran = fkTahunAjaran;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKodePendaftaran() {
		return kodePendaftaran;
	}
	public void setKodePendaftaran(String kodePendaftaran) {
		this.kodePendaftaran = kodePendaftaran;
	}
	public Integer getBiayaPendaftaran() {
		return biayaPendaftaran;
	}
	public void setBiayaPendaftaran(Integer biayaPendaftaran) {
		this.biayaPendaftaran = biayaPendaftaran;
	}
}

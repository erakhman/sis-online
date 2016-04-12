package com.beesinergi.model;

import java.util.Date;

public class Soal {

    private Integer pkSoal;
    private Integer level;
    private String soalPelajaran;
    private String pilihanA;
    private String pilihanB;
    private String pilihanC;
    private String pilihanD;
    private String pilihanE;
    private String jawaban;
    private Integer fkPelajaran;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    
    private String namaPelajaran;
    
	public Integer getPkSoal() {
		return pkSoal;
	}
	public void setPkSoal(Integer pkSoal) {
		this.pkSoal = pkSoal;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPilihanA() {
		return pilihanA;
	}
	public void setPilihanA(String pilihanA) {
		this.pilihanA = pilihanA;
	}
	public String getPilihanB() {
		return pilihanB;
	}
	public void setPilihanB(String pilihanB) {
		this.pilihanB = pilihanB;
	}
	public String getPilihanC() {
		return pilihanC;
	}
	public void setPilihanC(String pilihanC) {
		this.pilihanC = pilihanC;
	}
	public String getPilihanD() {
		return pilihanD;
	}
	public void setPilihanD(String pilihanD) {
		this.pilihanD = pilihanD;
	}
	public String getPilihanE() {
		return pilihanE;
	}
	public void setPilihanE(String pilihanE) {
		this.pilihanE = pilihanE;
	}
	public String getJawaban() {
		return jawaban;
	}
	public void setJawaban(String jawaban) {
		this.jawaban = jawaban;
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
	public String getSoalPelajaran() {
		return soalPelajaran;
	}
	public void setSoalPelajaran(String soalPelajaran) {
		this.soalPelajaran = soalPelajaran;
	}
	public Integer getFkPelajaran() {
		return fkPelajaran;
	}
	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
	}
	public String getNamaPelajaran() {
		return namaPelajaran;
	}
	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
	}
    
}
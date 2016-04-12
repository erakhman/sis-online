package com.beesinergi.model;

public class UjianMasukDetail {

    private Integer pkUjianMasukDetail;
    private Integer fkUjianMasuk;
    private Integer fkSoal;
    private String jawabanSoal;
    private String jawabanSiswa;
    private Integer fkPendaftaran;
    
    private Soal soal;
    
	public Integer getPkUjianMasukDetail() {
		return pkUjianMasukDetail;
	}
	public void setPkUjianMasukDetail(Integer pkUjianMasukDetail) {
		this.pkUjianMasukDetail = pkUjianMasukDetail;
	}
	public Integer getFkUjianMasuk() {
		return fkUjianMasuk;
	}
	public void setFkUjianMasuk(Integer fkUjianMasuk) {
		this.fkUjianMasuk = fkUjianMasuk;
	}
	public Integer getFkSoal() {
		return fkSoal;
	}
	public void setFkSoal(Integer fkSoal) {
		this.fkSoal = fkSoal;
	}
	public String getJawabanSoal() {
		return jawabanSoal;
	}
	public void setJawabanSoal(String jawabanSoal) {
		this.jawabanSoal = jawabanSoal;
	}
	public String getJawabanSiswa() {
		return jawabanSiswa;
	}
	public void setJawabanSiswa(String jawabanSiswa) {
		this.jawabanSiswa = jawabanSiswa;
	}
	public Integer getFkPendaftaran() {
		return fkPendaftaran;
	}
	public void setFkPendaftaran(Integer fkPendaftaran) {
		this.fkPendaftaran = fkPendaftaran;
	}
	public Soal getSoal() {
		return soal;
	}
	public void setSoal(Soal soal) {
		this.soal = soal;
	}
    
}
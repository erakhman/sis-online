package com.beesinergi.model;

public class HasilUjianMasuk {

	private Integer pkHasilUjianMasuk;
	private Integer jumlahSoal;
	private Integer jawabanBenar;
	private Integer jawabanSalah;
	private double score;
	private Integer fkPendaftaran;
	private Integer fkPelajaran;
	
	private String namaPelajaran;
	private Pendaftaran pendaftaran;
	
	public Integer getJumlahSoal() {
		return jumlahSoal;
	}
	public void setJumlahSoal(Integer jumlahSoal) {
		this.jumlahSoal = jumlahSoal;
	}
	public Integer getJawabanBenar() {
		return jawabanBenar;
	}
	public void setJawabanBenar(Integer jawabanBenar) {
		this.jawabanBenar = jawabanBenar;
	}
	public Integer getJawabanSalah() {
		return jawabanSalah;
	}
	public void setJawabanSalah(Integer jawabanSalah) {
		this.jawabanSalah = jawabanSalah;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Integer getPkHasilUjianMasuk() {
		return pkHasilUjianMasuk;
	}
	public void setPkHasilUjianMasuk(Integer pkHasilUjianMasuk) {
		this.pkHasilUjianMasuk = pkHasilUjianMasuk;
	}
	public Integer getFkPendaftaran() {
		return fkPendaftaran;
	}
	public void setFkPendaftaran(Integer fkPendaftaran) {
		this.fkPendaftaran = fkPendaftaran;
	}
	public Pendaftaran getPendaftaran() {
		return pendaftaran;
	}
	public void setPendaftaran(Pendaftaran pendaftaran) {
		this.pendaftaran = pendaftaran;
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

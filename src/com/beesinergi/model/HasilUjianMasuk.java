package com.beesinergi.model;

public class HasilUjianMasuk {

	private int pkHasilUjianMasuk;
	private int jumlahSoal;
	private int jawabanBenar;
	private int jawabanSalah;
	private double score;
	private int fkPendaftaran;
	
	private Pendaftaran pendaftaran;
	
	public int getJumlahSoal() {
		return jumlahSoal;
	}
	public void setJumlahSoal(int jumlahSoal) {
		this.jumlahSoal = jumlahSoal;
	}
	public int getJawabanBenar() {
		return jawabanBenar;
	}
	public void setJawabanBenar(int jawabanBenar) {
		this.jawabanBenar = jawabanBenar;
	}
	public int getJawabanSalah() {
		return jawabanSalah;
	}
	public void setJawabanSalah(int jawabanSalah) {
		this.jawabanSalah = jawabanSalah;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getPkHasilUjianMasuk() {
		return pkHasilUjianMasuk;
	}
	public void setPkHasilUjianMasuk(int pkHasilUjianMasuk) {
		this.pkHasilUjianMasuk = pkHasilUjianMasuk;
	}
	public int getFkPendaftaran() {
		return fkPendaftaran;
	}
	public void setFkPendaftaran(int fkPendaftaran) {
		this.fkPendaftaran = fkPendaftaran;
	}
	public Pendaftaran getPendaftaran() {
		return pendaftaran;
	}
	public void setPendaftaran(Pendaftaran pendaftaran) {
		this.pendaftaran = pendaftaran;
	}
	
}

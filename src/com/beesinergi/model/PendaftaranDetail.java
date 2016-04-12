package com.beesinergi.model;

public class PendaftaranDetail {

    private Integer pkPendaftaranDetail;
    private Integer fkPendaftaran;
    private Integer fkPelajaran;
    private Integer nilai;
    
    private String namaPelajaran;
    
	public Integer getPkPendaftaranDetail() {
		return pkPendaftaranDetail;
	}
	public void setPkPendaftaranDetail(Integer pkPendaftaranDetail) {
		this.pkPendaftaranDetail = pkPendaftaranDetail;
	}
	public Integer getFkPendaftaran() {
		return fkPendaftaran;
	}
	public void setFkPendaftaran(Integer fkPendaftaran) {
		this.fkPendaftaran = fkPendaftaran;
	}
	public Integer getFkPelajaran() {
		return fkPelajaran;
	}
	public void setFkPelajaran(Integer fkPelajaran) {
		this.fkPelajaran = fkPelajaran;
	}
	public Integer getNilai() {
		return nilai;
	}
	public void setNilai(Integer nilai) {
		this.nilai = nilai;
	}
	public String getNamaPelajaran() {
		return namaPelajaran;
	}
	public void setNamaPelajaran(String namaPelajaran) {
		this.namaPelajaran = namaPelajaran;
	}
    

}
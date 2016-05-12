package com.beesinergi.model;

import com.beesinergi.util.SystemConstant;

public class PenaltyType {
	private Integer id;
	private Integer tahunAjaranId;
	private Integer penaltyType;
	private Double nominal;
	
	private String tahunAjaran;
	
	public String getPenaltyTypeDescr() {
		return SystemConstant.PenaltyType().get(penaltyType);
	} 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTahunAjaranId() {
		return tahunAjaranId;
	}
	public void setTahunAjaranId(Integer tahunAjaranId) {
		this.tahunAjaranId = tahunAjaranId;
	}
	public Integer getPenaltyType() {
		return penaltyType;
	}
	public void setPenaltyType(Integer penaltyType) {
		this.penaltyType = penaltyType;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	public String getTahunAjaran() {
		return tahunAjaran;
	}
	public void setTahunAjaran(String tahunAjaran) {
		this.tahunAjaran = tahunAjaran;
	}
	
	
}

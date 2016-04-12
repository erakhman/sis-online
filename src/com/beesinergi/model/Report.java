package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class Report {
	
	private String reportType;
	private Date dateFrom;
	private Date dateTo;
	private Integer trxNoFrom;
	private Integer trxNoTo;
	private String supplier;
	private Integer fkCustomer;
	private Integer fkJaminan;
	private Integer fkBarang;
	private String status;
	private Integer fkLookup;
	
	private List<Integer> fkBarangList;
	private List<Integer> fkJaminanList;
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public Integer getFkCustomer() {
		return fkCustomer;
	}
	public void setFkCustomer(Integer fkCustomer) {
		this.fkCustomer = fkCustomer;
	}
	public Integer getFkJaminan() {
		return fkJaminan;
	}
	public void setFkJaminan(Integer fkJaminan) {
		this.fkJaminan = fkJaminan;
	}
	public Integer getFkBarang() {
		return fkBarang;
	}
	public void setFkBarang(Integer fkBarang) {
		this.fkBarang = fkBarang;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTrxNoFrom() {
		return trxNoFrom;
	}
	public void setTrxNoFrom(Integer trxNoFrom) {
		this.trxNoFrom = trxNoFrom;
	}
	public Integer getTrxNoTo() {
		return trxNoTo;
	}
	public void setTrxNoTo(Integer trxNoTo) {
		this.trxNoTo = trxNoTo;
	}
	public Integer getFkLookup() {
		return fkLookup;
	}
	public void setFkLookup(Integer fkLookup) {
		this.fkLookup = fkLookup;
	}
	public List<Integer> getFkBarangList() {
		return fkBarangList;
	}
	public void setFkBarangList(List<Integer> fkBarangList) {
		this.fkBarangList = fkBarangList;
	}
	public List<Integer> getFkJaminanList() {
		return fkJaminanList;
	}
	public void setFkJaminanList(List<Integer> fkJaminanList) {
		this.fkJaminanList = fkJaminanList;
	}
	
}

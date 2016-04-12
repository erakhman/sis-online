package com.beesinergi.model;

import java.util.Date;

public class Reservation {

    private Integer pkReservation;
    private Integer reservationType;
    private Integer fkLookup;
    private Date dateFrom;
    private Date dateTo;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String status;
    private Integer fkDriver;
    
    private String lookupName;
    
	public Integer getPkReservation() {
		return pkReservation;
	}
	public void setPkReservation(Integer pkReservation) {
		this.pkReservation = pkReservation;
	}
	public Integer getReservationType() {
		return reservationType;
	}
	public void setReservationType(Integer reservationType) {
		this.reservationType = reservationType;
	}
	public Integer getFkLookup() {
		return fkLookup;
	}
	public void setFkLookup(Integer fkLookup) {
		this.fkLookup = fkLookup;
	}
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLookupName() {
		return lookupName;
	}
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	public Integer getFkDriver() {
		return fkDriver;
	}
	public void setFkDriver(Integer fkDriver) {
		this.fkDriver = fkDriver;
	}
    

}
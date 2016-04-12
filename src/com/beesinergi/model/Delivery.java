package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class Delivery {

    private Integer pkDelivery;
    private String deliveryNo;
    private Integer fkPo;
    private Date deliveryDate;
    private String remarks;
    private String createdBy;
    private Date createdDate;
    private String changedBy;
    private Date changedDate;
    private String isEditable;
    
    private String poNo;
    
    private List<DeliveryDetail> deliveryDetailList;
    
	public Integer getPkDelivery() {
		return pkDelivery;
	}
	public void setPkDelivery(Integer pkDelivery) {
		this.pkDelivery = pkDelivery;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public Integer getFkPo() {
		return fkPo;
	}
	public void setFkPo(Integer fkPo) {
		this.fkPo = fkPo;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public List<DeliveryDetail> getDeliveryDetailList() {
		return deliveryDetailList;
	}
	public void setDeliveryDetailList(List<DeliveryDetail> deliveryDetailList) {
		this.deliveryDetailList = deliveryDetailList;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}
    

}
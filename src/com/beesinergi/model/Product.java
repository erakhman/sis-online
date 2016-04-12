package com.beesinergi.model;

import java.util.Date;

public class Product {
	
    private Integer pkProduct;
    private String productCode;
    private String productName;
    private Integer fkCategory;
    private Integer fkUnit;
    private Double basicPrice;
    private Double sellPrice;
    private Integer qty;
    private String remarks;
    private String merk;
    private String madeIn;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private String isActive;
    
    private String categoryName;
    private String unitName;
    
	public Integer getPkProduct() {
		return pkProduct;
	}
	public void setPkProduct(Integer pkProduct) {
		this.pkProduct = pkProduct;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getFkCategory() {
		return fkCategory;
	}
	public void setFkCategory(Integer fkCategory) {
		this.fkCategory = fkCategory;
	}
	public Integer getFkUnit() {
		return fkUnit;
	}
	public void setFkUnit(Integer fkUnit) {
		this.fkUnit = fkUnit;
	}
	public Double getBasicPrice() {
		return basicPrice;
	}
	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMerk() {
		return merk;
	}
	public void setMerk(String merk) {
		this.merk = merk;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
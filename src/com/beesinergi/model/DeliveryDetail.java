package com.beesinergi.model;

import java.util.List;

public class DeliveryDetail {

    private Integer pkDeliveryDetail;
    private Integer fkDelivery;
    private Integer fkProduct;
    private Double price;
    private Double total;
    private String remarks;
    private String isActive;
    private Integer qty;
    private Integer outstanding;
    
    private String productName;
    private String productUnit;
    private Integer productStock;
    private String fkCategory;
    private String categoryName;  
    
	public Integer getPkDeliveryDetail() {
		return pkDeliveryDetail;
	}
	public void setPkDeliveryDetail(Integer pkDeliveryDetail) {
		this.pkDeliveryDetail = pkDeliveryDetail;
	}
	public Integer getFkDelivery() {
		return fkDelivery;
	}
	public void setFkDelivery(Integer fkDelivery) {
		this.fkDelivery = fkDelivery;
	}
	public Integer getFkProduct() {
		return fkProduct;
	}
	public void setFkProduct(Integer fkProduct) {
		this.fkProduct = fkProduct;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Integer outstanding) {
		this.outstanding = outstanding;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFkCategory() {
		return fkCategory;
	}
	public void setFkCategory(String fkCategory) {
		this.fkCategory = fkCategory;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
    
}
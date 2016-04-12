package com.beesinergi.model;

public class PoOutDetail {

    private Integer pkPoOutDetail;
    private Integer fkPoOut;
    private Integer fkProduct;
    private Integer qty;
    private Integer reqQty;
    private Double price;
    private Double total;
    private String remarks;
    
    private String productName;
    private Integer productStock;
    private Integer fkCategory;
    private String categoryName;
    
	public Integer getPkPoOutDetail() {
		return pkPoOutDetail;
	}
	public void setPkPoOutDetail(Integer pkPoOutDetail) {
		this.pkPoOutDetail = pkPoOutDetail;
	}
	public Integer getFkPoOut() {
		return fkPoOut;
	}
	public void setFkPoOut(Integer fkPoOut) {
		this.fkPoOut = fkPoOut;
	}
	public Integer getFkProduct() {
		return fkProduct;
	}
	public void setFkProduct(Integer fkProduct) {
		this.fkProduct = fkProduct;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getProductStock() {
		return productStock;
	}
	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
	public Integer getReqQty() {
		return reqQty;
	}
	public void setReqQty(Integer reqQty) {
		this.reqQty = reqQty;
	}
    
}
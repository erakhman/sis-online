package com.beesinergi.model;

public class PurchaseDetail {

    private Integer pkPurchaseDetail;
    private Integer fkPurchase;
    private Integer fkProduct;
    private Integer qtyOrder;
    private Integer qty;
    private Double price;
    private Double total;
    private String remarks;
    
    private String productName;
    private Integer fkCategory;
    private String categoryName;
    
	public Integer getPkPurchaseDetail() {
		return pkPurchaseDetail;
	}
	public void setPkPurchaseDetail(Integer pkPurchaseDetail) {
		this.pkPurchaseDetail = pkPurchaseDetail;
	}
	public Integer getFkPurchase() {
		return fkPurchase;
	}
	public void setFkPurchase(Integer fkPurchase) {
		this.fkPurchase = fkPurchase;
	}
	public Integer getFkProduct() {
		return fkProduct;
	}
	public void setFkProduct(Integer fkProduct) {
		this.fkProduct = fkProduct;
	}
	public Integer getQtyOrder() {
		return qtyOrder;
	}
	public void setQtyOrder(Integer qtyOrder) {
		this.qtyOrder = qtyOrder;
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
    
    
}
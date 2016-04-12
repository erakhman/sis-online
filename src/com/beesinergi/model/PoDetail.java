package com.beesinergi.model;

public class PoDetail {

    private Integer pkPoDetail;
    private Integer fkPo;
    private Integer fkProduct;
    private Integer qty;
    private String remarks;
    private Double price;
    private Double total;
    private Integer outstanding;
    
    private String productName;
    private String productUnit;
    private String productStock;
    private Integer fkCategory;
    private String categoryName;
    
	public Integer getPkPoDetail() {
		return pkPoDetail;
	}
	public void setPkPoDetail(Integer pkPoDetail) {
		this.pkPoDetail = pkPoDetail;
	}
	public Integer getFkPo() {
		return fkPo;
	}
	public void setFkPo(Integer fkPo) {
		this.fkPo = fkPo;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Integer outstanding) {
		this.outstanding = outstanding;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductStock() {
		return productStock;
	}
	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}
    
    

}
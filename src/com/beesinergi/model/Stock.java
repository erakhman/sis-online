package com.beesinergi.model;

import java.util.Date;
import java.util.List;

public class Stock {

    private Integer pkStock;
    private Integer fkProduct;
    private Date trxDate;
    private String trxNo;
    private String trxType;
    private String remarks;
    private Integer trxQty;
    private Integer endStock;
    private Integer startStock;
    private Double price;
    private String createdBy;
    private Date createdDate;
    
    private String productName;
    
    private List<Stock> productList;
    
	public Integer getPkStock() {
		return pkStock;
	}
	public void setPkStock(Integer pkStock) {
		this.pkStock = pkStock;
	}
	public Integer getFkProduct() {
		return fkProduct;
	}
	public void setFkProduct(Integer fkProduct) {
		this.fkProduct = fkProduct;
	}
	public Date getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}
	public String getTrxNo() {
		return trxNo;
	}
	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getTrxQty() {
		return trxQty;
	}
	public void setTrxQty(Integer trxQty) {
		this.trxQty = trxQty;
	}
	public Integer getEndStock() {
		return endStock;
	}
	public void setEndStock(Integer endStock) {
		this.endStock = endStock;
	}
	public Integer getStartStock() {
		return startStock;
	}
	public void setStartStock(Integer startStock) {
		this.startStock = startStock;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<Stock> getProductList() {
		return productList;
	}
	public void setProductList(List<Stock> productList) {
		this.productList = productList;
	}

}
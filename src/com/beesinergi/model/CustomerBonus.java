package com.beesinergi.model;

public class CustomerBonus {
 
    private Integer pkCustomerBonus;
    private Double nominal;
    private Integer fkCustomer;
    private Integer fkPenjualan;
    
	public Integer getPkCustomerBonus() {
		return pkCustomerBonus;
	}
	public void setPkCustomerBonus(Integer pkCustomerBonus) {
		this.pkCustomerBonus = pkCustomerBonus;
	}
	public Double getNominal() {
		return nominal;
	}
	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	public Integer getFkCustomer() {
		return fkCustomer;
	}
	public void setFkCustomer(Integer fkCustomer) {
		this.fkCustomer = fkCustomer;
	}
	public Integer getFkPenjualan() {
		return fkPenjualan;
	}
	public void setFkPenjualan(Integer fkPenjualan) {
		this.fkPenjualan = fkPenjualan;
	}
    
}
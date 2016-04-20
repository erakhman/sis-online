package com.beesinergi.model;

public class CoaType {
	
	private Integer pkCoaType;
	private String coaTypeName;
	private Integer naturalBalance;
	private String naturalBalanceDesc;
	
	public String getNaturalBalanceDesc() {
		return naturalBalanceDesc;
	}
	public void setNaturalBalanceDesc(String naturalBalanceDesc) {
		this.naturalBalanceDesc = naturalBalanceDesc;
	}
	public Integer getPkCoaType() {
		return pkCoaType;
	}
	public void setPkCoaType(Integer pkCoaType) {
		this.pkCoaType = pkCoaType;
	}
	public String getCoaTypeName() {
		return coaTypeName;
	}
	public void setCoaTypeName(String coaTypeName) {
		this.coaTypeName = coaTypeName;
	}
	public Integer getNaturalBalance() {
		return naturalBalance;
	}
	public void setNaturalBalance(Integer naturalBalance) {
		this.naturalBalance = naturalBalance;
	}
	
	

}

package com.beesinergi.model;



public class BookCategory {

    private Integer pkBookCategory;
    private String categoryCode;
    private String categoryDescription;
    
	public Integer getPkBookCategory() {
		return pkBookCategory;
	}
	public void setPkBookCategory(Integer pkBookCategory) {
		this.pkBookCategory = pkBookCategory;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
    
    
}
package com.beesinergi.model;

public class BookLocation {
	private Integer id;
	private Integer shelfId;
	private String code;
	private String locationDescription;
	
	public Integer getShelfId() {
		return shelfId;
	}
	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}

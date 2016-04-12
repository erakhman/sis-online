package com.beesinergi.model;

public class AppUserRole {

    private Integer pkUserRole;
    private Integer fkUser;
    private Integer fkRole;
    
	public Integer getPkUserRole() {
		return pkUserRole;
	}
	public void setPkUserRole(Integer pkUserRole) {
		this.pkUserRole = pkUserRole;
	}
	public Integer getFkUser() {
		return fkUser;
	}
	public void setFkUser(Integer fkUser) {
		this.fkUser = fkUser;
	}
	public Integer getFkRole() {
		return fkRole;
	}
	public void setFkRole(Integer fkRole) {
		this.fkRole = fkRole;
	}

}
package com.beesinergi.model;

import java.util.Date;
import java.util.List;

import com.beesinergi.util.SystemConstant;

public class Staff {
	
	private Integer pkStaff;
	private String code;
	private String name;
	private Date dob; 
	private String sex;
	private String maritalStatus;
	private String address;
	private String phoneNo;
	private Date joinDate;
	private Integer type;
	private Integer status;
	
	private List<Integer> fkClassList;
	private List<ClassHistory> classHistoryList;
	
	
	public String getTypeDescr() {
		return SystemConstant.StaffType().get(type);
	}

	public String getStatusDescr() {
		return SystemConstant.StaffStatus().get(status);
	}
	
	public Integer getPkStaff() {
		return pkStaff;
	}
	public void setPkStaff(Integer pkStaff) {
		this.pkStaff = pkStaff;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Integer> getFkClassList() {
		return fkClassList;
	}

	public void setFkClassList(List<Integer> fkClassList) {
		this.fkClassList = fkClassList;
	}

	public List<ClassHistory> getClassHistoryList() {
		return classHistoryList;
	}

	public void setClassHistoryList(List<ClassHistory> classHistoryList) {
		this.classHistoryList = classHistoryList;
	}

}

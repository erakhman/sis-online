package com.beesinergi.model;

import java.util.List;

public class StudentClass {
	
	private Integer pkStudentClass;
	private Integer fkClassHistory;
	private Integer fkStudent;
	
	private List<Integer> pkStudentList;
	
	public Integer getPkStudentClass() {
		return pkStudentClass;
	}
	public void setPkStudentClass(Integer pkStudentClass) {
		this.pkStudentClass = pkStudentClass;
	}
	public Integer getFkClassHistory() {
		return fkClassHistory;
	}
	public void setFkClassHistory(Integer fkClassHistory) {
		this.fkClassHistory = fkClassHistory;
	}
	public Integer getFkStudent() {
		return fkStudent;
	}
	public void setFkStudent(Integer fkStudent) {
		this.fkStudent = fkStudent;
	}
	public List<Integer> getPkStudentList() {
		return pkStudentList;
	}
	public void setPkStudentList(List<Integer> pkStudentList) {
		this.pkStudentList = pkStudentList;
	}
	
}

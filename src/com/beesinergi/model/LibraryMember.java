package com.beesinergi.model;

import java.util.Date;

import com.beesinergi.util.SystemConstant;

public class LibraryMember {

    private Integer pkLibraryMember;
    private String memberCode;
    private String memberName;
    private Integer memberType;
    private String memberIdentityNo;
    private Integer memberStatus;
    private Date startDate;
    private Date endDate;
    private Integer annualFeeStatus;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private Date suspendDate;
    private Date endSuspendDate;
    
    public String getMemberTypeDescr() {
    	return SystemConstant.MemberType().get(memberType);
    }
    
    public String getMemberStatusDescr() {
    	return SystemConstant.MemberStatus().get(memberStatus);
    }
    
	public Integer getPkLibraryMember() {
		return pkLibraryMember;
	}
	public void setPkLibraryMember(Integer pkLibraryMember) {
		this.pkLibraryMember = pkLibraryMember;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	public String getMemberIdentityNo() {
		return memberIdentityNo;
	}
	public void setMemberIdentityNo(String memberIdentityNo) {
		this.memberIdentityNo = memberIdentityNo;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getAnnualFeeStatus() {
		return annualFeeStatus;
	}
	public void setAnnualFeeStatus(Integer annualFeeStatus) {
		this.annualFeeStatus = annualFeeStatus;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public Date getSuspendDate() {
		return suspendDate;
	}
	public void setSuspendDate(Date suspendDate) {
		this.suspendDate = suspendDate;
	}
	public Date getEndSuspendDate() {
		return endSuspendDate;
	}
	public void setEndSuspendDate(Date endSuspendDate) {
		this.endSuspendDate = endSuspendDate;
	}
    
	
    
}
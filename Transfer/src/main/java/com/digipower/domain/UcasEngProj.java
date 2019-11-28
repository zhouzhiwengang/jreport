package com.digipower.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UcasEngProj implements Serializable {
    private String sid;

    private String engProjName;

    private String engProjNo;

    private String approvalNo;

    private String approvalUnit;

    private String recordProjNo;

    private String createdBy;

    private Date createdDt;

    private String updatedBy;

    private Date updatedDt;
    
    // 补充字段
    private String deleteFlag;
    
    public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getEngProjName() {
        return engProjName;
    }

    public void setEngProjName(String engProjName) {
        this.engProjName = engProjName == null ? null : engProjName.trim();
    }

    public String getEngProjNo() {
        return engProjNo;
    }

    public void setEngProjNo(String engProjNo) {
        this.engProjNo = engProjNo == null ? null : engProjNo.trim();
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo == null ? null : approvalNo.trim();
    }

    public String getApprovalUnit() {
        return approvalUnit;
    }

    public void setApprovalUnit(String approvalUnit) {
        this.approvalUnit = approvalUnit == null ? null : approvalUnit.trim();
    }

    public String getRecordProjNo() {
        return recordProjNo;
    }

    public void setRecordProjNo(String recordProjNo) {
        this.recordProjNo = recordProjNo == null ? null : recordProjNo.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

	@Override
	public String toString() {
		return "UcasEngProj [sid=" + sid + ", engProjName=" + engProjName + ", engProjNo=" + engProjNo + ", approvalNo="
				+ approvalNo + ", approvalUnit=" + approvalUnit + ", recordProjNo=" + recordProjNo + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", updatedBy=" + updatedBy + ", updatedDt=" + updatedDt
				+ ", deleteFlag=" + deleteFlag + "]";
	}
}
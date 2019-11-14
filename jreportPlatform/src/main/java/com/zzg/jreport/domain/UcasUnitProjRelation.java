package com.zzg.jreport.domain;

public class UcasUnitProjRelation {
    private String sid;

    private String unitProjSid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getUnitProjSid() {
        return unitProjSid;
    }

    public void setUnitProjSid(String unitProjSid) {
        this.unitProjSid = unitProjSid == null ? null : unitProjSid.trim();
    }
}
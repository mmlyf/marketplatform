package com.mtpt.alibean;

import java.util.Date;

public class TBLabel {
    private Integer id;

    private String bqName;

    private Date adTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBqName() {
        return bqName;
    }

    public void setBqName(String bqName) {
        this.bqName = bqName == null ? null : bqName.trim();
    }

    public Date getAdTime() {
        return adTime;
    }

    public void setAdTime(Date adTime) {
        this.adTime = adTime;
    }
}
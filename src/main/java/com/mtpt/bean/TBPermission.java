package com.mtpt.bean;

public class TBPermission {
    private Integer id;

    private Integer perId;

    private String perDes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerDes() {
        return perDes;
    }

    public void setPerDes(String perDes) {
        this.perDes = perDes == null ? null : perDes.trim();
    }
}
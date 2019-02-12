package com.mtpt.bean;

public class TBProd {
    private Integer proid;

    private String proname;

    private Integer lxid;

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public Integer getLxid() {
        return lxid;
    }

    public void setLxid(Integer lxid) {
        this.lxid = lxid;
    }
}
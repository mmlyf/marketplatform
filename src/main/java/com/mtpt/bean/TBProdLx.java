package com.mtpt.bean;

public class TBProdLx {
    private Integer lxid;

    private String lxname;

    private String lxvalue;

    public Integer getLxid() {
        return lxid;
    }

    public void setLxid(Integer lxid) {
        this.lxid = lxid;
    }

    public String getLxname() {
        return lxname;
    }

    public void setLxname(String lxname) {
        this.lxname = lxname == null ? null : lxname.trim();
    }

    public String getLxvalue() {
        return lxvalue;
    }

    public void setLxvalue(String lxvalue) {
        this.lxvalue = lxvalue == null ? null : lxvalue.trim();
    }
}
package com.mtpt.alibean;

import java.util.Date;

public class TBOrderFlowgift {
    private Integer id;

    private String dn;

    private String orderFlowno;

    private String prodName;

    private Integer isGift;

    private Date zsAddtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn == null ? null : dn.trim();
    }

    public String getOrderFlowno() {
        return orderFlowno;
    }

    public void setOrderFlowno(String orderFlowno) {
        this.orderFlowno = orderFlowno == null ? null : orderFlowno.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public Integer getIsGift() {
        return isGift;
    }

    public void setIsGift(Integer isGift) {
        this.isGift = isGift;
    }

    public Date getZsAddtime() {
        return zsAddtime;
    }

    public void setZsAddtime(Date zsAddtime) {
        this.zsAddtime = zsAddtime;
    }
}
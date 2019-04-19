package com.mtpt.alibean;

import java.util.Date;

public class TBSecondConfirm {
    private Integer id;

    private String dn;

    private String agw;

    private Date orderTime;

    private String code;

    private String content;

    private String confirmTime;

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

    public String getAgw() {
        return agw;
    }

    public void setAgw(String agw) {
        this.agw = agw == null ? null : agw.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime == null ? null : confirmTime.trim();
    }
}
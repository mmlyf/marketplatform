package com.mtpt.bean;

import java.util.Date;

public class FTPDetails {
    private String id;

    private String mobile;

    private Integer amount;

    private Date addtime;

    private Date modifytime;

    private Integer datastate;

    private Integer status;

    private String flowtype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getDatastate() {
        return datastate;
    }

    public void setDatastate(Integer datastate) {
        this.datastate = datastate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype == null ? null : flowtype.trim();
    }
}
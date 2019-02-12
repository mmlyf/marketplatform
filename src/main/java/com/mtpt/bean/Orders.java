package com.mtpt.bean;

import java.util.Date;

public class Orders {
    private String id;

    private String serialno;

    private String productid;

    private Date purchasetime;

    private Date confirmtime;

    private Integer bssstate;

    private Integer status;

    private Long buytime;

    private String userid;

    private String transactionid;

    private Date addtime;

    private Date modifytime;

    private Integer datastate;

    private String source;

    private String userfrom;

    private int lsource;

    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno == null ? null : serialno.trim();
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public Date getPurchasetime() {
        return purchasetime;
    }

    public void setPurchasetime(Date purchasetime) {
        this.purchasetime = purchasetime;
    }

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    public Integer getBssstate() {
        return bssstate;
    }

    public void setBssstate(Integer bssstate) {
        this.bssstate = bssstate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBuytime() {
        return buytime;
    }

    public void setBuytime(Long buytime) {
        this.buytime = buytime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid == null ? null : transactionid.trim();
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(String userfrom) {
        this.userfrom = userfrom == null ? null : userfrom.trim();
    }

    public int getLsource() {
        return lsource;
    }

    public void setLsource(int lsource) {
        this.lsource = lsource;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}
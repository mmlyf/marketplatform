package com.mtpt.alibean;

public class TBFlowredeveTotal {
    private Integer id;

    private Integer pv;

    private Integer lijiBc;

    private Integer comfirBc;

    private Integer txCount;

    private Integer ppCount;

    private Integer aqyCount;

    private Integer ykCount;

    private Integer ordersucCount;

    private Integer orderunsucCount;

    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getLijiBc() {
        return lijiBc;
    }

    public void setLijiBc(Integer lijiBc) {
        this.lijiBc = lijiBc;
    }

    public Integer getComfirBc() {
        return comfirBc;
    }

    public void setComfirBc(Integer comfirBc) {
        this.comfirBc = comfirBc;
    }

    public Integer getTxCount() {
        return txCount;
    }

    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    public Integer getPpCount() {
        return ppCount;
    }

    public void setPpCount(Integer ppCount) {
        this.ppCount = ppCount;
    }

    public Integer getAqyCount() {
        return aqyCount;
    }

    public void setAqyCount(Integer aqyCount) {
        this.aqyCount = aqyCount;
    }

    public Integer getYkCount() {
        return ykCount;
    }

    public void setYkCount(Integer ykCount) {
        this.ykCount = ykCount;
    }

    public Integer getOrdersucCount() {
        return ordersucCount;
    }

    public void setOrdersucCount(Integer ordersucCount) {
        this.ordersucCount = ordersucCount;
    }

    public Integer getOrderunsucCount() {
        return orderunsucCount;
    }

    public void setOrderunsucCount(Integer orderunsucCount) {
        this.orderunsucCount = orderunsucCount;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
}
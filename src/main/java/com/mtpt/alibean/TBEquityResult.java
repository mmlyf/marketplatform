package com.mtpt.alibean;

import java.util.Date;

public class TBEquityResult {
    private Integer id;

    private String qyNum;

    private String cjDn;

    private String zsTime;

    private String zsState;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQyNum() {
        return qyNum;
    }

    public void setQyNum(String qyNum) {
        this.qyNum = qyNum == null ? null : qyNum.trim();
    }

    public String getCjDn() {
        return cjDn;
    }

    public void setCjDn(String cjDn) {
        this.cjDn = cjDn == null ? null : cjDn.trim();
    }

    public String getZsTime() {
        return zsTime;
    }

    public void setZsTime(String zsTime) {
        this.zsTime = zsTime == null ? null : zsTime.trim();
    }

    public String getZsState() {
        return zsState;
    }

    public void setZsState(String zsState) {
        this.zsState = zsState == null ? null : zsState.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
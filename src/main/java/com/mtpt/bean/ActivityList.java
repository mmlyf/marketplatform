package com.mtpt.bean;

import java.util.Date;

public class ActivityList {
    private Integer id;

    private String actName;

    private Date actStarttime;

    private Date actEndtime;

    private String actPage;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public Date getActStarttime() {
        return actStarttime;
    }

    public void setActStarttime(Date actStarttime) {
        this.actStarttime = actStarttime;
    }

    public Date getActEndtime() {
        return actEndtime;
    }

    public void setActEndtime(Date actEndtime) {
        this.actEndtime = actEndtime;
    }

    public String getActPage() {
        return actPage;
    }

    public void setActPage(String actPage) {
        this.actPage = actPage == null ? null : actPage.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
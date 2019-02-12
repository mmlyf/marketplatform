package com.mtpt.alibean;

import java.util.Date;

public class TBSceneJob {
    private Integer id;

    private String sceneBq;

    private String bqOpera;

    private String isTimework;

    private Date workTime;

    private String isDelblack;

    private String isDeldays;

    private Integer deldays;

    private Integer dataCount;

    private String addMan;

    private Date addTime;

    private String reviewMan;

    private Integer misId;

    private Integer state;

    private String lastOpera;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSceneBq() {
        return sceneBq;
    }

    public void setSceneBq(String sceneBq) {
        this.sceneBq = sceneBq == null ? null : sceneBq.trim();
    }

    public String getBqOpera() {
        return bqOpera;
    }

    public void setBqOpera(String bqOpera) {
        this.bqOpera = bqOpera == null ? null : bqOpera.trim();
    }

    public String getIsTimework() {
        return isTimework;
    }

    public void setIsTimework(String isTimework) {
        this.isTimework = isTimework == null ? null : isTimework.trim();
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getIsDelblack() {
        return isDelblack;
    }

    public void setIsDelblack(String isDelblack) {
        this.isDelblack = isDelblack == null ? null : isDelblack.trim();
    }

    public String getIsDeldays() {
        return isDeldays;
    }

    public void setIsDeldays(String isDeldays) {
        this.isDeldays = isDeldays == null ? null : isDeldays.trim();
    }

    public Integer getDeldays() {
        return deldays;
    }

    public void setDeldays(Integer deldays) {
        this.deldays = deldays;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public String getAddMan() {
        return addMan;
    }

    public void setAddMan(String addMan) {
        this.addMan = addMan == null ? null : addMan.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getReviewMan() {
        return reviewMan;
    }

    public void setReviewMan(String reviewMan) {
        this.reviewMan = reviewMan == null ? null : reviewMan.trim();
    }

    public Integer getMisId() {
        return misId;
    }

    public void setMisId(Integer misId) {
        this.misId = misId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLastOpera() {
        return lastOpera;
    }

    public void setLastOpera(String lastOpera) {
        this.lastOpera = lastOpera == null ? null : lastOpera.trim();
    }
}
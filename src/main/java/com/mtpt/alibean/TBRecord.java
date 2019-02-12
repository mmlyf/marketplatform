package com.mtpt.alibean;

import java.util.Date;

public class TBRecord {
    private Integer id;

    private String groupname;

    private String newfilename;

    private String filename;

    private Integer filenum;

    private String addman;

    private Date addtime;

    private String lastwork;

    private Date endtime;

    private String istimework;

    private Date worktime;

    private String reviewman;

    private Integer state;

    private String isdelblack;

    private Integer deldays;

    private String isdeldays;

    private Integer migId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getNewfilename() {
        return newfilename;
    }

    public void setNewfilename(String newfilename) {
        this.newfilename = newfilename == null ? null : newfilename.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getFilenum() {
        return filenum;
    }

    public void setFilenum(Integer filenum) {
        this.filenum = filenum;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getLastwork() {
        return lastwork;
    }

    public void setLastwork(String lastwork) {
        this.lastwork = lastwork == null ? null : lastwork.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getIstimework() {
        return istimework;
    }

    public void setIstimework(String istimework) {
        this.istimework = istimework == null ? null : istimework.trim();
    }

    public Date getWorktime() {
        return worktime;
    }

    public void setWorktime(Date worktime) {
        this.worktime = worktime;
    }

    public String getReviewman() {
        return reviewman;
    }

    public void setReviewman(String reviewman) {
        this.reviewman = reviewman == null ? null : reviewman.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIsdelblack() {
        return isdelblack;
    }

    public void setIsdelblack(String isdelblack) {
        this.isdelblack = isdelblack == null ? null : isdelblack.trim();
    }

    public Integer getDeldays() {
        return deldays;
    }

    public void setDeldays(Integer deldays) {
        this.deldays = deldays;
    }

    public String getIsdeldays() {
        return isdeldays;
    }

    public void setIsdeldays(String isdeldays) {
        this.isdeldays = isdeldays == null ? null : isdeldays.trim();
    }

    public Integer getMigId() {
        return migId;
    }

    public void setMigId(Integer migId) {
        this.migId = migId;
    }
}
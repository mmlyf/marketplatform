package com.mtpt.alibean;

public class TBHfczReview {
    private Integer id;

    private String seNo;

    private String czDn;

    private Double czAmount;

    private String czReason;

    private String czAddtime;

    private String czReviewer;

    private String czAddman;

    private Integer czReviewstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeNo() {
        return seNo;
    }

    public void setSeNo(String seNo) {
        this.seNo = seNo == null ? null : seNo.trim();
    }

    public String getCzDn() {
        return czDn;
    }

    public void setCzDn(String czDn) {
        this.czDn = czDn == null ? null : czDn.trim();
    }

    public Double getCzAmount() {
        return czAmount;
    }

    public void setCzAmount(Double czAmount) {
        this.czAmount = czAmount;
    }

    public String getCzReason() {
        return czReason;
    }

    public void setCzReason(String czReason) {
        this.czReason = czReason == null ? null : czReason.trim();
    }

    public String getCzAddtime() {
        return czAddtime;
    }

    public void setCzAddtime(String czAddtime) {
        this.czAddtime = czAddtime == null ? null : czAddtime.trim();
    }

    public String getCzReviewer() {
        return czReviewer;
    }

    public void setCzReviewer(String czReviewer) {
        this.czReviewer = czReviewer == null ? null : czReviewer.trim();
    }

    public String getCzAddman() {
        return czAddman;
    }

    public void setCzAddman(String czAddman) {
        this.czAddman = czAddman == null ? null : czAddman.trim();
    }

    public Integer getCzReviewstate() {
        return czReviewstate;
    }

    public void setCzReviewstate(Integer czReviewstate) {
        this.czReviewstate = czReviewstate;
    }
}
package com.mtpt.alibean;

import java.util.Date;

public class TBReview {
    private Integer id;

    private String city;

    private String sourceType;

    private String prelx;

    private String product;

    private String dangw;

    private String ifrh;

    private String ifdx;

    private Integer migId;

    private String rdUser;

    private Integer count;

    private Date secTime;

    private Integer state;

    private Date addTime;

    private String isdelblack;

    private Integer deldays;

    private String isitmework;

    private Date worktime;

    private String isdeldays;

    private String reUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getPrelx() {
        return prelx;
    }

    public void setPrelx(String prelx) {
        this.prelx = prelx == null ? null : prelx.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getDangw() {
        return dangw;
    }

    public void setDangw(String dangw) {
        this.dangw = dangw == null ? null : dangw.trim();
    }

    public String getIfrh() {
        return ifrh;
    }

    public void setIfrh(String ifrh) {
        this.ifrh = ifrh == null ? null : ifrh.trim();
    }

    public String getIfdx() {
        return ifdx;
    }

    public void setIfdx(String ifdx) {
        this.ifdx = ifdx == null ? null : ifdx.trim();
    }

    public Integer getMigId() {
        return migId;
    }

    public void setMigId(Integer migId) {
        this.migId = migId;
    }

    public String getRdUser() {
        return rdUser;
    }

    public void setRdUser(String rdUser) {
        this.rdUser = rdUser == null ? null : rdUser.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getSecTime() {
        return secTime;
    }

    public void setSecTime(Date secTime) {
        this.secTime = secTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public String getIsitmework() {
        return isitmework;
    }

    public void setIsitmework(String isitmework) {
        this.isitmework = isitmework == null ? null : isitmework.trim();
    }

    public Date getWorktime() {
        return worktime;
    }

    public void setWorktime(Date worktime) {
        this.worktime = worktime;
    }

    public String getIsdeldays() {
        return isdeldays;
    }

    public void setIsdeldays(String isdeldays) {
        this.isdeldays = isdeldays == null ? null : isdeldays.trim();
    }

    public String getReUser() {
        return reUser;
    }

    public void setReUser(String reUser) {
        this.reUser = reUser == null ? null : reUser.trim();
    }
}
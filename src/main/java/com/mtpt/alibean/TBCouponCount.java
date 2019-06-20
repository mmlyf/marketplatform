package com.mtpt.alibean;

public class TBCouponCount {
    private Integer id;

    private Integer pv;

    private Integer coupon;

    private Integer pvD;

    private Integer pu;

    private String xingCoupon;

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

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Integer getPvD() {
        return pvD;
    }

    public void setPvD(Integer pvD) {
        this.pvD = pvD;
    }

    public Integer getPu() {
        return pu;
    }

    public void setPu(Integer pu) {
        this.pu = pu;
    }

    public String getXingCoupon() {
        return xingCoupon;
    }

    public void setXingCoupon(String xingCoupon) {
        this.xingCoupon = xingCoupon == null ? null : xingCoupon.trim();
    }
}
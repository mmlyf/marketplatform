package com.mtpt.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Zfbcz {
    private String pyId;

    private String pyDn;

    private String pySeno;

    private Date pyCreatime;

    private String pyLastday;

    private BigDecimal pyTotalmoey;

    private int pyZfqd;

    private int pyIfpay;

    private Date pyOftime;

    private Byte pyOfcode;

    private String pyOfspid;

    private String pyOftitle;

    public String getPyId() {
        return pyId;
    }

    public void setPyId(String pyId) {
        this.pyId = pyId == null ? null : pyId.trim();
    }

    public String getPyDn() {
        return pyDn;
    }

    public void setPyDn(String pyDn) {
        this.pyDn = pyDn == null ? null : pyDn.trim();
    }

    public String getPySeno() {
        return pySeno;
    }

    public void setPySeno(String pySeno) {
        this.pySeno = pySeno == null ? null : pySeno.trim();
    }

    public Date getPyCreatime() {
        return pyCreatime;
    }

    public void setPyCreatime(Date pyCreatime) {
        this.pyCreatime = pyCreatime;
    }

    public String getPyLastday() {
        return pyLastday;
    }

    public void setPyLastday(String pyLastday) {
        this.pyLastday = pyLastday == null ? null : pyLastday.trim();
    }

    public BigDecimal getPyTotalmoey() {
        return pyTotalmoey;
    }

    public void setPyTotalmoey(BigDecimal pyTotalmoey) {
        this.pyTotalmoey = pyTotalmoey;
    }

    public int getPyZfqd() {
        return pyZfqd;
    }

    public void setPyZfqd(int pyZfqd) {
        this.pyZfqd = pyZfqd;
    }

    public int getPyIfpay() {
        return pyIfpay;
    }

    public void setPyIfpay(int pyIfpay) {
        this.pyIfpay = pyIfpay;
    }

    public Date getPyOftime() {
        return pyOftime;
    }

    public void setPyOftime(Date pyOftime) {
        this.pyOftime = pyOftime;
    }

    public Byte getPyOfcode() {
        return pyOfcode;
    }

    public void setPyOfcode(Byte pyOfcode) {
        this.pyOfcode = pyOfcode;
    }

    public String getPyOfspid() {
        return pyOfspid;
    }

    public void setPyOfspid(String pyOfspid) {
        this.pyOfspid = pyOfspid == null ? null : pyOfspid.trim();
    }

    public String getPyOftitle() {
        return pyOftitle;
    }

    public void setPyOftitle(String pyOftitle) {
        this.pyOftitle = pyOftitle == null ? null : pyOftitle.trim();
    }
}
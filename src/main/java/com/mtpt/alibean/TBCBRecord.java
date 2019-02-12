package com.mtpt.alibean;

import java.util.Date;

public class TBCBRecord {
    private Integer cdId;

    private Integer rdId;

    private String cdTell;

    private Date cdSendtime;

    private Integer cdSendstat;

    private Integer sendflag;

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getRdId() {
        return rdId;
    }

    public void setRdId(Integer rdId) {
        this.rdId = rdId;
    }

    public String getCdTell() {
        return cdTell;
    }

    public void setCdTell(String cdTell) {
        this.cdTell = cdTell == null ? null : cdTell.trim();
    }

    public Date getCdSendtime() {
        return cdSendtime;
    }

    public void setCdSendtime(Date cdSendtime) {
        this.cdSendtime = cdSendtime;
    }

    public Integer getCdSendstat() {
        return cdSendstat;
    }

    public void setCdSendstat(Integer cdSendstat) {
        this.cdSendstat = cdSendstat;
    }

    public Integer getSendflag() {
        return sendflag;
    }

    public void setSendflag(Integer sendflag) {
        this.sendflag = sendflag;
    }
}
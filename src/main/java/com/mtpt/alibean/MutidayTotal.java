package com.mtpt.alibean;

public class MutidayTotal {
    private Integer id;

    private Integer pv;

    private Integer bc;

    private Integer sixOrderc;

    private Integer nineOrderc;

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

    public Integer getBc() {
        return bc;
    }

    public void setBc(Integer bc) {
        this.bc = bc;
    }

    public Integer getSixOrderc() {
        return sixOrderc;
    }

    public void setSixOrderc(Integer sixOrderc) {
        this.sixOrderc = sixOrderc;
    }

    public Integer getNineOrderc() {
        return nineOrderc;
    }

    public void setNineOrderc(Integer nineOrderc) {
        this.nineOrderc = nineOrderc;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
}
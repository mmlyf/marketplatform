package com.mtpt.alibean;

import java.util.Date;

public class TBEquityData {
    private Integer id;

    private String qyId;

    private String dn;

    private String qyName;

    private Integer source;

    private Date addtime;
    
    private TBEquityResult resultdata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQyId() {
        return qyId;
    }

    public void setQyId(String qyId) {
        this.qyId = qyId == null ? null : qyId.trim();
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn == null ? null : dn.trim();
    }

    public String getQyName() {
        return qyName;
    }

    public void setQyName(String qyName) {
        this.qyName = qyName == null ? null : qyName.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public TBEquityResult getResultdata() {
		return resultdata;
	}

	public void setResultdata(TBEquityResult resultdata) {
		this.resultdata = resultdata;
	}
    
    
}
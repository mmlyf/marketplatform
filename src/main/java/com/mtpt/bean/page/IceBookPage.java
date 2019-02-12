package com.mtpt.bean.page;

public class IceBookPage {
	private Integer page = 1;
	private Integer limit = 10;
	private Integer totalRecord;
	private Integer totalPage;
	private String dn;
	private String date_star;
	private String date_end;
	private String systype;
	private String qudao;
	private String ifdx;
	private String ifrh;
	private Integer start;
	
	public IceBookPage() {}
	public Integer getStart() {
		start=(page-1)*limit;
        return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotalPage() {
		totalPage=(totalRecord-1)/limit+1;
        return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getDate_star() {
		return date_star;
	}
	public void setDate_star(String date_star) {
		this.date_star = date_star;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getQudao() {
		return qudao;
	}
	public void setQudao(String qudao) {
		this.qudao = qudao;
	}
	public String getSystype() {
		return systype;
	}
	public void setSystype(String systype) {
		this.systype = systype;
	}
	public String getIfdx() {
		return ifdx;
	}
	public void setIfdx(String ifdx) {
		this.ifdx = ifdx;
	}
	public String getIfrh() {
		return ifrh;
	}
	public void setIfrh(String ifrh) {
		this.ifrh = ifrh;
	}
	
}

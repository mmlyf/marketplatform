package com.mtpt.alibean.page;

public class TBRecordPage {
	private Integer page = 1;
	private Integer limit = 10;
	private Integer totalRecord;
	private Integer totalPage;
	private String keytype;
	private String keyid;
	private String keyidtype;
	private String keyword;
	private String keystate;
	private Integer start;
	public TBRecordPage() {}
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
	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	public String getKeyidtype() {
		return keyidtype;
	}
	public void setKeyidtype(String keyidtype) {
		this.keyidtype = keyidtype;
	}
	public String getKeystate() {
		return keystate;
	}
	public void setKeystate(String keystate) {
		this.keystate = keystate;
	}
	
}

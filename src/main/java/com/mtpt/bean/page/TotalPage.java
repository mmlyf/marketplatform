package com.mtpt.bean.page;

public class TotalPage {
	private String adtime;
	private int state;
	private int flag;
	private Integer page = 1;
	private Integer limit = 10;
	private Integer delay;
	private Integer start;
	private Integer totalRecord;
	private Integer totalPage;
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
	public Integer getDelay() {
		return delay;
	}
	public void setDelay(Integer delay) {
		this.delay = delay;
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
	public Integer getStart() {
		start=(page-1)*limit;
        return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getAdtime() {
		return adtime;
	}
	public void setAdtime(String adtime) {
		this.adtime = adtime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}

package com.mtpt.bean.page;

public class SceneDataPage {
	private Integer page = 1;
	private Integer limit = 10;
	private Integer totalRecord;
	private Integer totalPage;
	private String bq;
	private String opera;
	private String type;
	private Integer start;
	public SceneDataPage() {}
	
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

	public String getBq() {
		return bq;
	}

	public void setBq(String bq) {
		this.bq = bq;
	}

	public String getOpera() {
		return opera;
	}

	public void setOpera(String opera) {
		this.opera = opera;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

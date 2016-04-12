package com.beesinergi.util;

import java.util.List;

public class Paging {

	private int page;
	private int totalRecord;
	private int totalPage;
	private int offset;
	private int limit;
	private List<Object> result;
	
	public Paging(){};
	
	public Paging(List result){
		this.result = result;
	};

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit == 0 ? SystemParameter.PAGE_LIMIT : limit;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
		offset = page*getLimit()-getLimit();
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}

}

package com.ssafy.sunbis.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class PageNavDto {
	int currentPageNo;
	int startPageNo;
	int endPageNo;
	boolean headRange;
	boolean tailRange;
	int totalPageCount;
	public PageNavDto() {
		super();
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	public boolean isHeadRange() {
		return headRange;
	}
	public void setHeadRange(boolean headRange) {
		this.headRange = headRange;
	}
	public boolean isTailRange() {
		return tailRange;
	}
	public void setTailRange(boolean tailRange) {
		this.tailRange = tailRange;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	@Override
	public String toString() {
		return "PageNavDto [currentPageNo=" + currentPageNo + ", startPageNo=" + startPageNo + ", endPageNo="
				+ endPageNo + ", headRange=" + headRange + ", tailRange=" + tailRange + ", totalPageCount="
				+ totalPageCount + "]";
	}
}

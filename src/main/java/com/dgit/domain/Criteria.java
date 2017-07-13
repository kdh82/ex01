package com.dgit.domain;

public class Criteria {
	private int page = 1;
	private int perPageNum = 10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart(){
		// 해당 page의  제일 첫번째 게시물 [index : 0번부터 시작됨]를 구함
		return  (page -1) * this.perPageNum; 
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

	
	
}

package com.dgit.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount; // 전체 게시물 갯수
	private int startPage; // 화면에 나타날 시작 페이지 번호
	private int endPage; // 화면에 나타날 끝 페이지 번호
	private boolean prev; // 이전 페이지 존재 여부
	private boolean next; // 다음 페이지 존재여부
	private int displayPageNum = 10; // 화면에 보일 페이지 번호의 총 갯수
	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcDate();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void calcDate() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		int tempEndPage = (int) Math.ceil(totalCount / (double) cri.getPerPageNum());
		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		if(startPage == 1){
			prev = false;
		}else{
			prev = true;
		}
		
		if(endPage * cri.getPerPageNum() >= totalCount){
			next = false;
		}else{
			next = true;
		}
		
	}

	@Override
	public String toString() {
		return String.format(
				"PageMaker [totalCount=%s, startPage=%s, endPage=%s, prev=%s, next=%s, displayPageNum=%s, cri=%s]",
				totalCount, startPage, endPage, prev, next, displayPageNum, cri);
	}

	public String makeSearch(int page){
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		System.out.println(uri.toUriString()); // ?page-2&perPageNum=10&searchType=t&keyword=aaa
		return uri.toUriString();
	}
	

}

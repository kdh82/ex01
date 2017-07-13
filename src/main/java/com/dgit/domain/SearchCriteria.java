package com.dgit.domain;

public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return String.format("SearchCriteria [searchType=%s, keyword=%s]", searchType, keyword);
	}
	
	
}

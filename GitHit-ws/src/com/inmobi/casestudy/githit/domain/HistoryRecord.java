package com.inmobi.casestudy.githit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
"id",
"git_handle",
"searched_by"
})
public class HistoryRecord {

	@JsonProperty("git_handle")
	private String gitHandle;
	
	@JsonProperty("searched_by")
	private String searchedBy;
	
	@JsonProperty("id")
	private Integer id;
	
	public HistoryRecord() {
		
	}

	@JsonProperty("git_handle")
	public String getGitHandle() {
		return gitHandle;
	}

	@JsonProperty("git_handle")
	public void setGitHandle(String gitHandle) {
		this.gitHandle = gitHandle;
	}

	@JsonProperty("searched_by")
	public String getSearchedBy() {
		return this.searchedBy;
	}
	
	@JsonProperty("searched_by")
	public void setSearchedBy(String searchedBy) {
		this.searchedBy = searchedBy;
	}
	
	@JsonProperty("id")
	public Integer getId() {
		return this.id;
	}
	
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
}

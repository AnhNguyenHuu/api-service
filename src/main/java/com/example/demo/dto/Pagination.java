package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Pagination implements Serializable{
	
	@JsonProperty("page")
	private Integer page;
	
	@JsonProperty("limit")
	private Integer limit;
	
	@JsonProperty("totalRows")
	private Long totalRows;
	
	public Pagination(Integer page, Integer limit, Long totalRows) {
		super();
		this.page = page;
		this.limit = limit;
		this.totalRows = totalRows;
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
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	
	
}

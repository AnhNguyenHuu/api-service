package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.React;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class ReactJson implements Serializable{
	
	@JsonProperty("data")
	private List<React> reacts;
	
	@JsonProperty("pagination")
	private Pagination pagination;

	public List<React> getReacts() {
		return reacts;
	}

	public void setReacts(List<React> reacts) {
		this.reacts = reacts;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
}

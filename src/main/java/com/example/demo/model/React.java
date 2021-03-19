package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "react")
public class React {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "published")
	private Integer published;
	
	@Transient
	private Integer page;
	
	@Transient
	private Integer size;
	
	
	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}


	public React() {
		super();
	}


	public React(String title, String description, Integer published) {
		super();
		this.title = title;
		this.description = description;
		this.published = published;
	}
	

	public React(Long id, String title, String description, Integer published) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.published = published;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPublished() {
		return published;
	}


	public void setPublished(Integer published) {
		this.published = published;
	}


	@Override
	public String toString() {
		return "React [id=" + id + ", title=" + title + ", description=" + description + ", published=" + published
				+ "]";
	}
}

package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto implements Serializable{
	@JsonProperty("id")
	private long id;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("token")
	private String token;

	
	public UserDto() {
		super();
	}

	public UserDto(long id, String username, String password, String token) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	 
}

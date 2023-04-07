package com.example.SecurityAndToken.dto;

import java.util.Date;

public class GetUserRespon {

	private String username;
	
	private String std;
	
	private String email;
	
	private Date dayUpdate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDayUpdate() {
		return dayUpdate;
	}

	public void setDayUpdate(Date dayUpdate) {
		this.dayUpdate = dayUpdate;
	}
	
	
}

package com.jds.epathshala.nbirest.rest.payload;

import com.jds.epathshala.persistenceservice.db.model.User;

public class UserInfo {
	private String name;
	private String email;
	private String mobileNo;

	public UserInfo(User user) {
		this.name = user.getName();
		this.mobileNo = user.getMobileNo();
		this.email = user.getEmail();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}

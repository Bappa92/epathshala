package com.jds.epathshala.nbirest.rest.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@NotNull(message = "Name can not be empty")
	private String name;

	@Email
	private String email;

	@NotNull(message = "Phone no is not proper")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobileNo;

	@NotNull(message = "Password is a required field")
	@Size(min = 8, max = 16, message = "Password must be equal to or greater than 8 characters and less than 16 characters")
	private String passwrod;

	@NotNull(message = "Please select correct profile type")
	private ProfileType profileType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmailId(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	
	
}

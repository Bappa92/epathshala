package com.jds.epathshala.nbirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jds.epathshala.nbirest.rest.payload.ProfileType;
import com.jds.epathshala.persistence.repository.UserRepository;

@Component
public class UserInputValidator {

	@Autowired
	UserRepository userRepository;

	public boolean validateMobileNo(String mobileNo) {
		return userRepository.findByMobileNo(mobileNo).isPresent();
	}

	public boolean validateEmailId(String EmailId) {
		return userRepository.findByEmail(EmailId).isPresent();
	}

	public boolean validateProfileType(ProfileType profileType) {
		return profileType == null;
	}
}

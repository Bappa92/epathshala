package com.jds.epathshala.nbirest.service;

import java.util.List;

import com.jds.epathshala.baseservice.exception.InvalidUserDetailsException;
import com.jds.epathshala.nbirest.rest.payload.ProfileType;
import com.jds.epathshala.nbirest.rest.payload.SignUpRequest;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;

public interface UserService {
	List<UserInfo> getAllUser();

	boolean register(SignUpRequest singUpRequest) throws InvalidUserDetailsException ;

	void deleteAllUser();

	UserInfo getUser(String id);
	
	List<UserInfo> getAllUserByProfileType(ProfileType profileType);
}

package com.jds.epathshala.nbirest.service;

import java.util.List;

import com.jds.epathshala.nbirest.rest.payload.SignUpRequest;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;

public interface UserService {
	List<UserInfo> getAllUser();

	boolean register(SignUpRequest singUpRequest);

	void deleteAllUser();

	UserInfo getUser(String id);
}

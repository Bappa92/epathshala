package com.jds.epathshala.nbirest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jds.epathshala.nbirest.rest.payload.ApiResponse;
import com.jds.epathshala.nbirest.rest.payload.ProfileType;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;
import com.jds.epathshala.nbirest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping(value = "/get", produces = "application/json")
	public List<UserInfo> getUsers(@RequestParam(value = "profiletype", required = false) ProfileType profileType) {

		LOGGER.info("<REST:GET>Received request to get all users {}",
				profileType == null ? "" : " with profiletype " + profileType);

		if (profileType == null) {
			return userService.getAllUser();
		}
		return userService.getAllUserByProfileType(profileType);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping(value = "/get/{id}", produces = "application/json")
	public UserInfo getUser(@PathVariable(value = "id") String id) {
		LOGGER.info("<REST:GET{ID}>Received request to get a user with id {}", id);

		return userService.getUser(id);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<ApiResponse> deleteAllUser() {
		LOGGER.info("<REST:DELETE>Received request to delete all user");

		userService.deleteAllUser();
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "All users are deleted"), HttpStatus.OK);
	}
}

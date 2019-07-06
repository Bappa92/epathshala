package com.jds.epathshala.nbirest.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jds.epathshala.nbirest.rest.payload.ApiResponse;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;
import com.jds.epathshala.nbirest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
	@RolesAllowed("ADMIN")
	public List<UserInfo> getUsers() {
		return userService.getAllUser();
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
	@RolesAllowed("ADMIN")
	public UserInfo getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json")
	@RolesAllowed("ADMIN")
	public ResponseEntity<ApiResponse> deleteAllUser() {
		userService.deleteAllUser();
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "All users are deleted"), HttpStatus.OK);
	}
}

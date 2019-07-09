package com.jds.epathshala.nbirest.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jds.epathshala.baseservice.exception.InvalidUserDetailsException;
import com.jds.epathshala.nbirest.rest.payload.ProfileType;
import com.jds.epathshala.nbirest.rest.payload.SignUpRequest;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;
import com.jds.epathshala.nbirest.service.UserService;
import com.jds.epathshala.persistence.repository.UserRepository;
import com.jds.epathshala.persistence.db.model.Role;
import com.jds.epathshala.persistence.db.model.User;
import com.jds.epathshala.persistence.db.model.UserType;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<UserInfo> getAllUser() {
		return userRepository.findAll().stream().map(user -> new UserInfo(user)).collect(Collectors.toList());
	}

	public boolean register(SignUpRequest singUpRequest) throws InvalidUserDetailsException {

		User user = new User();
		user.setName(singUpRequest.getName());
		user.setUsername(singUpRequest.getMobileNo());
		user.setMobileNo(singUpRequest.getMobileNo());
		user.setEmail(singUpRequest.getEmail());
		user.setRoles(getRoles(singUpRequest.getProfileType()));
		user.setUserType(adaptUserType(singUpRequest.getProfileType()));
		user.setPassword(passwordEncoder.encode(singUpRequest.getPasswrod()));
		userRepository.save(user);
		return true;

	}

	private UserType adaptUserType(ProfileType profileType) throws InvalidUserDetailsException {
		switch (profileType) {
		case STUDENT:
			return UserType.STUDENT;
		case TEACHER:
			return UserType.TEACHER;
		case ADMIN:
			return UserType.ADMIN;
		default:
			throw new InvalidUserDetailsException(MessageFormat.format("ProfileType : {0} is invalid ", profileType));
		}
	}

	private Set<Role> getRoles(ProfileType profileType) throws InvalidUserDetailsException {
		Set<Role> roles = new HashSet<Role>();
		switch (profileType) {
		case STUDENT:
			roles.add(Role.STUDENT);
			break;
		case TEACHER:
			roles.add(Role.TEACHER);
			break;
		case ADMIN:
			roles.add(Role.ADMIN);
			break;
		default:
			throw new InvalidUserDetailsException(MessageFormat.format("ProfileType : {0} is invalid ", profileType));
		}
		return roles;
	}

	public void deleteAllUser() {
		userRepository.deleteAll();
	}

	public UserInfo getUser(String id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return new UserInfo(userOptional.get());
		}
		return null;
	}

	@Override
	public List<UserInfo> getAllUserByProfileType(ProfileType profileType) {
		List<UserInfo> userInfos = new ArrayList<>();
		switch (profileType) {
		case TEACHER:
			Optional<List<User>> teachersOptinal = userRepository.findByUserType(UserType.TEACHER);
			if (teachersOptinal.isPresent()) {
				userInfos = teachersOptinal.get().stream().map(user -> new UserInfo(user)).collect(Collectors.toList());
			}
			break;
		case STUDENT:
			Optional<List<User>> studentsOptinal = userRepository.findByUserType(UserType.STUDENT);
			if (studentsOptinal.isPresent()) {
				userInfos = studentsOptinal.get().stream().map(user -> new UserInfo(user)).collect(Collectors.toList());
			}
			break;
		case ADMIN:
			Optional<List<User>> adminOptional = userRepository.findByUserType(UserType.ADMIN);
			if (adminOptional.isPresent()) {
				userInfos = adminOptional.get().stream().map(user -> new UserInfo(user)).collect(Collectors.toList());
			}
		}
		return userInfos;
	}

}

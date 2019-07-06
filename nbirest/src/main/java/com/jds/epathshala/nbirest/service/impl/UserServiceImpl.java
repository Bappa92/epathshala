package com.jds.epathshala.nbirest.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jds.epathshala.nbirest.rest.payload.ProfileType;
import com.jds.epathshala.nbirest.rest.payload.SignUpRequest;
import com.jds.epathshala.nbirest.rest.payload.UserInfo;
import com.jds.epathshala.nbirest.service.UserService;
import com.jds.epathshala.persistence.repository.UserRepository;
import com.jds.epathshala.persistenceservice.db.model.Role;
import com.jds.epathshala.persistenceservice.db.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<UserInfo> getAllUser() {
		return userRepository.findAll().stream().map(user -> new UserInfo(user)).collect(Collectors.toList());
	}

	public boolean register(SignUpRequest singUpRequest) {
		User user = new User();
		user.setName(singUpRequest.getName());
		user.setMobileNo(singUpRequest.getMobileNo());
		user.setEmail(singUpRequest.getEmail());
		user.setRoles(getRoles(singUpRequest.getProfileType()));
		user.setPassword(passwordEncoder.encode(singUpRequest.getPasswrod()));
		userRepository.save(user);
		return true;
	}

	private Set<Role> getRoles(ProfileType profileType) {
		Set<Role> roles = new HashSet<Role>();
		switch (profileType) {
		case STUDENT:
			roles.add(Role.STUDENT);
			break;
		case TEACHER:
			roles.add(Role.TEACHER);
			break;
		case MANAGER:
			roles.add(Role.STUDENT);
			roles.add(Role.TEACHER);
			break;
		case ADMIN:
			roles.add(Role.ADMIN);
			break;
		default:
			break;
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

}

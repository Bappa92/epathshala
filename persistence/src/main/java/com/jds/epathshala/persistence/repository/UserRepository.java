package com.jds.epathshala.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jds.epathshala.persistence.db.model.User;
import com.jds.epathshala.persistence.db.model.UserType;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail(String name);

	Optional<User> findByMobileNo(String mobileNo);

	Optional<User> findByUsername(String username);

	Optional<List<User>> findByUserType(UserType userType);
}

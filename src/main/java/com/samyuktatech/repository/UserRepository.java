package com.samyuktatech.repository;

import org.springframework.data.repository.CrudRepository;

import com.samyuktatech.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

}

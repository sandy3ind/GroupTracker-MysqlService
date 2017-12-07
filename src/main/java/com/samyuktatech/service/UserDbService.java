package com.samyuktatech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyuktatech.entity.User;
import com.samyuktatech.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserDbService {
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Save User entity
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {		
		
		userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
}

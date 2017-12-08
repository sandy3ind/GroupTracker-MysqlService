package com.samyuktatech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<?> save(@RequestBody User user) {
		
		// Check if user already exists with Email
		User userDb = userRepository.findByEmail(user.getEmail());
		if (userDb != null) {
			return new ResponseEntity<>("User already exists!", HttpStatus.BAD_REQUEST);
		}
		
		userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
	
	/**
	 * Get user by Email
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getByEmail(@RequestParam("email") String email) {		
		
		User user = userRepository.findByEmail(email);
		
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
	}
	
	
}

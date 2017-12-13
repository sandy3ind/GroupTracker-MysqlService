package com.samyuktatech.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.samyuktatech.entity.UserFriend;
import com.samyuktatech.repository.UserFriendRepository;
import com.samyuktatech.repository.UserRepository;
import com.samyuktatech.util.Utility;

@RestController
@RequestMapping("/user/friend")
public class UserFriendDbService {
	
	@Autowired
	private UserFriendRepository userFriendRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Save User Friend entity
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody com.samyuktatech.comman.model.UserFriend modelUserFriend) {
		
		// Check if this relation already exists
		// Check if friend already accepted it
		Long isAccpeted = userFriendRepository.countByUserIdAndFriendIdAndIsRequestAccepted(modelUserFriend.getUserId(),
				modelUserFriend.getFriendId(), modelUserFriend.isRequestAccepted());
		if (isAccpeted > 0) {
			return new ResponseEntity<>("Request already accpeted", HttpStatus.CONFLICT);	
		}
		
		UserFriend userFriendEntity = Utility.userFriendModelToEntity(modelUserFriend);		
		
		userFriendRepository.save(userFriendEntity);
		
		return new ResponseEntity<>(userFriendEntity, HttpStatus.CREATED);		
	}	
	
	/**
	 * Get UserFriend by userId and friendId
	 * 
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@GetMapping("/{userId}/{friendId}")
	public ResponseEntity<?> getByUserIdAndFriendId(
			@PathVariable("userId") Long userId,
			@PathVariable("friendId") Long friendId) {
		
		UserFriend userFriend = userFriendRepository.findByUserIdAndFriendId(userId, friendId);
		
		if (userFriend != null) {
			return ResponseEntity.ok(Utility.userFriendEntityToModel(userFriend));
		}
		
		return ResponseEntity.noContent().build();	
		
	}
	
	/**
	 * Get all the friends of a user and paginate the result
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<?> getFriends(@PathVariable("userId") Long userId) {
		
		List<UserFriend> userFriends = userFriendRepository.findByUserIdOrFriendIdAndIsRequestAccepted(
				userId, userId, true);
		
		if (!userFriends.isEmpty()) {			
			
			// Iterate and build Users list out of UserFriend list
			// If user.id == userId then request has been sent by this user
			// else sent by other user
			
			List<com.samyuktatech.comman.model.User> modelUsers = 
					userFriends
						.stream()
							.filter(u -> u.getUserId() != null && u.getFriendId() != null)
							.map(u -> {								
								if (u.getUserId().equals(userId)) {
									return Utility.userEntityToModel(userRepository.findOne(u.getFriendId()));
								}
								else {
									return Utility.userEntityToModel(userRepository.findOne(u.getUserId()));
								}
							})
							.collect(Collectors.toList());
			
			return ResponseEntity.ok(modelUsers);
		}
		
		return ResponseEntity.ok(Collections.emptyList());
	}
	
	
	
}

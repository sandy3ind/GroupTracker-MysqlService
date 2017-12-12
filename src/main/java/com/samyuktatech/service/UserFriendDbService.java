package com.samyuktatech.service;

import java.util.Date;

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
	
}

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
	public ResponseEntity<?> save(@RequestBody com.samyuktatech.comman.model.UserFriend userFriend) {
		
		// Check if this relation already exists
		// Check if user has sent same request again
		Long isSent = userFriendRepository.countByUserIdAndFriendIdAndIsRequestSent(userFriend.getUserId(),
				userFriend.getFriendId(), userFriend.isRequestSent());
		if (isSent > 0) {
			return new ResponseEntity<>("Request already sent", HttpStatus.CONFLICT);	
		}
		// Check if friend already accepted it
		Long isAccpeted = userFriendRepository.countByUserIdAndFriendIdAndIsRequestAccepted(userFriend.getUserId(),
				userFriend.getFriendId(), userFriend.isRequestAccepted());
		if (isAccpeted > 0) {
			return new ResponseEntity<>("Request already accpeted", HttpStatus.CONFLICT);	
		}
		
		UserFriend userFriendEntity = new UserFriend();
		userFriendEntity.setUserId(userFriend.getUserId());
		userFriendEntity.setFriendId(userFriend.getFriendId());		
		userFriendEntity.setRequestSent(userFriend.isRequestSent());
		userFriendEntity.setRequestSentDate(userFriend.getRequestSentDate());
		userFriendEntity.setRequestAccepted(userFriend.isRequestAccepted());
		userFriendEntity.setRequestAcceptedDate(userFriend.getRequestAcceptedDate());
		
		userFriendRepository.save(userFriendEntity);
		
		return new ResponseEntity<>(userFriendEntity, HttpStatus.CREATED);		
	}	
	
}

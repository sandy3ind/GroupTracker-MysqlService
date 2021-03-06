package com.samyuktatech.util;

import com.samyuktatech.entity.User;
import com.samyuktatech.entity.UserFriend;

public class Utility {

	/**
	 * Convert UserFriend Entity to its Model
	 * 
	 * @param entityUserFriend
	 * @return
	 */
	public static com.samyuktatech.comman.model.UserFriend userFriendEntityToModel(UserFriend entityUserFriend) {
		
		com.samyuktatech.comman.model.UserFriend modelUserFriend = new com.samyuktatech.comman.model.UserFriend(); 
		
		modelUserFriend.setId(entityUserFriend.getId());
		modelUserFriend.setUserId(entityUserFriend.getUserId());
		modelUserFriend.setFriendId(entityUserFriend.getFriendId());
		modelUserFriend.setRequestSent(entityUserFriend.isRequestSent());
		modelUserFriend.setRequestSentDate(entityUserFriend.getRequestSentDate());
		modelUserFriend.setRequestAccepted(entityUserFriend.isRequestAccepted());
		modelUserFriend.setRequestAcceptedDate(entityUserFriend.getRequestAcceptedDate());		
		
		return modelUserFriend;
	}
	
	/**
	 * Convert User Entity to its Model
	 * 
	 * @param user
	 * @return
	 */
	public static com.samyuktatech.comman.model.User userEntityToModel(User user) {
		
		com.samyuktatech.comman.model.User modelUser = new com.samyuktatech.comman.model.User();
				
		modelUser.setId(user.getId());
		modelUser.setName(user.getName());
		modelUser.setEmail(user.getEmail());
		modelUser.setPhone(user.getPhone());		
		
		return modelUser;
	}
	
	/**
	 * Convert UserFriend Model to its Entity
	 * 
	 * @param modelUserFriend
	 * @return
	 */
	public static UserFriend userFriendModelToEntity(com.samyuktatech.comman.model.UserFriend modelUserFriend) {
		
		UserFriend entityUserFriend = new UserFriend(); 
		
		entityUserFriend.setId(modelUserFriend.getId());
		entityUserFriend.setUserId(modelUserFriend.getUserId());
		entityUserFriend.setFriendId(modelUserFriend.getFriendId());
		entityUserFriend.setRequestSent(modelUserFriend.isRequestSent());
		entityUserFriend.setRequestSentDate(modelUserFriend.getRequestSentDate());
		entityUserFriend.setRequestAccepted(modelUserFriend.isRequestAccepted());
		entityUserFriend.setRequestAcceptedDate(modelUserFriend.getRequestAcceptedDate());		
		
		return entityUserFriend;
	}
	
	
}

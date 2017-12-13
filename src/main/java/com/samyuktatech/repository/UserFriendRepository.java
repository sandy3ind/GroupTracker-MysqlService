package com.samyuktatech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.samyuktatech.entity.UserFriend;

public interface UserFriendRepository extends CrudRepository<UserFriend, Long> {

	Long countByUserIdAndFriendIdAndIsRequestSent(Long userId, Long friendId, boolean isRequestSent);

	Long countByUserIdAndFriendIdAndIsRequestAccepted(Long userId, Long friendId, boolean isRequestAccepted);
	
	UserFriend findByUserIdAndFriendIdAndIsRequestSent(Long userId, Long friendId, boolean isRequestSent);
	
	UserFriend findByUserIdAndFriendId(Long userId, Long friendId);
	
	UserFriend findByUserIdAndFriendIdAndIsRequestAccepted(Long userId, Long friendId, boolean isRequestAccepted);

	List<UserFriend> findByUserIdOrFriendIdAndIsRequestAccepted(Long userId, Long friendId, boolean isRequestAccepted);

}

package com.samyuktatech.repository;

import org.springframework.data.repository.CrudRepository;

import com.samyuktatech.entity.UserFriend;

public interface UserFriendRepository extends CrudRepository<UserFriend, Long> {

	Long countByUserIdAndFriendIdAndIsRequestSent(Long userId, Long friendId, boolean isRequestSent);

	Long countByUserIdAndFriendIdAndIsRequestAccepted(Long userId, Long friendId, boolean isRequestAccepted);

}

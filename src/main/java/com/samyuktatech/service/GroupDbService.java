package com.samyuktatech.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samyuktatech.entity.Group;
import com.samyuktatech.entity.User;
import com.samyuktatech.repository.GroupRepository;

@RestController
@RequestMapping("/group")
public class GroupDbService {
	
	@Autowired
	private GroupRepository groupRepository;

	/**
	 * Save Group 
	 * 
	 * @param group
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody com.samyuktatech.comman.model.Group group) {		
		
		Group groupEntity = new Group();
		groupEntity.setName(group.getName());
		groupEntity.setCreatedDate(new Date());
		groupEntity.setCreatedBy(new User(group.getCreatedBy()));
		
		// Set user list
		List<User> users = group.getUserIds().stream().map(id -> new User(id)).collect(Collectors.toList());
		groupEntity.setUsers(users);		
		
		groupRepository.save(groupEntity);
		
		return new ResponseEntity<>(groupEntity, HttpStatus.CREATED);
		
	}
}

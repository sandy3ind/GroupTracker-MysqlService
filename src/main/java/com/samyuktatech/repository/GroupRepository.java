package com.samyuktatech.repository;

import org.springframework.data.repository.CrudRepository;

import com.samyuktatech.entity.Group;
import com.samyuktatech.entity.User;

public interface GroupRepository extends CrudRepository<Group, Long> {

}

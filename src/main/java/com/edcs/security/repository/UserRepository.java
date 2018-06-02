package com.edcs.security.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edcs.model.EntityModel;
import com.edcs.security.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    
    Set<EntityModel>findByEntityModelList(String userName);
}

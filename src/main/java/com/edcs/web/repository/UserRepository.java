package com.edcs.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edcs.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

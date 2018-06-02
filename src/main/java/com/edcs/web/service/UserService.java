package com.edcs.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edcs.security.model.Authority;
import com.edcs.security.model.AuthorityName;
import com.edcs.security.model.User;
import com.edcs.security.repository.UserRepository;
import com.edcs.web.repository.AuthorityRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public User createUser(User user){
		List<Authority> authorityList=authorityRepository.findAllByName(AuthorityName.ROLE_SUPER_ADMIN);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(false);
		user.setLastPasswordResetDate(new Date());
		user.setAuthorities(authorityList);
		return userRepository.save(user);
	}
	
	public List<User>findAllUsers(){
		return userRepository.findAll();
	}
	
	public User findOne(String userName){
		return userRepository.findByUsername(userName);
	}
	
	
}

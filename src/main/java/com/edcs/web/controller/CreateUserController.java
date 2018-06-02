package com.edcs.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edcs.bean.UserBean;
import com.edcs.security.model.User;
import com.edcs.web.service.UserService;

@RestController
public class CreateUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="users",method=RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return ResponseEntity.ok( userService.createUser(user));
	}
	
	@RequestMapping(value="users",method=RequestMethod.GET)
	public List<UserBean>findAllUsers(){
		List<User>userList=userService.findAllUsers();
		List<UserBean>userBeanList=new ArrayList<UserBean>();
		for(User user:userList){
			UserBean userBean=new UserBean();
			BeanUtils.copyProperties(user, userBean);
			userBeanList.add(userBean);
		}
		System.out.println("userList size="+userBeanList.size());
		return userBeanList;
	}
	
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@RequestMapping(value="api/users",method=RequestMethod.GET)
	public List<UserBean>findAllUsers2(){
		List<User>userList=userService.findAllUsers();
		List<UserBean>userBeanList=new ArrayList<UserBean>();
		for(User user:userList){
			UserBean userBean=new UserBean();
			BeanUtils.copyProperties(user, userBean);
			userBeanList.add(userBean);
		}
		System.out.println("userList size="+userBeanList.size());
		return userBeanList;
	}
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN,ROLE_ADMIN')")
	@GetMapping(value="api/user/{userName}")
	public ResponseEntity<UserBean> findUserByUserName(HttpServletRequest request,@PathVariable("userName")String userName){
		UserBean userBean=new UserBean();
		User user=userService.findOne(userName);
		if(null!=user){
			BeanUtils.copyProperties(user, userBean);
			return new ResponseEntity<>(userBean,HttpStatus.OK);
		}else{
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		
	}
	
}

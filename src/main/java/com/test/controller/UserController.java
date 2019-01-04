package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.bean.ResponeBean;
import com.test.bean.UserBean;
import com.test.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService  userService;
	
	@GetMapping("/user-list")
	public ResponeBean getUserList()throws Exception {	
		return new ResponeBean(userService.getAllUser(),HttpStatus.OK);
	}
	@PostMapping("/save-user")
	public ResponeBean saveUser(@RequestBody UserBean bean)throws Exception {
		userService.saveUser(bean);
		return new ResponeBean(HttpStatus.OK,"User saved Successfully");
	}
	@GetMapping("/get-user/{id}")
	public ResponeBean getUserById(@PathVariable Long id)throws Exception {
		return new ResponeBean(userService.getUserById(id),HttpStatus.OK);
	}
	
	@GetMapping("/delete-user/{id}")
	public ResponeBean deleteUser(@PathVariable Long id)throws Exception {
		userService.deleteUser(id);
		return new ResponeBean(HttpStatus.OK,"User Deleted Successfully");	
	}

}

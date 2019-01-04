package com.test.service;

import java.util.List;

import com.test.bean.ResponeBean;
import com.test.bean.UserBean;

public interface UserService {

	List<UserBean> getAllUser()throws Exception;

	boolean saveUser(UserBean bean)throws Exception;

	UserBean getUserById(Long id)throws Exception;

	void deleteUser(Long id) throws Exception;
	
	
	

}

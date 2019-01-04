package com.test.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.bean.AddressBean;
import com.test.bean.ResponeBean;
import com.test.bean.UserBean;
import com.test.model.Address;
import com.test.model.AppUser;
import com.test.repository.AddressRepository;
import com.test.repository.UserRepository;
import com.test.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRepo;
	@Autowired
	AddressRepository addRepo;
	
	@Override
	public List<UserBean> getAllUser()throws Exception {

		List<UserBean> beans = new ArrayList<>();
		List<AppUser> appUsers = userRepo.findAll();

		if (appUsers != null && !appUsers.isEmpty())

			appUsers.forEach((appUser) -> {
				UserBean bean = new UserBean();
				bean.setId(appUser.getId());
				bean.setName(appUser.getName());
				bean.setAge(appUser.getAge());
				if (appUser.getAddress() != null) {
					AddressBean adBean = new AddressBean();
					adBean.setAddress1(appUser.getAddress().getAddress1());
					adBean.setCity(appUser.getAddress().getCity());
					adBean.setState(appUser.getAddress().getState());
					adBean.setZipCode(appUser.getAddress().getZipCode());
					bean.setAddress(adBean);
				}
				beans.add(bean);
			});

		return beans;
	}

	@Override
	public boolean saveUser(UserBean bean)throws Exception {
		AppUser user = new AppUser();
		if(bean.getAddress()!=null) {
			Address address = new Address();
			address.setId(bean.getAddress().getId());
			address.setCity(bean.getAddress().getCity());
			address.setState(bean.getAddress().getState());
			address.setAddress1(bean.getAddress().getAddress1());
			addRepo.save(address);
			user.setAddress(address);
		}
		user.setId(bean.getId());
		user.setAge(bean.getAge());
		user.setName(bean.getName());
		userRepo.save(user);
		return true;
	}

	@Override
	public UserBean getUserById(Long id)throws Exception {
		Optional<AppUser> user = userRepo.findById(id);
		AppUser appUser = user.get();
		UserBean bean = new UserBean();
		
		if (appUser.getAddress() != null) {
			AddressBean adBean = new AddressBean();
			adBean.setAddress1(appUser.getAddress().getAddress1());
			adBean.setCity(appUser.getAddress().getCity());
			adBean.setState(appUser.getAddress().getState());
			adBean.setZipCode(appUser.getAddress().getZipCode());
			bean.setAddress(adBean);
		}
		bean.setId(appUser.getId());
		bean.setAge(appUser.getAge());
		bean.setName(appUser.getName());
		return bean;
	}

	@Override
	public void deleteUser(Long id)throws Exception {
		Optional<AppUser> user = userRepo.findById(id);
		AppUser appUser = user.get();
		userRepo.delete(appUser);
	}

}

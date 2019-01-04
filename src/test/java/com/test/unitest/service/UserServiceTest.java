package com.test.unitest.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.UserBean;
import com.test.model.Address;
import com.test.model.AppUser;
import com.test.repository.AddressRepository;
import com.test.repository.UserRepository;
import com.test.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepo;
	@Mock
	AddressRepository addressRepo;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void testGetAllUser() throws Exception {

		when(userRepo.findAll()).thenReturn(getUsersData());
		List<UserBean> usersBean = userService.getAllUser();
		usersBean.forEach((bean) -> {
			System.out.println("  id   " + bean.getId() + " Name  " + bean.getName());

		});

	}

	@Test
	public void testGetUserById() throws Exception {
		Optional<AppUser> optionalUser = Optional.of(getUsersData().get(0));
		when(userRepo.findById(10l)).thenReturn(optionalUser);
		UserBean bean = userService.getUserById(10l);
		System.out.println("  id   " + bean.getId() + " Name  " + bean.getName());

	}

	@Test
	public void testSaveUser() throws Exception {
		AppUser user = getUsersData().get(0);
		UserBean bean = new DozerBeanMapper().map(user, UserBean.class);
		when(userRepo.save(user)).thenReturn(user);
		when(addressRepo.save(user.getAddress())).thenReturn(user.getAddress());
		boolean status = userService.saveUser(bean);
		System.out.println(status);
	}

	private List<AppUser> getUsersData() {
		List<AppUser> uBeans = new ArrayList<>();
		AppUser user = new AppUser();
		user.setId(10l);
		user.setName("jack");
		user.setAge(20);
		Address address = new Address();
		address.setAddress1("Gachibowali");
		address.setCity("Hyd");
		address.setId(20l);
		address.setState("CG");
		user.setAddress(address);
		uBeans.add(user);

		AppUser user2 = new AppUser();
		user2.setId(20l);
		user2.setName("raam");
		user2.setAge(40);
		user2.setAddress(address);
		uBeans.add(user2);
		return uBeans;
	}

}

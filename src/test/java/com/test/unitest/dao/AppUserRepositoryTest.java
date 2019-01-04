package com.test.unitest.dao;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.model.Address;
import com.test.model.AppUser;
import com.test.repository.AddressRepository;
import com.test.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AppUserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepo;

	@Test
	public void getUserList() {
		List<AppUser> users = userRepository.findAll();
		System.out.println("----size of users ----" + users.size());
		users.forEach((user -> {
			System.out.println("--userId--" + user.getId() + "---name---" + user.getName());
		}));
	}

	@Test
	public void saveUser() {
		AppUser bean1 = new AppUser();
		bean1.setName("jack");
		bean1.setAge(20);
		Address aBean = new Address();
		aBean.setAddress1("hello");
		aBean.setCity("Hyd11");
		aBean.setState("CG");
		bean1.setAddress(aBean);
		addressRepo.save(aBean);
		userRepository.save(bean1);
	}

	@Test
	public void getById() {
		Optional<AppUser> appUser = userRepository.findById(1l);
		AppUser user = appUser.get();
		System.out.println("---user Id" + user.getId() + "--name--" + user.getName());

	}

	@Test
	public void deleteUser() {
		Optional<AppUser> appUser = userRepository.findById(1l);
		AppUser user = appUser.get();
		userRepository.delete(user);
	}
}

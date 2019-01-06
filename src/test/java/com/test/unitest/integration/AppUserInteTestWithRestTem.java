package com.test.unitest.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.AddressBean;
import com.test.bean.ResponeBean;
import com.test.bean.UserBean;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@TestPropertySource("classpath:test/test.properties")
public class AppUserInteTestWithRestTem {

	@Autowired
	TestRestTemplate restTemplate;
	ObjectMapper om = new ObjectMapper();

	@Test
	public void testAppUserList() throws JSONException, JsonParseException, JsonMappingException, IOException {
		ResponeBean response = restTemplate.getForObject("/api/user-list", ResponeBean.class);
		System.out.println(response);
		String usreString = om.writeValueAsString(response.payLoad);
		List objs = om.readValue(usreString, List.class);
		Iterator iterator = objs.iterator();
		while (iterator.hasNext()) {
			UserBean userBean = new DozerBeanMapper().map(iterator.next(), UserBean.class);
			System.out.println(" id              " + userBean.getId());
			System.out.println(" name            " + userBean.getName());
			System.out.println(" age             " + userBean.getAge());
			AddressBean aBean = userBean.getAddress();
			System.out.println("  state           " + aBean.getCity());
			System.out.println("  city             " + aBean.getCity());
			System.out.println(" address1          " + aBean.getAddress1());
			System.out.println("   zipCode         " + aBean.getZipCode());

		}
	}

	@Test
	public void testGetUserById() throws Exception {
		ResponeBean response = restTemplate.getForObject("/api/get-user/" + 10l, ResponeBean.class);
		UserBean userBean = new DozerBeanMapper().map(response.payLoad, UserBean.class);
		System.out.println(" id   " + userBean.getId());
		System.out.println(" name   " + userBean.getName());
		System.out.println(" age   " + userBean.getAge());
		AddressBean aBean = userBean.getAddress();
		System.out.println("  state  " + aBean.getCity());
		System.out.println("  city    " + aBean.getCity());
		System.out.println(" address1  " + aBean.getAddress1());
		System.out.println("   zipCode  " + aBean.getZipCode());
	}

	@Test
	public void testSaveUser() throws Exception {
		ResponseEntity<ResponeBean> response = restTemplate.postForEntity("/api/save-user", getUsersData().get(0),
				ResponeBean.class);
		System.out.println(" response code" + response.getStatusCodeValue() + " Headers   " + response.getHeaders());
		ResponeBean repBean = response.getBody();
		System.out.println(" --message--  " + repBean.getMessage() + " --status code--  " + repBean.getStatusCode());

	}

	private List<UserBean> getUsersData() {
		List<UserBean> uBeans = new ArrayList<>();
		UserBean bean1 = new UserBean();
		bean1.setName("Ritesh");
		bean1.setAge(40);
		AddressBean aBean = new AddressBean();
		aBean.setAddress1(" road no 6 indra vihar ");
		aBean.setCity("Rapur");
		// aBean.setId(20l);
		aBean.setState("CG");
		aBean.setZipCode("123456");
		bean1.setAddress(aBean);
		uBeans.add(bean1);

		UserBean bean2 = new UserBean();
		bean2.setName("mohan");
		bean2.setAge(40);
		bean2.setAddress(aBean);
		uBeans.add(bean2);
		return uBeans;
	}

}

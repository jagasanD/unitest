package com.test.unitest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.AddressBean;
import com.test.bean.ResponeBean;
import com.test.bean.UserBean;
import com.test.controller.UserController;
import com.test.serviceImpl.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserServiceImpl userService;

	private MvcResult andReturn;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void testGetAllUser() throws Exception {
		List<UserBean> uBeans = getUsersData();
		when(userService.getAllUser()).thenReturn(uBeans);
		RequestBuilder builder = MockMvcRequestBuilders.get("/api/user-list").accept(MediaType.APPLICATION_JSON);
		andReturn = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		String result = andReturn.getResponse().getContentAsString();
		ResponeBean response = om.readValue(result, ResponeBean.class);
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

		/*
		 * ******* Another Example with Key value Pair**************
		 * 
		 * JsonNode node = om.readValue(result, JsonNode.class); Iterator<JsonNode>
		 * iterator = node.get("payLoad").iterator(); while (iterator.hasNext()) {
		 * JsonNode userNode = iterator.next();
		 * System.out.println(" id     "+userNode.get("id").asText());
		 * System.out.println(" name   "+userNode.get("name").asText());
		 * System.out.println(" age   "+userNode.get("age").asText()); JsonNode
		 * addressNode = userNode.get("address");
		 * System.out.println("  state  "+addressNode.get("state").asText());
		 * System.out.println("  city    "+addressNode.get("city").asText());
		 * System.out.println(" address1  "+addressNode.get("address1").asText());
		 * System.out.println("   zipCode  "+addressNode.get("zipCode").asText());
		 * 
		 * }
		 */

	}

	@Test
	public void testGetUserById() throws Exception {
		List<UserBean> uBeans = getUsersData();
		when(userService.getUserById(10l)).thenReturn(uBeans.get(0));

		RequestBuilder builder = MockMvcRequestBuilders.get("/api/get-user/" + 10l).accept(MediaType.APPLICATION_JSON);

		andReturn = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		String result = andReturn.getResponse().getContentAsString();
		ResponeBean response = om.readValue(result, ResponeBean.class);
		System.out.println("---response by id----" + response.payLoad);
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

		List<UserBean> uBeans = getUsersData();
		String inputJson = om.writeValueAsString(uBeans.get(0));
		when(userService.saveUser(uBeans.get(0))).thenReturn(true);
		RequestBuilder builder = MockMvcRequestBuilders.post("/api/save-user").contentType(MediaType.APPLICATION_JSON)
				.content(inputJson);

		andReturn = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();

		String result = andReturn.getResponse().getContentAsString();

		ResponeBean response = om.readValue(result, ResponeBean.class);
		System.out.println(" results --------------- " + response.getMessage());
		assertEquals("User saved Successfully", response.getMessage());
	}

	private List<UserBean> getUsersData() {
		List<UserBean> uBeans = new ArrayList<>();
		UserBean bean1 = new UserBean();
		bean1.setId(10l);
		bean1.setName("jack");
		bean1.setAge(20);
		AddressBean aBean = new AddressBean();
		aBean.setAddress1("Gachibowali");
		aBean.setCity("Hyd");
		aBean.setId(20l);
		aBean.setState("CG");
		bean1.setAddress(aBean);
		uBeans.add(bean1);

		UserBean bean2 = new UserBean();
		bean2.setId(20l);
		bean2.setName("raam");
		bean2.setAge(40);
		bean2.setAddress(aBean);
		uBeans.add(bean2);
		return uBeans;
	}

}

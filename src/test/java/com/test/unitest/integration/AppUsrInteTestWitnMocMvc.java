package com.test.unitest.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.AddressBean;
import com.test.bean.ResponeBean;
import com.test.bean.UserBean;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AppUsrInteTestWitnMocMvc {

	@Autowired
	MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void integrationTest() throws Exception {

		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/user-list")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		String reString = result.getResponse().getContentAsString();
		ResponeBean resBean = om.readValue(reString, ResponeBean.class);

		String usreString = om.writeValueAsString(resBean.payLoad);
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
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/get-user/" + 1l)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		String reString = result.getResponse().getContentAsString();
		ResponeBean resBean = om.readValue(reString, ResponeBean.class);

		UserBean userBean = new DozerBeanMapper().map(resBean.payLoad, UserBean.class);
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
	public void testSave() throws Exception {
		String inputJson = om.writeValueAsString(getUsersData().get(0));
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/save-user")
				.contentType(MediaType.APPLICATION_JSON).content(inputJson);
		MvcResult result = mockMvc.perform(reqBuilder).andExpect(status().isOk()).andReturn();
		System.out.println("   response  " + result.getResponse().getContentAsString());
	}

	private List<UserBean> getUsersData() {
		List<UserBean> uBeans = new ArrayList<>();
		UserBean bean1 = new UserBean();
		bean1.setName("PK");
		bean1.setAge(50);
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

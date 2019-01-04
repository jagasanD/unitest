package com.test.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7653386314365988209L;

	private Long id;

	private String name;

	private Integer age;

	private AddressBean addressBean;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public AddressBean getAddress() {
		return addressBean;
	}

	public void setAddress(AddressBean address) {
		this.addressBean = address;
	}


}

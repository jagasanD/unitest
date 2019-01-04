package com.test.bean;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonInclude(Include.NON_NULL)
public class ResponeBean {

	public Object payLoad;

	public String message;

	public HttpStatus statusCode;

	public String errorsMessage;

	public ResponeBean() {

	}

	public ResponeBean(Object payLoad, HttpStatus statusCode) {

		this.payLoad = payLoad;
		this.statusCode = statusCode;

	}

	public ResponeBean(HttpStatus statusCode, String errorsMessage, String message) {
		this.statusCode = statusCode;
		this.errorsMessage = errorsMessage;
		this.message = message;

	}

	public ResponeBean(HttpStatus statusCode, String message) {
		this.setStatusCode(statusCode);
		this.setMessage(message);
		
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorsMessage() {
		return errorsMessage;
	}

	public void setErrorsMessage(String errorsMessage) {
		this.errorsMessage = errorsMessage;
	}

	
	
	

}

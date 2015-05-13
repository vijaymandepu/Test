package com.test.spring.models;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse<T> implements Serializable {	
	private static final long serialVersionUID = -5109241967151765433L;
	ServiceResponseStatus status;
	T results;
	public ServiceResponse(){
		
	}
	public ServiceResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceResponseStatus status) {
		this.status = status;
	}
	public T getResults() {
		return results;
	}
	public void setResults(T results) {
		this.results = results;
	}

}

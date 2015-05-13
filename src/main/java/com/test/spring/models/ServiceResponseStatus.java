package com.test.spring.models;

public class ServiceResponseStatus {
	String code;
	String desc;
	public ServiceResponseStatus(){
		
	}
	public ServiceResponseStatus(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}

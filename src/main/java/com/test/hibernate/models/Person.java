package com.test.hibernate.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Person {
	@NotEmpty(message="{NotEmpty.person.name}")
	@Size (min=2, max=30,message="{Size.person.name}")
	private String name;
	@NotNull(message="{NotNull.person.age}")
	@Min(message="{Min.person.age}",value=18)
	@Range(min=18, max=100,message="{Range.person.age}")
	private Integer age;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
}

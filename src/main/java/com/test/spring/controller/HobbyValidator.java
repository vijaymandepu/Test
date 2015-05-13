package com.test.spring.controller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<IsValidHobby , String> {
	public void initialize(IsValidHobby studentHobby){}
	
	public boolean isValid(String studentHobby, ConstraintValidatorContext ctx){return false;}

}


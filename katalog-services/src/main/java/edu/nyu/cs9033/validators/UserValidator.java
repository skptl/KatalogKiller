package edu.nyu.cs9033.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.nyu.cs9033.models.User;

/**
 * @author Shilpan Patel
 * 
 */

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "fName", "fName empty.");
		ValidationUtils.rejectIfEmpty(errors, "lName", "lName empty.");
		ValidationUtils.rejectIfEmpty(errors, "fName", "fName empty.");
		ValidationUtils.rejectIfEmpty(errors, "email", "email empty.");
		ValidationUtils.rejectIfEmpty(errors, "password", "password empty.");
		ValidationUtils.rejectIfEmpty(errors, "address", "address empty.");
		ValidationUtils.rejectIfEmpty(errors, "zipCode", "zipCode empty.");
	}

}

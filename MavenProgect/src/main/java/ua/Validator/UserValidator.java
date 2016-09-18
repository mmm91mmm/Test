package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.UserService;
import ua.shop_e.User;

public class UserValidator implements Validator{
	
	private final UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User form = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "User login can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "", "User mail can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "User name can't be empty");
	
		if(userService.findByMail(form.getMail())!=null){
			errors.rejectValue("mail", "", "User with this mail already exists");
		}
		if(userService.findByLogin(form.getLogin())!=null){
			errors.rejectValue("login", "", "User with this login already exists");
		}
	}
}
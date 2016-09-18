package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.RoleService;
import ua.shop_e.Role;

public class RoleValidator implements Validator{
	
	private final RoleService roleService;
	
	public RoleValidator(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Role form = (Role) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Role name can't be empty");
	
		if(roleService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Role with this name already exists");
		}
	}
}
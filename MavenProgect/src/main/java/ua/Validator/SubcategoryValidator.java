package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.SubcategoryService;
import ua.shop_e.Subcategory;

public class SubcategoryValidator implements Validator{
	
	private final SubcategoryService subcategoryService;
	
	public SubcategoryValidator(SubcategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Subcategory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Subcategory form = (Subcategory) target;
		if(form.getId()==0)if(subcategoryService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "That subcategory already exists");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}
}
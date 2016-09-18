package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.CategoryService;
import ua.shop_e.Category;

public class CategoryValidator implements Validator{
	
	private final CategoryService categoryService;
	
	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}
//проверка чтобы не повторялись категории
	@Override
	public void validate(Object target, Errors errors) {
		Category form = (Category) target;
		if(form.getId()==0)if(categoryService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Category already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}	
}
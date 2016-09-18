package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.ColourService;
import ua.shop_e.Colour;

public class ColourValidator implements Validator{
	
	private final ColourService colourService;
	
	public ColourValidator(ColourService colourService) {
		this.colourService = colourService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Colour.class.equals(clazz);
	}
//проверка чтобы не повторялись категории
	@Override
	public void validate(Object target, Errors errors) {
		Colour form = (Colour) target;
		if(form.getId()==0)if(colourService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Colour already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}	
}
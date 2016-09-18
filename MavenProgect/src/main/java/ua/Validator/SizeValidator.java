package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.SizeService;
import ua.shop_e.Size;

public class SizeValidator implements Validator{
	
	private final SizeService sizeService;
	
	public SizeValidator(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Size.class.equals(clazz);
	}
//проверка чтобы не повторялись категории
	@Override
	public void validate(Object target, Errors errors) {
		Size form = (Size) target;
		if(form.getId()==0)if(sizeService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Colour already exists");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}	
}
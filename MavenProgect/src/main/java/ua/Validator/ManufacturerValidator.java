package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.ManufacturerService;
import ua.shop_e.Manufacturer;

public class ManufacturerValidator implements Validator{
	
	private final ManufacturerService manufacturerService;
	
	public ManufacturerValidator(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Manufacturer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Manufacturer form = (Manufacturer) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Manufacturer name can't be empty");

		if(manufacturerService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Manufacturer with this name already exists");
		}
	}
}
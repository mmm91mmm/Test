package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.CountryService;
import ua.shop_e.Country;

public class CountryValidator implements Validator{
	
	private final CountryService countryService;
	
	public CountryValidator(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Country country = (Country) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Country name can't be empty");

		if(country.getId()==0)if(countryService.findByName(country.getName())!=null){
			errors.rejectValue("name", "", "Country with this name already exists");
		}
	}
}
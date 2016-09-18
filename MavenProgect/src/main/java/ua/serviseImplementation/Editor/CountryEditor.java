package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.CountryService;
import ua.shop_e.Country;

public class CountryEditor extends PropertyEditorSupport{

	private final CountryService countryService;

	public CountryEditor(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Country country = countryService.findOne(Integer.valueOf(text));
		setValue(country);
	}
}
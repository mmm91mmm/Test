package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.CountryFilterForm;
import ua.shop_e.Country;

public interface CountryService {

	Country findByName(String name);
	
	void delete(int id);
	
	List<Country> findAll();
	
	Country findOne(int id);

	void save(Country country);

	Page <Country> findAll(Pageable pageable, CountryFilterForm form);
}

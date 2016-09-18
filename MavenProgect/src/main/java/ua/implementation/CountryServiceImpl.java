package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.CountryFilterForm;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.shop_e.Country;
import ua.specification.CountryFilterAdapter;

	@Service
	public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public void delete(int id) {
		countryRepository.delete(id);
		
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country findOne(int id) {
		return  countryRepository.findOne(id);
	}

	@Override
	public void save(Country country) {
		if(countryRepository.findByName(country.getName())==null){
		countryRepository.save(country);
		}	
	}

	@Override
	public Page<Country> findAll(Pageable pageable, CountryFilterForm form) {
		return countryRepository.findAll(new CountryFilterAdapter(form), pageable);
	}


}

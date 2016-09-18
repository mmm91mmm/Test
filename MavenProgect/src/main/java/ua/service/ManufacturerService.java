package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.ManufacturerFilterForm;
import ua.shop_e.Category;
import ua.shop_e.Manufacturer;

public interface ManufacturerService {

	Manufacturer findByName(String name);
	
	void delete(int id);
	
	List<Manufacturer> findAll();
	
	Manufacturer findOne(int id);

//	void save(String manufacturerName);

	void save(Manufacturer manufacturer);
	
	Page<Manufacturer> findAll(Pageable pageable, ManufacturerFilterForm form);

}

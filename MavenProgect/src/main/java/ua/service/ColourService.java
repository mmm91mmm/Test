package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.ColourFilterForm;
import ua.shop_e.Colour;

public interface ColourService {
	
//	void save(String name);
	
	void delete(int id);
	
	List<Colour> findAll();
	
	Colour findByName(String name);

	void save(Colour colour);

	Colour findOne(int id);

	Page<Colour> findAll(Pageable pageable, ColourFilterForm form);

}

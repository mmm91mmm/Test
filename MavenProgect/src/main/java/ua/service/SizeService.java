package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.SizeFilterForm;
import ua.shop_e.Role;
import ua.shop_e.Size;

public interface SizeService {
	
	Size findByName(String name);
	
//	void save(String name);
	
	void delete(int id);
	
	List<Size> findAll();

	void save(Size size);

	Size findOne(int id);

	Page<Size> findAll(Pageable pageable, SizeFilterForm form);

}

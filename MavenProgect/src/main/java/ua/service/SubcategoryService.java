package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.SubcategoryFilterForm;
import ua.shop_e.Category;
import ua.shop_e.Size;
import ua.shop_e.Subcategory;

public interface SubcategoryService {

	Subcategory findByName(String name);
	
	void delete(int id);
	
	List<Subcategory> findAll();

	Subcategory findForForm(int id);

	void save(Subcategory form);

	Subcategory findOne(int id);

	Subcategory findByCategory(Category category);

	Page<Subcategory> findAll(Pageable pageable, SubcategoryFilterForm form);

}

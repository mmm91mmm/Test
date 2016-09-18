package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.CategoryFilterForm;
import ua.shop_e.Category;

public interface CategoryService {
	
	Category findByName(String name);
	
	void delete(int id);
	
	List<Category> findAll();

	void save(Category category);

	Category findOne(int id);

	Page <Category> findAll(Pageable pageable, CategoryFilterForm form);
}

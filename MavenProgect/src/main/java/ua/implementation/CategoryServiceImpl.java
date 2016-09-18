package ua.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.CategoryFilterForm;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;
import ua.shop_e.Category;
import ua.specification.CategoryFilterAdapter;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public void save(Category category) {
		if(categoryRepository.findByName(category.getName())==null){
		categoryRepository.save(category);
		}
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
		
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Page<Category> findAll(Pageable pageable, CategoryFilterForm form) {
		return categoryRepository.findAll(new CategoryFilterAdapter(form), pageable);
	}

}

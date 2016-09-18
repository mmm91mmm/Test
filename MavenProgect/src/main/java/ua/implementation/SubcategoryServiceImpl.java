package ua.implementation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.filterForm.SubcategoryFilterForm;
import ua.repository.SubcategoryRepository;
import ua.service.SubcategoryService;
import ua.shop_e.Category;
import ua.shop_e.Subcategory;
import ua.specification.SubcategoryFilterAdapter;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

	
	@Resource
	private  SubcategoryRepository  subcategoryRepository;
	
	@Override
	public Subcategory findByName(String name) {
		return subcategoryRepository.findByName(name);
	}

	
	@Override
	public void save(Subcategory form) {
			Subcategory subcategory = new Subcategory();
			subcategory.setName(form.getName());
			subcategory.setCategory(form.getCategory());
			subcategory.setId(form.getId());
			subcategoryRepository.save(subcategory);
	}
	
	@Override
	public void delete(int id) {
		subcategoryRepository.delete(id);
		
	}

	@Override
	public List<Subcategory> findAll() {
		return subcategoryRepository.findAll();
	}

	@Override
	public Subcategory findForForm(int id) {
		return subcategoryRepository.findOne(id);
	}

	@Override
	public Subcategory findOne(int id) {
		return subcategoryRepository.findOne(id);
	}

	@Override
	public Subcategory findByCategory(Category category) {	
		return subcategoryRepository.findByCategory(category);
	}


	@Override
	public Page<Subcategory> findAll(Pageable pageable, SubcategoryFilterForm form) {
		return subcategoryRepository.findAll(new SubcategoryFilterAdapter(form), pageable);
	}

}

package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.filterForm.ProductFilterForm;
import ua.shop_e.Product;

public interface ProductService {

	Product findByName(String name);
	
	void delete(int id);
	
	Product findById(int id);
	
	List<Product> findAll();

	void deliteSizes(int productId, int sizeId);

	void addSizes(int productId, String sizeName);
	
	List<Product> findAllInited();

	Product findForForm(int id);

	void save(Product form);

	Product findAllSizes(int id);
	
	Page<Product> findAll(Pageable pageable, ProductFilterForm form);

}

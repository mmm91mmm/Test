package ua.implementation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.filterForm.ProductFilterForm;
import ua.repository.ProductRepository;
import ua.repository.SizeRepository;
import ua.service.FileWriter;
import ua.service.ProductService;
import ua.shop_e.Product;
import ua.specification.ProductFilterAdapter;
	@Service
	public class ProductServiceImpl implements ProductService {

	
	@Resource
	private  ProductRepository  productRepository;
		
	@Autowired
	private FileWriter fileWriter;
	
	@Autowired
	private  SizeRepository  sizeRepository;
	
	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	@Transactional
	public void addSizes(int productId, String sizeName) {
		Product product = productRepository.findOne(productId);
		if (!product.getSizes().contains(sizeName)){
		product.getSizes().add(sizeRepository.findByName(sizeName));
		productRepository.save(product);
		}
	}
	
	@Override
	public void delete(int id) {
		productRepository.delete(id);
		
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public void deliteSizes(int productId, int sizeId) {
		Product product = productRepository.findOne(productId);
		product.getSizes().remove(sizeRepository.findOne(sizeId));
		productRepository.save(product);
	}

	@Override
	public Product findById(int id) {
	 return productRepository.findById(id);
	}

	@Override
	public List<Product> findAllInited() {
		return productRepository.findAllInited();
	}

	
	@Override
	public Product findForForm(int id) {
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public void save(Product form) {
		Product product = new Product();
		product.setCost(form.getCost());
		product.setName(form.getName());
		product.setColour(form.getColour());
		product.setCountry(form.getCountry());
		product.setManufacturer(form.getManufacturer());
		product.setSubcategory(form.getSubcategory());
		product.setId(form.getId());
		product.setPath(form.getPath());
		product.setVersion(form.getVersion());
		productRepository.saveAndFlush(product);
		String extension = fileWriter.write(FileWriter.Folder.PRODUCT, form.getFile(), product.getId());
		if(extension!=null){
			product.setVersion(form.getVersion()+1);
			product.setPath(extension);
			productRepository.save(product);
		}
	}
	

	@Override
	public Product findAllSizes(int id) {
		return productRepository.findAllSizes(id);
	}

	@Override
	@Transactional
	public Page<Product> findAll(Pageable pageable, ProductFilterForm form) {
		return productRepository.findAll(new ProductFilterAdapter(form), pageable);
	}

}

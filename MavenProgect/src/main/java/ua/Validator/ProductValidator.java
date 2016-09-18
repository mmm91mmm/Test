package ua.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.service.ProductService;
import ua.shop_e.Product;

public class ProductValidator implements Validator{
	
	private final ProductService productService;
	
	public ProductValidator(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product form = (Product) target;
		if(form.getId()==0)if(productService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Product already exists");
		}
		
		if(form.getCost()<=0){
			errors.rejectValue("cost", "", "cost must be > 0");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
	}
}
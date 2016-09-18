package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.ProductService;
import ua.shop_e.Product;

public class ProductEditor extends PropertyEditorSupport{

	private final ProductService productService;

	public ProductEditor(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Product product = productService.findById(Integer.valueOf(text));
		setValue(product);
	}
}
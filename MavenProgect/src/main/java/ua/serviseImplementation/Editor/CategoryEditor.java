package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.CategoryService;
import ua.shop_e.Category;

public class CategoryEditor extends PropertyEditorSupport{

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findOne(Integer.valueOf(text));
		setValue(category);
	}
}
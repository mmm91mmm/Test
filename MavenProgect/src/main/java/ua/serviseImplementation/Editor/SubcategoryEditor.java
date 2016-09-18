package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.SubcategoryService;
import ua.shop_e.Subcategory;

public class SubcategoryEditor extends PropertyEditorSupport{

	private final SubcategoryService subcategoryService;

	public SubcategoryEditor(SubcategoryService subcategoryService) {
		this.subcategoryService = subcategoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Subcategory subcategory = subcategoryService.findOne(Integer.valueOf(text));
		setValue(subcategory);
	}
}
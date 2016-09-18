package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.SizeService;
import ua.shop_e.Size;

public class SizeEditor extends PropertyEditorSupport{

	private final SizeService sizeService;

	public SizeEditor(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Size size = sizeService.findOne(Integer.valueOf(text));
		setValue(size);
	}
}
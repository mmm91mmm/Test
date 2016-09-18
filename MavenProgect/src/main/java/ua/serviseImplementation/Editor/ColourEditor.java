package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.ColourService;
import ua.shop_e.Colour;

public class ColourEditor extends PropertyEditorSupport{

	private final ColourService colourService;

	public ColourEditor(ColourService colourService) {
		this.colourService = colourService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Colour colour = colourService.findOne(Integer.valueOf(text));
		setValue(colour);
	}
}
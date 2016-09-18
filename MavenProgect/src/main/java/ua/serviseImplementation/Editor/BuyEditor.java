package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.BuyService;
import ua.shop_e.Buy;

public class BuyEditor extends PropertyEditorSupport{

	private final BuyService buyService;

	public BuyEditor(BuyService buyService) {
		this.buyService = buyService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Buy buy = buyService.findById(Integer.valueOf(text));
		setValue(buy);
	}
}
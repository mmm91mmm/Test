package ua.serviseImplementation.Editor;

import java.beans.PropertyEditorSupport;

import ua.service.MyOrderService;
import ua.shop_e.MyOrder;

public class MyOrderEditor extends PropertyEditorSupport{

	private final MyOrderService myOrderService;

	public MyOrderEditor(MyOrderService myOrderService) {
		this.myOrderService = myOrderService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MyOrder myOrder = myOrderService.findOne(Integer.valueOf(text));
		setValue(myOrder);
	}
}
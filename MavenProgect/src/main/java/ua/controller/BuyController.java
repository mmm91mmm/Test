package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.service.MyOrderService;
import ua.serviseImplementation.Editor.MyOrderEditor;
import ua.shop_e.Buy;
import ua.shop_e.MyOrder;

public class BuyController {

	@Autowired
	private MyOrderService myOrderService;
	
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(MyOrder.class, new MyOrderEditor(myOrderService));
	}
	
	@ModelAttribute("form")
	public Buy getForm(){
		return new Buy();
	}
}

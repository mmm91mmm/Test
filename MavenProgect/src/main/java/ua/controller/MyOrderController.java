package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.service.ColourService;
import ua.service.MyOrderService;
import ua.service.ProductService;
import ua.service.SizeService;
import ua.service.UserService;
import ua.serviseImplementation.Editor.ColourEditor;
import ua.serviseImplementation.Editor.ProductEditor;
import ua.serviseImplementation.Editor.SizeEditor;
import ua.serviseImplementation.Editor.UserEditor;
import ua.shop_e.Colour;
import ua.shop_e.MyOrder;
import ua.shop_e.Product;
import ua.shop_e.Size;
import ua.shop_e.User;

@Controller
public class MyOrderController {

	@Autowired
	private MyOrderService myOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ColourService colourService;
	
	@Autowired
	private SizeService sizeService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(User.class, new UserEditor(userService));
	   binder.registerCustomEditor(Colour.class, new ColourEditor(colourService));
	   binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
	   binder.registerCustomEditor(Product.class, new ProductEditor(productService));
	}
	
	@ModelAttribute("form")
	public MyOrder getMyOrder(){
		return new MyOrder();
	}
	
	@RequestMapping("/admin/myOrder")
	public String showMyOrder(Model model, @PageableDefault(5) Pageable pageable){
		model.addAttribute("myOrders", myOrderService.findAll());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("colors", colourService.findAll());
		model.addAttribute("users", userService.findAll());
		return "adminMyOrder";
	}
	
	
	@RequestMapping("/user/myOrder/delete/{id}")
	public String deleteMyOrderUser(@PathVariable int id){
		myOrderService.delete(id);
		return "redirect:/user/myOrder";
	}
	
	
	@RequestMapping(value= "/admin/myOrder", method=RequestMethod.POST)
	public String saveMyOrderAdmin(@ModelAttribute("form") MyOrder form){
		myOrderService.save(form);
		return "redirect:/admin/myOrder";
	}
	
	//////////////////////////// User Part //////////////////////////////////
	
	@RequestMapping("/user/myOrder")
	public String showMyOrderUser(Model model){
		model.addAttribute("myOrders", myOrderService.findAll());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("colors", colourService.findAll());
		return "userMyOrder";
	}
	
	@RequestMapping(value= "/user/myOrder", method=RequestMethod.POST)
	public String saveMyOrderUser(@ModelAttribute("form") MyOrder form){
		myOrderService.save(form);
		return "redirect:/user/myOrder";
	}
	
	@RequestMapping("/admin/myOrder/delete/{id}")
	public String deleteMyOrder(@PathVariable int id){
		myOrderService.delete(id);
		return "redirect:/admin/myOrder";
	}
	
}

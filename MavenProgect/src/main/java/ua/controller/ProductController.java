package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.Validator.ProductValidator;
import ua.filterForm.ProductFilterForm;
import ua.service.ColourService;
import ua.service.CountryService;
import ua.service.ManufacturerService;
import ua.service.ProductService;
import ua.service.SizeService;
import ua.service.SubcategoryService;
import ua.serviseImplementation.Editor.ColourEditor;
import ua.serviseImplementation.Editor.CountryEditor;
import ua.serviseImplementation.Editor.ManufacturerEditor;
import ua.serviseImplementation.Editor.SubcategoryEditor;
import ua.shop_e.Colour;
import ua.shop_e.Country;
import ua.shop_e.Manufacturer;
import ua.shop_e.Product;
import ua.shop_e.Subcategory;

@Controller
public class ProductController {
		
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private ColourService colourService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private CountryService countryService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Subcategory.class, new SubcategoryEditor(subcategoryService));
	   binder.registerCustomEditor(Manufacturer.class, new ManufacturerEditor(manufacturerService));
	   binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
	   binder.registerCustomEditor(Colour.class, new ColourEditor(colourService));
	   binder.setValidator(new ProductValidator(productService));
	}
	
	@ModelAttribute("form")
	public Product getForm(){
		return new Product();
	}
	
	@ModelAttribute("filter")
	public ProductFilterForm getFilter(){
		return new ProductFilterForm();
	}
	
	@RequestMapping("/user/product")
	public String showProductForUser(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ProductFilterForm form){
		model.addAttribute("page", productService.findAll(pageable, form));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("subcategorys", subcategoryService.findAll());
		model.addAttribute("colors", colourService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "userProduct";
	}
		
	@RequestMapping("/admin/product")
	public String showProduct(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ProductFilterForm form){
		model.addAttribute("page", productService.findAll(pageable, form));
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("subcategorys", subcategoryService.findAll());
		model.addAttribute("colors", colourService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("productSize", productService.findAll());
		return "adminProduct";
	}
	
	@RequestMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ProductFilterForm form){
		productService.delete(id);
		return "redirect:/admin/product"+getParams(pageable, form);
	}
	
	@RequestMapping(value= "/admin/product", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("form") @Valid Product product,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ProductFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", productService.findAll(pageable, form));
			model.addAttribute("countrys", countryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("subcategorys", subcategoryService.findAll());
			model.addAttribute("colors", colourService.findAll());
			model.addAttribute("sizes", sizeService.findAll());
			return "adminProduct";
		}
		productService.save(product);
		return "redirect:/admin/product"+getParams(pageable, form);
	}
	
	@RequestMapping(value="/admin/product/update/{id}")
	public String update(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ProductFilterForm form){
		model.addAttribute("form", productService.findForForm(id));
		model.addAttribute("page", productService.findAll(pageable, form));
		model.addAttribute("subcategorys", subcategoryService.findAll());
		model.addAttribute("countrys", countryService.findAll());
		model.addAttribute("colors", colourService.findAll());
		model.addAttribute("manufacturers", manufacturerService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "adminProduct";
	}
	
	@RequestMapping(value= "/admin/product/deliteSize/{id}", method=RequestMethod.POST)
	public String deliteSize(@PathVariable int id, @RequestParam int sizeId){
		productService.deliteSizes(id, sizeId);
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value= "/admin/product/addSize", method=RequestMethod.POST)
	public String addSize(@RequestParam int productId, @RequestParam String sizeName){
		productService.addSizes(productId, sizeName);
		return "redirect:/admin/product";
	}
	
	private String getParams(Pageable pageable, ProductFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&min=");
		buffer.append(form.getMin());
		buffer.append("&max=");
		buffer.append(form.getMax());
		for(Integer i : form.getSubcategoryIds()){
			buffer.append("&subcategoryIds=");
			buffer.append(i.toString());
		}
		for(Integer i : form.getManufacturerIds()){
			buffer.append("&manufacturerIds=");
			buffer.append(i.toString());
		}
		for(Integer i : form.getColourIds()){
			buffer.append("&colourIds=");
			buffer.append(i.toString());
		}
		for(Integer i : form.getCountryIds()){
			buffer.append("&countryIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
	}
}
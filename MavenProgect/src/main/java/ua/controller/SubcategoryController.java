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

import ua.Validator.SubcategoryValidator;
import ua.filterForm.SubcategoryFilterForm;
import ua.service.CategoryService;
import ua.service.SubcategoryService;
import ua.serviseImplementation.Editor.CategoryEditor;
import ua.shop_e.Category;
import ua.shop_e.Subcategory;


@Controller
public class SubcategoryController {
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("filter")
	public SubcategoryFilterForm getFilter(){
		return new SubcategoryFilterForm();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	   binder.setValidator(new SubcategoryValidator(subcategoryService));
	}
	
	@ModelAttribute("form")
	public Subcategory getForm(){
		return new Subcategory();
	}
	
	@RequestMapping("/admin/subcategory")
	public String showSubcategory(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SubcategoryFilterForm form){
		model.addAttribute("page", subcategoryService.findAll(pageable, form));
		model.addAttribute("categorys", categoryService.findAll());
		return "adminSubcategory";
	}
	
	@RequestMapping("/admin/subcategory/delete/{id}")
	public String deleteSubcategory(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SubcategoryFilterForm form){
		subcategoryService.delete(id);
		return "redirect:/admin/subcategory"+getParams(pageable, form);
	}
		
	
	@RequestMapping(value= "/admin/subcategory", method=RequestMethod.POST)
	public String saveSubcategory(@ModelAttribute("form") @Valid Subcategory subcategory,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SubcategoryFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", subcategoryService.findAll(pageable, form));
			model.addAttribute("categorys", categoryService.findAll());
			return "adminSubcategory";
		}
		subcategoryService.save(subcategory);
		return "redirect:/admin/subcategory"+getParams(pageable, form);
	}
	
	@RequestMapping(value="/admin/subcategory/update/{id}")
	public String update(Model model, @PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SubcategoryFilterForm form){
		model.addAttribute("form", subcategoryService.findForForm(id));
		model.addAttribute("page", subcategoryService.findAll(pageable, form));
		model.addAttribute("categorys", categoryService.findAll());
		return "adminSubcategory";
	}
	
	private String getParams(Pageable pageable, SubcategoryFilterForm form){
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
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}

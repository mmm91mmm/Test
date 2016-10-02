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

import ua.Validator.SizeValidator;
import ua.filterForm.SizeFilterForm;
import ua.service.SizeService;
import ua.shop_e.Size;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;

	@ModelAttribute("size")
	public Size getSize(){
		return new Size();
	}
	
	@ModelAttribute("filter")
	public SizeFilterForm getFilter(){
		return new SizeFilterForm();
	}
	
	@InitBinder("size")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new SizeValidator(sizeService));
	}
	
	@RequestMapping("/admin/size")
	public String showSizes(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SizeFilterForm form){
		model.addAttribute("page", sizeService.findAll(pageable, form));
		return "adminSize";
	}
	
	@RequestMapping("/admin/size/delete/{id}")
	public String deleteSize(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SizeFilterForm form){
		sizeService.delete(id);
		return "redirect:/admin/size"+getParams(pageable, form);
	}
	
	@RequestMapping(value= "/admin/size", method=RequestMethod.POST)
	public String saveSize(@ModelAttribute("country") @Valid Size size,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SizeFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", sizeService.findAll(pageable, form));
			return "adminSize";
		}
		sizeService.save(size);
		return "redirect:/admin/size"+getParams(pageable, form);
	}
	@RequestMapping("/admin/size/update/{id}")
	public String updateSize(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") SizeFilterForm form){
		model.addAttribute("size", sizeService.findOne(id));
		model.addAttribute("page", sizeService.findAll(pageable, form));
		return "adminSize";
	}
	
	private String getParams(Pageable pageable, SizeFilterForm form){
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

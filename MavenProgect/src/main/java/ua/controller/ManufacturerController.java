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

import ua.Validator.ManufacturerValidator;
import ua.filterForm.ManufacturerFilterForm;
import ua.service.ManufacturerService;
import ua.shop_e.Manufacturer;

@Controller
public class ManufacturerController {
		
	@Autowired
	private ManufacturerService manufacturerService;
	
	@ModelAttribute("manufacturer")
	public Manufacturer getManufacturer(){
		return new Manufacturer();
	}
	
	@ModelAttribute("filter")
	public ManufacturerFilterForm getFilter(){
		return new ManufacturerFilterForm();
	}

	@InitBinder("manufacturer")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ManufacturerValidator(manufacturerService));
	}
	
	@RequestMapping("/admin/manufacturer")
	public String showManufacturer(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ManufacturerFilterForm form){
		model.addAttribute("page", manufacturerService.findAll(pageable, form));
		return "adminManufacturer";
	}
	
	@RequestMapping(value= "/admin/manufacturer", method=RequestMethod.POST)
	public String save (@ModelAttribute("country") @Valid Manufacturer manufacturer,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ManufacturerFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", manufacturerService.findAll(pageable, form));
			return "adminManufacturer";
		}
		manufacturerService.save(manufacturer);
		return "redirect:/admin/manufacturer"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/manufacturer/delete/{id}")
	public String deleteManufacturer(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ManufacturerFilterForm form){
		manufacturerService.delete(id);
		return "redirect:/admin/manufacturer"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/manufacturer/update/{id}")
	public String updateManufacturer(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ManufacturerFilterForm form){
		model.addAttribute("manufacturer", manufacturerService.findOne(id));
		model.addAttribute("page", manufacturerService.findAll(pageable, form));
		return "adminManufacturer";
	}

	private String getParams(Pageable pageable, ManufacturerFilterForm form){
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

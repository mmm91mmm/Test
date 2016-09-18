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

import ua.Validator.CountryValidator;
import ua.filterForm.CountryFilterForm;
import ua.service.CountryService;
import ua.shop_e.Country;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	@ModelAttribute("country")
	public Country getCountry(){
		return new Country();
	}
	
	@ModelAttribute("filter")
	public CountryFilterForm getFilter(){
		return new CountryFilterForm();
	}
	
	@InitBinder("country")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new CountryValidator(countryService));
	}

	@RequestMapping("/admin/country")
	public String showCountry(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CountryFilterForm form){
		model.addAttribute("page", countryService.findAll(pageable, form));
		return "adminCountry";
	}
	
	@RequestMapping("/admin/country/delete/{id}")
	public String deleteCountry(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CountryFilterForm form){
		countryService.delete(id);
		return "redirect:/admin/country"+getParams(pageable, form);
	}

	@RequestMapping(value= "/admin/country", method=RequestMethod.POST)
	public String saveCountry(@ModelAttribute("country") @Valid Country country,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CountryFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", countryService.findAll(pageable, form));
			return "adminCountry";
		}
			countryService.save(country);
		return "redirect:/admin/country"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/country/update/{id}")
	public String updateCountry(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CountryFilterForm form){
		model.addAttribute("country", countryService.findOne(id));
		model.addAttribute("page", countryService.findAll(pageable, form));
		return "adminCountry";
	}
	private String getParams(Pageable pageable, CountryFilterForm form){
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
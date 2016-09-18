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

import ua.Validator.ColourValidator;
import ua.filterForm.ColourFilterForm;
import ua.service.ColourService;
import ua.shop_e.Colour;

@Controller
public class ColourController {

	@Autowired
	private ColourService colourService;

	@ModelAttribute("colour")
	public Colour getÑolour(){
		return new Colour();
	}
	
	@ModelAttribute("filter")
	public ColourFilterForm getFilter(){
		return new ColourFilterForm();
	}
	
	@InitBinder("colour")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ColourValidator(colourService));
	}
	
	@RequestMapping("/admin/colour")
	public String showColors(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ColourFilterForm form){
		model.addAttribute("page", colourService.findAll(pageable, form));
		return "adminColour";
	}
	
	@RequestMapping("/admin/colour/delete/{id}")
	public String deleteColour(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ColourFilterForm form){
		colourService.delete(id);
		return "redirect:/admin/colour"+getParams(pageable, form);
	}
	
	@RequestMapping(value= "/admin/colour", method=RequestMethod.POST)
	public String saveColour(@ModelAttribute("colour") @Valid Colour colour,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ColourFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", colourService.findAll(pageable, form));
			return "adminColour";
		}
		colourService.save(colour);
		return "redirect:/admin/colour"+getParams(pageable, form);
	}
	@RequestMapping("/admin/colour/update/{id}")
	public String updateColour(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ColourFilterForm form){
		model.addAttribute("colour", colourService.findOne(id));
		model.addAttribute("page", colourService.findAll(pageable, form));
		return "adminColour";
	}
	private String getParams(Pageable pageable, ColourFilterForm form){
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


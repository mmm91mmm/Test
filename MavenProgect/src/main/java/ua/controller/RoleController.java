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

import ua.Validator.RoleValidator;
import ua.filterForm.CountryFilterForm;
import ua.filterForm.RoleFilterForm;
import ua.service.RoleService;
import ua.shop_e.Country;
import ua.shop_e.Role;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")
	public Role getRole(){
		return new Role();
	}
	
	@ModelAttribute("filter")
	public RoleFilterForm getFilter(){
		return new RoleFilterForm();
	}
	
	@InitBinder("role")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new RoleValidator(roleService));
	}
	
	@RequestMapping("/admin/role")
	public String showRoles(Model model, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") RoleFilterForm form){
		model.addAttribute("page", roleService.findAll(pageable, form));
		return "adminRole";
	}
	
	@RequestMapping("/admin/role/delete/{id}")
	public String deleteRole(@PathVariable int id, @PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") RoleFilterForm form){
		roleService.delete(id);
		return "redirect:/admin/role"+getParams(pageable, form);
	}
	
	@RequestMapping(value= "/admin/role", method=RequestMethod.POST)
	public String saveRole(@ModelAttribute("country") @Valid Role role,
			BindingResult br,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") RoleFilterForm form,
			Model model){
		if(br.hasErrors()){
			model.addAttribute("page", roleService.findAll(pageable, form));
			return "adminRole";
		}
		roleService.save(role);
	return "redirect:/admin/role"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/role/update/{id}")
	public String updateRole(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") RoleFilterForm form){
		model.addAttribute("role", roleService.findOne(id));
		model.addAttribute("page", roleService.findAll(pageable, form));
		return "adminRole";
	}
	
	private String getParams(Pageable pageable, RoleFilterForm form){
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

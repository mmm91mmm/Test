package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import ua.Validator.UserValidator;
import ua.service.RoleService;
import ua.service.UserService;
import ua.serviseImplementation.Editor.RoleEditor;
import ua.serviseImplementation.Editor.UserEditor;
import ua.shop_e.Role;
import ua.shop_e.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(User.class, new UserEditor(userService));
	   binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
	   binder.setValidator(new UserValidator(userService));
	}
	
	@ModelAttribute("form")
	public User getForm(){
		return new User();
	}
	
	@RequestMapping("/admin/user")
	public String showUser(Model model, @PageableDefault(5) Pageable pageable){
		model.addAttribute("page", userService.findAll(pageable));
		model.addAttribute("roles", roleService.findAll());
		return "adminUser";
	}
	
	@RequestMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/admin/user";
	}
	
	
	@RequestMapping(value= "/admin/user", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("form") @Valid User form, BindingResult br, Model model){
		if(br.hasErrors()){
			model.addAttribute("users", userService.findAll());
			model.addAttribute("roles", roleService.findAll());
			return "adminUser";
		}
		userService.save(form);
		return "redirect:/admin/user";
	}
	
	@RequestMapping("/admin/user/update/{id}")
	public String updateUser(@PathVariable int id, Model model){
		model.addAttribute("user", userService.findOneUser(id));
		model.addAttribute("role", roleService.findOne(id));
		return "adminUser";
	}
	
}

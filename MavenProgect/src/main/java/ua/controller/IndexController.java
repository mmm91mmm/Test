package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//	@RequestMapping("/")
//	public String showIndex(Principal principal){
//		System.out.println(principal);
//		System.out.println(System.getProperty("catalina.home"));
//		return "index";
//	}
//	
//	@RequestMapping("/admin")
//	public String showAdmin() {
//		return "adminPanel";
//	}
//	
//	@RequestMapping("/login")
//	public String showLogin() {
//		return "login";
//	}
	
	@RequestMapping("/")
	public String showIndex(){
		System.out.println(System.getProperty("catalina.home"));
		return "index";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(){
		return "adminPanel";
	}
	
	@RequestMapping("/user")
	public String showUser(){
		return "userPanel";
	}
	
}
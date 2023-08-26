package com.ahievran.besyoSinav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahievran.besyoSinav.business.abstracts.AdminService;
import com.ahievran.besyoSinav.business.requests.CreateAdminRequest;

@Controller
@RequestMapping("/registration")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public String showRegistration() {
		return "registration";
	}
	
	@ModelAttribute("admin")
	public CreateAdminRequest createAdminRequest() {
		return new CreateAdminRequest();
	}
	
	@PostMapping()
	public String adminRegistrationController(@ModelAttribute("admin") CreateAdminRequest createAdminRequest) {
		this.adminService.add(createAdminRequest);
		return "redirect:registration?success";
	}
}
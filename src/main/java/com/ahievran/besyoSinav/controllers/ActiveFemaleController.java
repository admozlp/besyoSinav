package com.ahievran.besyoSinav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ahievran.besyoSinav.business.abstracts.ActiveStudentService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class ActiveFemaleController {

	@Autowired
	ActiveStudentService activeStudentService;
	
	Ogrenci aktifBayan=null;
	
	
	@GetMapping("/aktifbayan")
	public String showActiveFeale(Model model) {
		Ogrenci parkurda = activeStudentService.findActiveStudent("Bayan"); 
		String yol="";
		if(parkurda != null) {
			model.addAttribute("aktifBayan", parkurda);
			aktifBayan = parkurda;
			yol = parkurda.getResim().getYol();
			
		}else {
			if(aktifBayan != null) {
				yol = aktifBayan.getResim().getYol();
				model.addAttribute("aktifBayan", aktifBayan);
			}else {
				model.addAttribute("aktifBayan", null);
			}
		
		}
		
		String path = "/images/" + yol;
		model.addAttribute("path", path);
		
		return "aktifbayan";
	}
	
	@GetMapping("/tamamlandimibayan")
	public String isTestCompleted() {
		try {
			if(aktifBayan != null) {
				aktifBayan = activeStudentService.findByTc(aktifBayan.getTcNo());
				if(aktifBayan.getDurum().getDurumu().equals("Sınava Girdi")) {
					return "redirect:/aktifbayan";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/aktifbayan";
	}
}
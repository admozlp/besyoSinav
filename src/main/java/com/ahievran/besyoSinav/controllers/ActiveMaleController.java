package com.ahievran.besyoSinav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ahievran.besyoSinav.business.abstracts.ActiveStudentService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class ActiveMaleController {
	@Autowired
	ActiveStudentService activeStudentService;
	
	Ogrenci aktifErkek=null;
	
	
	@GetMapping("/aktiferkek")
	public String showActiveMale(Model model) {
		Ogrenci parkurda = activeStudentService.findActiveStudent("Erkek"); 
		String yol="";
		if(parkurda != null) {
			model.addAttribute("aktifErkek", parkurda);
			aktifErkek = parkurda;
			yol = parkurda.getResim().getYol();
			
		}else {
			if(aktifErkek != null) {
				yol = aktifErkek.getResim().getYol();	
				model.addAttribute("aktifErkek", aktifErkek);
			}else {
				model.addAttribute("aktifErkek", null);
			}
			
		}
		String path = "/images/" + yol;
		model.addAttribute("path", path);
		
		return "aktiferkek";
	}
	
	@GetMapping("/tamamlandimierkek")
	public String isTestCompleted() {
		try {
			if(aktifErkek != null) {
				aktifErkek = activeStudentService.findByTc(aktifErkek.getTcNo());	
				if(aktifErkek.getDurum().getDurumu().equals("Sınava Girdi")) {
					return "redirect:/aktiferkek";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/aktiferkek";
	}
	
	
}
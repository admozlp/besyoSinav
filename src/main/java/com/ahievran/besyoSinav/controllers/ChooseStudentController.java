package com.ahievran.besyoSinav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.ChooseStudentService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
@RequestMapping("/ogrencisec")
public class ChooseStudentController {
	@Autowired
	private ChooseStudentService chooseStudentService;
	
	public ChooseStudentController(ChooseStudentService chooseStudentService) {
		this.chooseStudentService = chooseStudentService;
	}
	Ogrenci ogrenci;
	
	@GetMapping
	public String showOgrenciSec() {
		return "ogrencisec";
	}
	
	@PostMapping()
	public String ogrenciSec(@RequestParam("tcno") String tcNo, RedirectAttributes redirectAttributes) {
		this.ogrenci = chooseStudentService.findByTcNo(tcNo);
		if(ogrenci != null) {
			String path = "/images/" + ogrenci.getResim().getYol();
			redirectAttributes.addFlashAttribute("path", path);				
			redirectAttributes.addFlashAttribute("secilenogrenci", ogrenci);
			return "redirect:/ogrencisec?ogrenci";
		}
		return "redirect:ogrencisec?mevcutdegil";
	}
	
	@GetMapping("/parkurda")
	public String durumParkurda(RedirectAttributes redirectAttributes, Model model) {
		try {
			Ogrenci parkurdaogrenci = chooseStudentService.findByDurumAndCinsiyet("Parkurda", ogrenci.getCinsiyet());
			if(parkurdaogrenci == null) {
				chooseStudentService.updateOgrenciDurum(ogrenci, "Parkurda");
				return "redirect:?parkurda";
			}else {
				return "redirect:?parkurdaogrencivar";
			}
		}catch (Exception e) {
			return "redirect:?error";
		}
	}
	
	@GetMapping("/girmedi")
	public String durumDiskalifiye(RedirectAttributes redirectAttributes, Model model) {
		try {
			chooseStudentService.updateOgrenciDurum(ogrenci, "Sınava Girmedi");
			return "redirect:?girmedi";

		}catch (Exception e) {
			return "redirect:?error";
		}
	}
}

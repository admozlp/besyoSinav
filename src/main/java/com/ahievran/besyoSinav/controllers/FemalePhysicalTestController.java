package com.ahievran.besyoSinav.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.FemalePhysicalTestService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class FemalePhysicalTestController {
	List<String> options = Arrays.asList("Sınava Girdi", "Sınava Girmedi", "Diskalifiye Edildi");
	String selectedOption = "Sınava Girdi";

	@Autowired
	private FemalePhysicalTestService femaleService;
	
	@GetMapping("/bayansinav")
	public String showMalePhysicalTest(@RequestParam(value = "tcno", required = false) String tcNo, Model model) {
		
		if (tcNo != null) {
			tcNo = tcNo.trim();
			Ogrenci ogrenci = femaleService.getStudentByTc(tcNo);
			if (ogrenci != null) {
				model.addAttribute("options", options);
				model.addAttribute("selectedOption", selectedOption);
				String path = "/images/" + ogrenci.getResim().getYol();
				model.addAttribute("path", path);
				model.addAttribute("ogrenci", ogrenci);
				return "bayansinav";
			}
		} 
		model.addAttribute("ogrenci", null);
		return "bayansinav";
		// 27004974250
	}
	
	@GetMapping("/anlikbayan")
	public String showeCurrentActiveStudent(Model model, RedirectAttributes redirectAttributes) {
		Ogrenci ogrenci = femaleService.getByDurumAndCinsiyet("Parkurda");
		if (ogrenci != null) {
			model.addAttribute("options", options);
			model.addAttribute("selectedOption", selectedOption);
			String path = "/images/" + ogrenci.getResim().getYol();
			model.addAttribute("path", path);		
			model.addAttribute("ogrenci", ogrenci);
			return "bayansinav";
		}
		return "redirect:/bayansinav";
	}
	@GetMapping("/bayanadsoyad")
	public String adSoyadGoreGetir(@RequestParam(value="aday", required = false) String aday, RedirectAttributes redirectAttributes) {		
		if(aday != null) {
			aday = aday.trim();
			List<Ogrenci> femalestudents = femaleService.getByName(aday.toUpperCase());
			if(femalestudents.size() <= 0) {
				femalestudents = femaleService.findyByTel(aday.replaceAll("\\s+", ""));				
				if(femalestudents.size() <= 0) {
					try {
						femalestudents = femaleService.findBySiraNo(Integer.parseInt(aday));
					}catch (Exception e) {
						return "redirect:bayansinav?mevcutdegil";	
					}
					if(femalestudents.size() <= 0) {
						try {
							femalestudents = femaleService.getById(Integer.parseInt(aday));	
						}catch (Exception e) {
							return "redirect:bayansinav?mevcutdegil";	
						}						
					}
				}
			}			
			 redirectAttributes.addFlashAttribute("femalestudents", femalestudents);
			return "redirect:/bayansinav";
		}
		return "redirect:/bayansinav";
	}
	
	@GetMapping("/bayanhesapla")
	public String hesapla(@RequestParam("sure") double sure, @RequestParam("hataPuani") int hataPuani, @RequestParam("tcno") String tcno , Model model, RedirectAttributes redirectAttributes) {
		try {
			System.out.println(sure);
			double rawScore = femaleService.calculateRawScore(sure);
			double score = femaleService.calculateScore(sure, hataPuani); 
			
			 femaleService.updateScore(tcno, sure, hataPuani, rawScore, score);
			return "redirect:/bayansinav?tcno="+tcno;
		}catch (Exception e) {
			return "redirect:/bayansinav";
		}
	}
	
	@PostMapping("/bayankaydet")
	public String kaydet( @RequestParam("selectedOption") String selectedOption,@RequestParam("tcno") String tcno,HttpServletRequest request  ,Model model, RedirectAttributes redirectAttributes) {
		try {
			femaleService.updateSinav(tcno, selectedOption, request.getRemoteAddr());
		} catch (Exception e) {
			return "redirect:?error";
		}
		return "redirect:/bayansinav";
	}
	
	
	@GetMapping("/parkuralbayan")
	public String parkuraAl(@RequestParam("tcno") String tcno) {
		try {
			if(femaleService.updateDurum(tcno)) {
				System.out.println("Tc No : " + tcno);
				return "redirect:/bayansinav?tcno="+tcno;
			}else {
				return "redirect:bayansinav?aktifadaymevcut";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/bayansinav";
		}
	}
	
	
}
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

import com.ahievran.besyoSinav.business.abstracts.MalePhysicalTestService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class MalePhysicalTestController {
	String selectedOption = "Sınava Girdi";	
	List<String> options = Arrays.asList("Sınava Girdi", "Sınava Girmedi", "Diskalifiye Edildi");
	@Autowired
	private MalePhysicalTestService maleService;

	@GetMapping("/erkeksinav")
	public String showMalePhysicalTest(@RequestParam(value = "tcno", required = false) String tcNo, Model model) {
		if (tcNo != null) {
			tcNo = tcNo.trim();			
			Ogrenci ogrenci = maleService.getStudentByTc(tcNo);
			if (ogrenci != null) {
				model.addAttribute("options", options);
				model.addAttribute("selectedOption", selectedOption);
				String path = "/images/" + ogrenci.getResim().getYol();
				model.addAttribute("path", path);
				model.addAttribute("ogrenci", ogrenci);
				return "erkeksinav";
			}
		} 
		model.addAttribute("ogrenci", null);
		return "erkeksinav";
		// 27004974250
	}

	@GetMapping("/anlikerkek")
	public String showeCurrentActiveStudent(Model model, RedirectAttributes redirectAttributes) {
		Ogrenci ogrenci = maleService.getByDurumAndCinsiyet("Parkurda");
		if (ogrenci != null) {
			model.addAttribute("options", options);
			model.addAttribute("selectedOption", selectedOption);
			String path = "/images/" + ogrenci.getResim().getYol();
			model.addAttribute("path", path);		
			model.addAttribute("ogrenci", ogrenci);
			return "erkeksinav";
		}
		return "redirect:/erkeksinav";
	}
	
	@GetMapping("/erkekadsoyad")
	public String adSoyadGoreGetir(@RequestParam(value="aday", required = false) String aday, RedirectAttributes redirectAttributes) {		
		if(aday != null) {
			aday = aday.trim();
			List<Ogrenci> malestudents = maleService.getByName(aday.toUpperCase());
			if(malestudents.size() <= 0) {
				malestudents = maleService.findyByTel(aday.replaceAll("\\s+", ""));				
				if(malestudents.size() <= 0) {
					try {
						malestudents = maleService.findBySiraNo(Integer.parseInt(aday));	
					}catch (Exception e) {
						return "redirect:erkeksinav?mevcutdegil";					
					}						
						if(malestudents.size() <= 0) {
							try {
								malestudents = maleService.getById(Integer.parseInt(aday));	
							}catch (Exception e) {
								return "redirect:erkeksinav?mevcutdegil";	
								}							
						}
				}
			}			
			 redirectAttributes.addFlashAttribute("malestudents", malestudents);
			return "redirect:/erkeksinav";
		}
		return "redirect:/erkeksinav";
	}
	
	@GetMapping("/erkekhesapla")
	public String hesapla(@RequestParam("sure") double sure, @RequestParam("hataPuani") int hataPuani, @RequestParam("tcno") String tcno , Model model, RedirectAttributes redirectAttributes) {
	
			try {
				double rawScore = maleService.calculateRawScore(sure);
				double score = maleService.calculateScore(sure, hataPuani); 
				
				 maleService.updateScore(tcno, sure, hataPuani, rawScore, score);
				return "redirect:/erkeksinav?tcno="+tcno;
			}catch (Exception e) {
				return "redirect:/erkeksinav";
			}
	}
	
	@PostMapping("/erkekkaydet")
	public String kaydet( @RequestParam("selectedOption") String selectedOption,@RequestParam("tcno") String tcno,HttpServletRequest request  ,Model model, RedirectAttributes redirectAttributes) {

		try {
			maleService.updateSinav(tcno, selectedOption, request.getRemoteAddr());
		} catch (Exception e) {
			return "redirect:erkeksinav?error";
		}
		return "redirect:/erkeksinav";
	}
	
	@GetMapping("/parkuralerkek")
	public String parkuraAl(@RequestParam("tcno") String tcno) {
		try {
			if(maleService.updateDurum(tcno)) {
				System.out.println("Tc No : " + tcno);
				return "redirect:/erkeksinav?tcno="+tcno;
			}else {
				return "redirect:erkeksinav?aktifadaymevcut";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/erkeksinav";
		}
	}
	
	
}
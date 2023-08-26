package com.ahievran.besyoSinav.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.ScoreService;
import com.ahievran.besyoSinav.business.requests.BayanPuanListe;
import com.ahievran.besyoSinav.business.requests.ErkekPuanListe;
import com.ahievran.besyoSinav.business.requests.UpdateFieldEffectsRequest;
import com.ahievran.besyoSinav.entities.AlanEtkisi;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UpdateFieldsController {
	private ScoreService scoreService;
	
	@GetMapping("/alanlariguncelle")
	public String showModifiableFields(Model model) {
		ErkekPuanListe erkekPuanlarListe = scoreService.getAllMaleScores() ;
		BayanPuanListe bayanPuanlarListe = scoreService.getAllFemaleScores();
		List<AlanEtkisi> alanEtkileri = scoreService.getFieldEffects();
		AlanEtkisi alanEtkisi = alanEtkileri.get(0);
		model.addAttribute("erkekPuanlarListe", erkekPuanlarListe);
		model.addAttribute("bayanPuanlarListe", bayanPuanlarListe);
		model.addAttribute("alanEtkisi", alanEtkisi);
		
		return "alanlariguncelle";
	}
	
	@ModelAttribute("erkekPuanlarListe")
	public ErkekPuanListe erkekPuan() {
		return new ErkekPuanListe();
	}
	
	@PostMapping("/erkekpuanguncelle")
	public String updateMaleScore(@ModelAttribute("erkekPuanlarListe") ErkekPuanListe erkekPuanListe, RedirectAttributes redirectAttributes) {
		try {
			scoreService.updateMaleScores(erkekPuanListe);
		}catch (Exception e) {
			return "redirect:alanlariguncelle?error";
		}
		return "redirect:/alanlariguncelle";
	}
	
	@ModelAttribute("bayanPuanlarListe")
	public BayanPuanListe bayanPuan() {
		return new BayanPuanListe();
	}
	
	@PostMapping("/bayanpuanguncelle")
	public String updateFemaleScore(@ModelAttribute("bayanPuanlarListe") BayanPuanListe bayanPuanListe, RedirectAttributes redirectAttributes) {
		try {
			scoreService.updateFeMaleScores(bayanPuanListe);
		}catch (Exception e) {
			return "redirect:alanlariguncelle?error";
		}
		return "redirect:/alanlariguncelle";
	}
	
	
	@ModelAttribute("alanEtkisi")	
	public UpdateFieldEffectsRequest alanEtkisi() {
		return new UpdateFieldEffectsRequest();
	}
	@PostMapping("/alanetkileriguncelle")
	public String updateFieldEffects(@ModelAttribute("alanEtkisi") UpdateFieldEffectsRequest alanEtkisi, RedirectAttributes redirectAttributes) {
		try {
				scoreService.updateFieldEffects(alanEtkisi);
		}catch (Exception e) {
			System.out.print(e.getLocalizedMessage());
			return "redirect:alanlariguncelle?error";
		}
		return "redirect:/alanlariguncelle";
	}
}

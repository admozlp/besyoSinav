package com.ahievran.besyoSinav.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahievran.besyoSinav.business.abstracts.StudentListService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class FemaleListController {
	private StudentListService studentListService;
	String selectedOption = "Hepsi";
	List<String> options = new ArrayList<>();
	
	public FemaleListController(StudentListService studentListService) {
		this.studentListService = studentListService;
		
		options.add("Hepsi");
		options.add("Milli Değil");
		options.add("Milli");	
	}
	
	@GetMapping("/bayanlistesi")
	public String showFemaleList(Model model,  @RequestParam(defaultValue = "0") int page, @RequestParam( value="selectedOption", required = false) String selectedOptionFromForm) {
		
		Pageable pageable = PageRequest.of(page, 15, Sort.by("sinav.puan").descending());
		
		if(selectedOptionFromForm != null) {
			selectedOption = selectedOptionFromForm;
		}
		Page<Ogrenci> ogrenciler = studentListService.findByCinsiyetAndMilliSporcu("Bayan", selectedOption, pageable);
		model.addAttribute("ogrenciler", ogrenciler);
		
		long totalBooks =studentListService.countByCinsiyetAndMilliSporcu("Bayan", selectedOption);
		int totalPages = (int) Math.ceil((double) totalBooks / 15);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("options", options);
		model.addAttribute("selectedOption", selectedOption);
		
		return "bayanlistesi";
	}
}

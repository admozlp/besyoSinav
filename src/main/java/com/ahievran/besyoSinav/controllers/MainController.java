package com.ahievran.besyoSinav.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahievran.besyoSinav.business.abstracts.StatisticsService;

@Controller
public class MainController {	
	private StatisticsService statisticsService;
	List<Integer> options = new ArrayList<>();;
	Calendar calendar =Calendar.getInstance();
	int selectedOption = calendar.get(Calendar.YEAR);
	List<String> formatlar = new ArrayList<>();
	String secilenFormat = "Grafik";

	@Autowired	
	public MainController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
		for(int i = 2019; i<=selectedOption; i++) {
			options.add(i);
		}
		
		formatlar.add("Grafik");
		formatlar.add("Tablo");
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(@RequestParam(value = "selectedOption", required=false) String selectedFromForm,
			@RequestParam(value = "secilenformat", required = false) String format,
			Model model) {

		List<Integer> istatistics = new ArrayList<>();
		if(selectedFromForm != null) {
			selectedOption = Integer.parseInt(selectedFromForm);
		}
		if(format != null) {
			secilenFormat = format;
		}
		
		//genel durum
		istatistics.add(statisticsService.findByBasBolumAndBasvuruYil("Beden Eğitimi ve Spor Eğitimi",  selectedOption));
		istatistics.add(statisticsService.findByBasBolumAndBasvuruYil("Antrenörlük Eğitimi", selectedOption));
		istatistics.add(statisticsService.findByBasBolumAndBasvuruYil("Spor Yöneticiliği", selectedOption));
		
		//genel erkek
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Beden Eğitimi ve Spor Eğitimi", 1, selectedOption)) ;
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Beden Eğitimi ve Spor Eğitimi", 0, selectedOption)) ;
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Antrenörlük Eğitimi", 1, selectedOption)) ;
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Antrenörlük Eğitimi", 0, selectedOption)) ;
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Spor Yöneticiliği", 1, selectedOption)) ;
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Erkek", "Spor Yöneticiliği", 0, selectedOption)) ;
		
		// genel bayan
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Beden Eğitimi ve Spor Eğitimi", 1, selectedOption));
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Beden Eğitimi ve Spor Eğitimi", 0, selectedOption));
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Antrenörlük Eğitimi", 1, selectedOption));
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Antrenörlük Eğitimi", 0, selectedOption));
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Spor Yöneticiliği", 1, selectedOption));
		istatistics.add(statisticsService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil("Bayan", "Spor Yöneticiliği", 0, selectedOption));
		
		// milli olmayan erkek
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girmedi", selectedOption));	
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Antrenörlük Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Antrenörlük Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Antrenörlük Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Spor Yöneticiliği", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Spor Yöneticiliği", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Erkek", "Spor Yöneticiliği", "Sınava Girmedi", selectedOption));
		
		// milli olmayan bayan
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Antrenörlük Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Antrenörlük Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Antrenörlük Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Spor Yöneticiliği", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Spor Yöneticiliği", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(0, "Bayan", "Spor Yöneticiliği", "Sınava Girmedi", selectedOption));
		
		// milli  erkek
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girmedi", selectedOption));	
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Antrenörlük Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Antrenörlük Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Antrenörlük Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Spor Yöneticiliği", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Spor Yöneticiliği", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Erkek", "Spor Yöneticiliği", "Sınava Girmedi", selectedOption));
	
		// milli  bayan
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Beden Eğitimi ve Spor Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Antrenörlük Eğitimi", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Antrenörlük Eğitimi", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Antrenörlük Eğitimi", "Sınava Girmedi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Spor Yöneticiliği", "Sınava Girdi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Spor Yöneticiliği", "Diskalifiye Edildi", selectedOption));
		istatistics.add(statisticsService.findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(1, "Bayan", "Spor Yöneticiliği", "Sınava Girmedi", selectedOption));
		
		
		
		
        model.addAttribute("istatistics", istatistics);     
		model.addAttribute("options", options);
		model.addAttribute("selectedOption", selectedOption);
		
		model.addAttribute("secilenformat", secilenFormat);
		model.addAttribute("formatlar", formatlar);
		
		return "home";
	}
}
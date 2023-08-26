package com.ahievran.besyoSinav.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.ResultService;
import com.ahievran.besyoSinav.business.abstracts.StudentService;
import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class ResultController {
	String secilenMillilik;
	List<String> millikler = new ArrayList<>();
	String secilenCinsiyet;
	List<String> cinsiyetler = new ArrayList<>();
	String secilenBolum;
	List<String> bolumler = new ArrayList<>();	
	private final int pageSize = 15;
	private ResultService resultService;
	@Autowired
	public ResultController(ResultService resultService, StudentService studentService) {
		this.resultService = resultService;
		
		secilenMillilik = "Milli Değil";		
		millikler = new ArrayList<>();
		millikler.add("Milli Değil");
		millikler.add("Milli");
				
		secilenCinsiyet = "Hepsi";		
		cinsiyetler = new ArrayList<>();
		cinsiyetler.add("Hepsi");
		cinsiyetler.add("Erkek");
		cinsiyetler.add("Bayan");
		
		secilenBolum = "Antrenörlük Eğitimi";
		bolumler = new ArrayList<>();
		bolumler.add("Beden Eğitimi ve Spor Eğitimi");
		bolumler.add("Antrenörlük Eğitimi");
		bolumler.add("Spor Yöneticiliği");
	}
  
	
	
	@GetMapping("/sonuclar")
	public String showComputation(@RequestParam(value = "milli", required=false) String milli, @RequestParam(value = "cinsiyet", required=false) String cinsiyet,

			@RequestParam(value = "bolum", required=false) String bolum,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(value = "excel", required=false) String excel, 
			HttpServletResponse response, Model model)  throws IOException{
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("yp").descending());

		if (milli != null) {
				secilenMillilik = milli;
		}						
		if(cinsiyet != null) {
			secilenCinsiyet = cinsiyet;
		}				
		if(bolum != null) {
			secilenBolum = bolum;
		}
		
		if(secilenCinsiyet.equals("Hepsi")) {
			List<Integer> statistics = resultService.countByBasBolumAndMilliSporcuAndBasvuruYil(secilenBolum, secilenMillilik);
			statistics.add(resultService.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(secilenBolum, secilenMillilik, "Sınava Girdi"));		
			Page<Ogrenci> ogrenciler = resultService.findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(secilenBolum, secilenMillilik, pageable, "Sınava Girdi");				
			long totalBooks = resultService.findCountBasBolumAndMilliSporcuAndBasvuruYil(secilenBolum, secilenMillilik, "Sıanava Girdi");
			int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
			
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			
			model.addAttribute("statistics",statistics);
			model.addAttribute("ogrenciler", ogrenciler);
		}else {
			List<Integer> statistics = resultService.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(secilenCinsiyet, secilenBolum, secilenMillilik);
			statistics.add(resultService.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(secilenCinsiyet, secilenBolum, secilenMillilik, "Sınava Girdi"));		
			Page<Ogrenci> ogrenciler = resultService.findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(secilenCinsiyet, secilenBolum, secilenMillilik, pageable, "Sınava Girdi");				
			long totalBooks = resultService.findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(secilenCinsiyet, secilenBolum, secilenMillilik, "Sıanava Girdi");
			int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
			
			model.addAttribute("currentPage", page);
			model.addAttribute("totalPages", totalPages);
			
			model.addAttribute("statistics",statistics);
			model.addAttribute("ogrenciler", ogrenciler);
		}
		
		AlanEtkisi alanEtki = resultService.alanEtki();
		model.addAttribute("alanEtki", alanEtki);
		
		
		
		model.addAttribute("millilikler", millikler);
		model.addAttribute("secilenmillilik", secilenMillilik);
		
		model.addAttribute("cinsiyetler", cinsiyetler);
		model.addAttribute("secilencinsiyet", secilenCinsiyet);
		
		model.addAttribute("bolumler", bolumler);
		model.addAttribute("secilenbolum", secilenBolum);
	
		return "sonuclar";
	}
	
	@GetMapping("/excelsonuclar")
	public void excelSonuclar(HttpServletResponse response, RedirectAttributes redirectAttributes, @RequestParam(value="zamandamgasi", required = false) boolean zamandamgasi) throws IOException {
			List<Ogrenci> excelList;
			if(secilenCinsiyet.equals("Hepsi")) {
				excelList = resultService.findByBasBolumAndMilliSporcuAndBasvuruYil(secilenBolum, secilenMillilik);
			}else {
				excelList = resultService.findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(secilenCinsiyet, secilenBolum, secilenMillilik);
			}
			
			Collections.sort(excelList, Comparator.comparingDouble(Ogrenci::getYp).reversed());
			
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH.mm");
            String timestamp = sdf.format(new Date());
            String fileName = "Sonuçlar_" + timestamp + ".xlsx";
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		     try (ServletOutputStream outputStream = response.getOutputStream()){
		            Workbook workbook = resultService.createExcel(excelList);			     
		            workbook.write(outputStream);		            
		            outputStream.flush();
		            outputStream.close();
		        } catch (Exception e) {
		            System.out.println("Controller : " + e.getLocalizedMessage());
		        }
		     
		     if(zamandamgasi) {
		    	 System.out.println("zaman damgası vurulacak");
		     }else {
		    	 System.out.println("zaman damgası vurulmayacak");
		     }
		     
		     
		     
		     
		     
		     
		     
		     
	}
	
}
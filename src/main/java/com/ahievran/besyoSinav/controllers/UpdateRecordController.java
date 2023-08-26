package com.ahievran.besyoSinav.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.StudentService;
import com.ahievran.besyoSinav.business.abstracts.UpdateRecordService;
import com.ahievran.besyoSinav.business.requests.UpdateStudentRequest;
import com.ahievran.besyoSinav.entities.Ogrenci;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UpdateRecordController {
	private StudentService studentService;
	private UpdateRecordService updateRecordService;
	
	
	@GetMapping("/kayitlariguncelle")
	public String showUpdateRecord(@RequestParam(value = "selectedOption", required=false) String selectedFromForm,  @RequestParam(value="aday", required=false) String aday ,Model model) {
		List<Integer> options = new ArrayList<>();
		int selectedOption = Calendar.getInstance().get(Calendar.YEAR);

		for(int i = 2019; i<=selectedOption; i++) {
			options.add(i);
		}
		if(selectedFromForm != null) {
			selectedOption = Integer.parseInt(selectedFromForm);
		}
		List<Integer> istatistics = new ArrayList<>();

		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Erkek", "Sınava Girdi", selectedOption));
		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Erkek", "Sınava Girmedi", selectedOption));
		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Erkek", "Diskalifiye Edildi", selectedOption));
		
		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Bayan", "Sınava Girdi", selectedOption));
		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Bayan", "Sınava Girmedi", selectedOption));
		istatistics.add(updateRecordService.countByCinsiyetAndDurumDurumuAndBasvuruYil("Bayan", "Diskalifiye Edildi", selectedOption));

		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Beden Eğitimi ve Spor Eğitimi", "Erkek", selectedOption));
		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Spor Yöneticiliği", "Erkek", selectedOption));
		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Antrenörlük Eğitimi", "Erkek", selectedOption));
		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Beden Eğitimi ve Spor Eğitimi", "Bayan", selectedOption));
		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Spor Yöneticiliği", "Bayan", selectedOption));
		istatistics.add(updateRecordService.countByBasBolumnAndCinsiyetAndBasvuruYil("Antrenörlük Eğitimi", "Bayan", selectedOption));
		
		istatistics.add(updateRecordService.countByCinsiyetAndBasvuruYil("Erkek", selectedOption));
		istatistics.add(updateRecordService.countByCinsiyetAndBasvuruYil("Bayan", selectedOption));
		
		model.addAttribute("options", options);
		model.addAttribute("selectedOption", selectedOption);
        model.addAttribute("istatistics", istatistics); 
		
		if(aday != null) {
			List<Ogrenci> ogrenciler = studentService.findtByTcno(aday, selectedOption);
			if(ogrenciler.size() > 0) {
				Ogrenci ogrenci = ogrenciler.get(0);
				String secilenDurum = ogrenci.getDurum().getDurumu();	
				List<String> durumlar = Arrays.asList("Sınava Girdi", "Sınava Girmedi", "Diskalifiye Edildi");
				model.addAttribute("durumlar", durumlar);
				model.addAttribute("secilenDurum", secilenDurum);
				model.addAttribute("secilenOgrenci", ogrenci);
			}
			else {
				model.addAttribute("ogrenci", null);
				return 	"redirect:kayitlariguncelle?mevcutdegil";
			}
		}		
		return "kayitlariguncelle";
	}
	
	
	@ModelAttribute("secilenOgrenci")
	public UpdateStudentRequest updateStudentRequest() {
		return new UpdateStudentRequest();
	}
	
	@PostMapping("/sinavguncelle")
	public String  update(@ModelAttribute("secilenOgrenci") UpdateStudentRequest secilenOGrenci,@RequestParam(value = "secilenDurum", required=false) String secilenDurum, RedirectAttributes redirectAttributes) {
			try {
				updateRecordService.updateRecord(secilenOGrenci, secilenDurum);
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				return "redirect:kayitlariguncelle?guncellemehatasi";
			}
		return "redirect:/kayitlariguncelle";
	}
	
	
	@GetMapping("/tumkayitlariyedekle")
	public void backUpAllRecords(RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
		List<Ogrenci> tumKayitlar = studentService.findAll();
		
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Tüm Kayıtlar.xlsx");

	     try (ServletOutputStream outputStream = response.getOutputStream()){
	            Workbook workbook = updateRecordService.createExcel(tumKayitlar);			     
	            workbook.write(outputStream);		            
	            outputStream.flush();
	            outputStream.close();
	        } catch (Exception e) {
	            System.out.println("Controller : " + e.getLocalizedMessage());
	        }
		
	}
	
	@GetMapping("/alanlariyedekle")
	public void backUpFields(HttpServletResponse response) throws IOException{
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Alanlar.xlsx");

	     try (ServletOutputStream outputStream = response.getOutputStream()){
	            Workbook workbook = updateRecordService.createExcelFields();			     
	            workbook.write(outputStream);		            
	            outputStream.flush();
	            outputStream.close();
	        } catch (Exception e) {
	            System.out.println("Controller : " + e.getLocalizedMessage());
	        }
	}
	
	

}

package com.ahievran.besyoSinav.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.StudentService;
import com.ahievran.besyoSinav.entities.Ogrenci;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/veritabaninakaydet")
@AllArgsConstructor
public class SaveDataController {
	@Autowired
	private StudentService studentService;

	
	@GetMapping()
	public String saveExcelData(RedirectAttributes redirectAttributes) {
		try {
			List<Ogrenci> excelDataAsList = this.studentService.getExcelDataAsList();
			int noOfRecords = this.studentService.saveExcelData(excelDataAsList);
			
			System.out.println("Kaydedilen Veri :" + noOfRecords);

			return "redirect:excelyukle?kayitedildi";
		}catch(Exception e) {
			return "redirect:excelyukle?kayitbasarisiz";
		}
	}
}

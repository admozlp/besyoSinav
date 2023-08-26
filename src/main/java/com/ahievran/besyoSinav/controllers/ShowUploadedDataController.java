package com.ahievran.besyoSinav.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahievran.besyoSinav.business.abstracts.StudentService;
import com.ahievran.besyoSinav.entities.Ogrenci;

@Controller
public class ShowUploadedDataController {
	@Autowired
	private StudentService studentService;

	private final int pageSize = 30;
	List<Integer> options = new ArrayList<>();;
	Calendar calendar =Calendar.getInstance();
	int selectedOption = calendar.get(Calendar.YEAR);
	
	public ShowUploadedDataController(StudentService studentService) {
		this.studentService = studentService;
		for(int i = 2019; i<=selectedOption; i++) {
			options.add(i);
		}
	}

	@GetMapping("/yuklenenverigoster")
	public String showUploadedData(@RequestParam(value = "selectedOption", required=false) String selectedFromForm,   Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(value = "aday", required = false) String aday) {
		
		
		if(selectedFromForm != null) {
			selectedOption = Integer.parseInt(selectedFromForm);
		}
		
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
		Page<Ogrenci> allStudents = this.studentService.getAllByYear(selectedOption, pageable);

		
		if (aday != null) {
			aday = aday.trim();
			allStudents = studentService.getByTcNo(aday, selectedOption, pageable);

			if (!allStudents.hasContent()) {
				allStudents = studentService.getByName(aday.toUpperCase(), selectedOption, pageable);
				if (!allStudents.hasContent()) {
					allStudents = studentService.findyByTel(aday.replaceAll("\\s+", ""), selectedOption, pageable);
					if (!allStudents.hasContent()) {
						try {
							allStudents = studentService.findBySiraNo(Integer.parseInt(aday), selectedOption, pageable);
						} catch (Exception e) {
							return "redirect:yuklenenverigoster?mevcutdegil";
						}
						if (!allStudents.hasContent()) {
							try {
								allStudents = studentService.getById(Integer.parseInt(aday), selectedOption, pageable);
							} catch (Exception e) {
								return "redirect:yuklenenverigoster?mevcutdegil";
							}
						}
					}
				}
			}
		}	

	
		long totalBooks = studentService.getCount();
		int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

		model.addAttribute("allStudents", allStudents);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("options", options);
		model.addAttribute("selectedOption", selectedOption);

		return "yuklenenverigoster";
	}
	
	
}

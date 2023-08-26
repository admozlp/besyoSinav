package com.ahievran.besyoSinav.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahievran.besyoSinav.business.abstracts.FileUploaderService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/excelyukle")
@AllArgsConstructor
public class UploadExcelController {
	@Autowired
	private FileUploaderService fileUploaderService;	
	
	@GetMapping()
	public String showUploadExcel() {
		return "excelyukle";
	}
	
	@PostMapping
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			if(!file.isEmpty()) {
				fileUploaderService.uploadFile(file);
			}else {
				return "redirect:excelyukle?dosyasecilmedi";
			}
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return "redirect:excelyukle?error";
		}
			
        try {
			Thread.sleep(3000);
			return "redirect:excelyukle?success";
		} catch (InterruptedException e) {
			System.out.println(e);
			return "redirect:excelyukle?error";
		}	
	}
}
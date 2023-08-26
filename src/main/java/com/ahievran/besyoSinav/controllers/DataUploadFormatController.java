package com.ahievran.besyoSinav.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahievran.besyoSinav.business.abstracts.DownloadExampleExcelService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/veriyuklemeformati")
@AllArgsConstructor
public class DataUploadFormatController {
	private DownloadExampleExcelService downloadExampleExcelService;
	
	@GetMapping()
	public String showDataUploadFormat(){		
		return "veriyuklemeformati";
	}
	
	@GetMapping("/excelindir")
	public void  downloadExce(HttpServletResponse response) throws IOException{
		try {
	        InputStream inputStream = downloadExampleExcelService.getInputStream();
			
	        response.setContentType("text/csv");
	        String originalFileName = "Örnek Excel Tablosu.xlsx";
	        
	        response.setHeader("Content-Disposition", "attachment; filename=" + originalFileName);
	        try (OutputStream outputStream = response.getOutputStream()) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            outputStream.close();
	        }
	        
		}catch (Exception e) {
			System.out.println("İndirilirken : " + e);
		}
	}
	
	
	
}

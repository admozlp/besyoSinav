package com.ahievran.besyoSinav.business.concretes;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.DownloadExampleExcelService;


@Service
public class DownloadExampleExcelManager implements DownloadExampleExcelService{
	public String EXCEL_FILE_PATH;
	@Override
	public InputStream getInputStream() throws IOException {
		EXCEL_FILE_PATH = "ornekExcelTablo/OrnekExcelTablosu.csv";
        Resource resource = new ClassPathResource(EXCEL_FILE_PATH);
        InputStream inputStream = resource.getInputStream();
		return inputStream;
	}

}

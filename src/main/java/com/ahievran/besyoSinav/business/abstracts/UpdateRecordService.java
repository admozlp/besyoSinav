package com.ahievran.besyoSinav.business.abstracts;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.ahievran.besyoSinav.business.requests.UpdateStudentRequest;
import com.ahievran.besyoSinav.entities.Ogrenci;

public interface UpdateRecordService {
	int countByCinsiyetAndDurumDurumuAndBasvuruYil(String cinsiyet, String durum, int basvuruYil);
	int countByBasBolumnAndCinsiyetAndBasvuruYil(String basBolum,  String cinsiyet, int basvuruYil);
	 int countByCinsiyetAndBasvuruYil(String cinsiyet, int basvuruYil);
	 void updateRecord(UpdateStudentRequest updateStudentRequest, String secilenDurum);
	 
	Workbook createExcel(List<Ogrenci> excelList);
	Workbook createExcelFields();
}

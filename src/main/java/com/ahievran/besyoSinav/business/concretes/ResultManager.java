package com.ahievran.besyoSinav.business.concretes;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.ResultService;
import com.ahievran.besyoSinav.business.abstracts.ScoreService;
import com.ahievran.besyoSinav.core.utilities.ExcelCellStyle;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.Ogrenci;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResultManager  implements ResultService{
	@Autowired
	private StudentRepository studentRepository;
	private ScoreService scoreService;
	private ExcelCellStyle excelCellStyle;
	
	@Override
	public Page<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(String cinsiyet, String basBolum, String milliSporcu, Pageable pageable, String durumu) {
		int millilik = 0; 
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		return studentRepository.findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), pageable,durumu);
	}

	@Override
	public List<Integer> countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, String milliSporcu) {
		List<Integer> statistics = new ArrayList<>();
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		//int sinavaGirdi
		statistics.add(studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi"));
		//int sinavaGirmedi 
		 statistics.add( studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girmedi"));
		//int diskalifiyeEdildi
		statistics.add(studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Diskalifiye Edildi"));
		//int toplamKayit 
		statistics.add(studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR)));
		
		
		return statistics;
	}

	@Override
	public int findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum,
			String milliSporcu, String durumu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		return studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), durumu);
	}

	@Override
	public List<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum,
			String milliSporcu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}		
		return studentRepository.findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi");
	}

	@Override
	public int countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, String basBolum,
			String milliSporcu, String durumu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		
		return studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), durumu);
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Page<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(String basBolum, String milliSporcu,
			Pageable pageable, String durumu) {
		int millilik = 0; 
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		return studentRepository.findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), pageable,durumu);
	}

	@Override
	public List<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}		
		return studentRepository.findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi");
	}

	@Override
	public List<Integer> countByBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu) {
		List<Integer> statistics = new ArrayList<>();
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		//int sinavaGirdi
		statistics.add(studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi"));
		//int sinavaGirmedi 
		 statistics.add( studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girmedi"));
		//int diskalifiyeEdildi
		statistics.add(studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), "Diskalifiye Edildi"));
		//int toplamKayit 
		statistics.add(studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYil(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR)));
		
		
		return statistics;
	}

	@Override
	public int findCountBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu, String durumu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		return studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), durumu);
	}

	@Override
	public int countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String basBolum, String milliSporcu,
			String durumu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}
		
		return studentRepository.countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(basBolum, millilik, Calendar.getInstance().get(Calendar.YEAR), durumu);
	}

	@Override
	public Workbook createExcel(List<Ogrenci> excelList) {
		AlanEtkisi alanEtkisi = scoreService.getFieldEffects().get(0);
		double obpYuzde = alanEtkisi.getAobp(); 
		double tytYuzde = alanEtkisi.getTyt();
		double osypYuzde = alanEtkisi.getOysp();
		double osypTamKatSayi = alanEtkisi.getOyspTamKatSayi();
		double aobpEkstraKatSayi = alanEtkisi.getAobpEkstraKatSayi();
		try {
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Sayfa 1");

	      
	        Row headerRow = sheet.createRow(0);
	        CellStyle headerStyle = excelCellStyle.createHeaderCellStyle(workbook);
	        CellStyle headerStyle2 = excelCellStyle.createHeaderCellStyle2(workbook);

	        
	        Cell headerCell1 =  headerRow.createCell(0);
	        headerCell1.setCellValue("TC");
	        headerCell1.setCellStyle(headerStyle2);
	        
	        Cell headerCell2 =  headerRow.createCell(1);
	        headerCell2.setCellValue("AD SOYAD");
	        headerCell2.setCellStyle(headerStyle);
	        
	        Cell headerCell3 =  headerRow.createCell(2);
	        headerCell3.setCellValue("MİLLİ SPORCU");
	        headerCell3.setCellStyle(headerStyle2);
	        Cell headerCell4 =  headerRow.createCell(3);
	        headerCell4.setCellValue("BAŞVURULAN BÖLÜM");
	        headerCell4.setCellStyle(headerStyle);
	        Cell headerCell5 =  headerRow.createCell(4);
	        headerCell5.setCellValue("LİSE ALAN");
	        headerCell5.setCellStyle(headerStyle2);
	        Cell headerCell6 =  headerRow.createCell(5);
	        headerCell6.setCellValue("TYT PUANI");
	        headerCell6.setCellStyle(headerStyle);
	        Cell headerCell7 =  headerRow.createCell(6);
	        headerCell7.setCellValue("AOBP");
	        headerCell7.setCellStyle(headerStyle2);
	        Cell headerCell8 =  headerRow.createCell(7);
	        headerCell8.setCellValue("ÖYSP");
	        headerCell8.setCellStyle(headerStyle);
	        Cell headerCell9 =  headerRow.createCell(8);
	        headerCell9.setCellValue("ÖNCEKİ SENE YERLEŞME DURUMU");
	        headerCell9.setCellStyle(headerStyle2);
	        Cell headerCell10 =  headerRow.createCell(9);
	        headerCell10.setCellValue("ÖYSP %");
	        headerCell10.setCellStyle(headerStyle);
	        Cell headerCell11 =  headerRow.createCell(10);
	        headerCell11.setCellValue("TYT %");
	        headerCell11.setCellStyle(headerStyle2);
	        Cell headerCell12 =  headerRow.createCell(11);
	        headerCell12.setCellValue("AOBP %");
	        headerCell12.setCellStyle(headerStyle);

	        Cell headerCell13 =  headerRow.createCell(12);
	        headerCell13.setCellValue("PARKUR ZAMANI");
	        headerCell13.setCellStyle(headerStyle2);
	        
	        Cell headerCell14 =  headerRow.createCell(13);
	        headerCell14.setCellValue("YERLEŞTİRME PUANI");
	        headerCell14.setCellStyle(headerStyle);
	        	      

	        
	        int rowNum = 1;
	        for (Ogrenci ogrenci: excelList) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(ogrenci.getTcNo());
	            row.createCell(1).setCellValue(ogrenci.getAdSoyad());
	            row.createCell(2).setCellValue(ogrenci.getMilliSporcu());
	            row.createCell(3).setCellValue(ogrenci.getBasBolum());
	            row.createCell(4).setCellValue(ogrenci.getLiseAlan());
	            row.createCell(5).setCellValue(ogrenci.getYksPuani());
	            row.createCell(6).setCellValue(ogrenci.getAobpPuani());
	            row.createCell(7).setCellValue(ogrenci.getSinav().getPuan());
	            row.createCell(8).setCellValue(ogrenci.getOncekiSeneYerlesme());
	            row.createCell(9).setCellValue(ogrenci.getSinav().getPuan() * osypYuzde * osypTamKatSayi);
	            row.createCell(10).setCellValue(ogrenci.getYksPuani() * tytYuzde);
	            if(ogrenci.getLiseAlan() == 0 && ogrenci.getOncekiSeneYerlesme() == 1 ) {
	            	row.createCell(11).setCellValue((ogrenci.getAobpPuani() * obpYuzde) / 2);
	            }else if(ogrenci.getLiseAlan() == 0 && ogrenci.getOncekiSeneYerlesme() == 0) {
	            	row.createCell(11).setCellValue(ogrenci.getAobpPuani() * obpYuzde);
	            }else if(ogrenci.getLiseAlan() == 1 && ogrenci.getOncekiSeneYerlesme() == 1) {
	            	row.createCell(11).setCellValue((ogrenci.getAobpPuani() * obpYuzde) / 2 + (ogrenci.getAobpPuani() * aobpEkstraKatSayi) );
	            }else if(ogrenci.getLiseAlan() == 1 && ogrenci.getOncekiSeneYerlesme() == 0) {
	            	row.createCell(11).setCellValue(ogrenci.getAobpPuani() * obpYuzde + ogrenci.getAobpPuani() * aobpEkstraKatSayi);
	            }
	            row.createCell(12).setCellValue(ogrenci.getSinav().getZaman().toString());
	            row.createCell(13).setCellValue(ogrenci.getYp());
	        }
	        
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        sheet.autoSizeColumn(7);
	        sheet.autoSizeColumn(8);
	        sheet.autoSizeColumn(9);
	        sheet.autoSizeColumn(10);
	        sheet.autoSizeColumn(11);
	        sheet.autoSizeColumn(12);
	        sheet.autoSizeColumn(13);
	        
	        return workbook;
		}catch (Exception e) {
			System.out.println("Manager : " + e.getLocalizedMessage());
			return null;
		}
	}
	

	@Override
	public AlanEtkisi alanEtki() {
		AlanEtkisi alanEtkisi = scoreService.getFieldEffects().get(0);
		return alanEtkisi;
	}

}

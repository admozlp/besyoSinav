package com.ahievran.besyoSinav.business.concretes;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.UpdateRecordService;
import com.ahievran.besyoSinav.business.requests.UpdateStudentRequest;
import com.ahievran.besyoSinav.core.utilities.ExcelCellStyle;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.Ogrenci;
import com.ahievran.besyoSinav.entities.Sinav;

import lombok.AllArgsConstructor;
	
@Service
@AllArgsConstructor
public class UpdateRecordManager implements UpdateRecordService {
	private StudentRepository studentRepository;
	private ExcelCellStyle excelCellStyle;

	@Override
	public int countByCinsiyetAndDurumDurumuAndBasvuruYil(String cinsiyet, String durum, int basvuruYil) {
		return studentRepository.countByCinsiyetAndDurumDurumuAndBasvuruYil(cinsiyet, durum, basvuruYil);
	}

	@Override
	public int countByBasBolumnAndCinsiyetAndBasvuruYil(String basBolum, String cinsiyet, int basvuruYil) {
		return studentRepository.countByBasBolumAndCinsiyetAndBasvuruYil(basBolum, cinsiyet, basvuruYil);
	}

	@Override
	public int countByCinsiyetAndBasvuruYil(String cinsiyet, int basvuruYil) {
		return studentRepository.countByCinsiyetAndBasvuruYil(cinsiyet, basvuruYil);
	}

	@Override
	public void updateRecord(UpdateStudentRequest updateStudentRequest, String secilenDurum) {
		Ogrenci ogrenci = studentRepository.findByTcNo(updateStudentRequest.getTcNo());
		if(ogrenci != null) {
			ogrenci.setBasBolum(updateStudentRequest.getBasBolum());
			Sinav dbSinav = ogrenci.getSinav();
			Sinav sinav = updateStudentRequest.getSinav();
			dbSinav.setSure(sinav.getSure());
			dbSinav.setHataPuani(sinav.getHataPuani());
			dbSinav.setHamPuani(sinav.getHamPuani());
			dbSinav.setPuan(sinav.getPuan());
			ogrenci.setSinav(dbSinav);
			ogrenci.setOzgecmisPuani(updateStudentRequest.getOzgecmisPuani());
			ogrenci.getDurum().setDurumu(secilenDurum);
			studentRepository.save(ogrenci);
		}
	}

	
	@Override
	public Workbook createExcel(List<Ogrenci> excelList) {
		Workbook workbook = new XSSFWorkbook();

		try {					
        Sheet sheet = workbook.createSheet("Sayfa 1");
       
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = excelCellStyle.createHeaderCellStyle(workbook);
        CellStyle headerStyle2 = excelCellStyle.createHeaderCellStyle2(workbook);
		
        
        Cell headerCell1 =  headerRow.createCell(0);
        headerCell1.setCellValue("ID");
        headerCell1.setCellStyle(headerStyle2);        
        Cell headerCell2 =  headerRow.createCell(1);
        headerCell2.setCellValue("TC");
        headerCell2.setCellStyle(headerStyle);
        
        Cell headerCell3 =  headerRow.createCell(2);
        headerCell3.setCellValue("AD SOYAD");
        headerCell3.setCellStyle(headerStyle2);        


        
        Cell headerCell5 =  headerRow.createCell(3);
        headerCell5.setCellValue("CİNSİYET");
        headerCell5.setCellStyle(headerStyle);
        Cell headerCell6 =  headerRow.createCell(4);
        headerCell6.setCellValue("DOĞUM TARİHİ");
        headerCell6.setCellStyle(headerStyle2);
        Cell headerCell7 =  headerRow.createCell(5);
        headerCell7.setCellValue("DOĞUM YERİ");
        headerCell7.setCellStyle(headerStyle);
        Cell headerCell8 =  headerRow.createCell(6);
        headerCell8.setCellValue("BAŞVURULAN BÖLÜM");
        headerCell8.setCellStyle(headerStyle2);
        Cell headerCell9 =  headerRow.createCell(7);
        headerCell9.setCellValue("TYT PUANI");
        headerCell9.setCellStyle(headerStyle);
        Cell headerCell10 =  headerRow.createCell(8);
        headerCell10.setCellValue("AOBP");
        headerCell10.setCellStyle(headerStyle2);
        Cell headerCell11 =  headerRow.createCell(9);
        headerCell11.setCellValue("MEZUN LİSE");
        headerCell11.setCellStyle(headerStyle);
        Cell headerCell12 =  headerRow.createCell(10);
        headerCell12.setCellValue("MEZUN LİSE TÜRÜ");
        headerCell12.setCellStyle(headerStyle2);

        Cell headerCell13 =  headerRow.createCell(11);
        headerCell13.setCellValue("MEZUN LİSE BÖLÜM");
        headerCell13.setCellStyle(headerStyle);
        
        Cell headerCell14 =  headerRow.createCell(12);
        headerCell14.setCellValue("LİSE ALAN");
        headerCell14.setCellStyle(headerStyle2);
        
        Cell headerCell15 =  headerRow.createCell(13);
        headerCell15.setCellValue("ÖNCEKİ SENE YERLEŞME");
        headerCell15.setCellStyle(headerStyle);
        Cell headerCell16 = headerRow.createCell(14);
        headerCell16.setCellValue("MİLLİ SPORCU");
        headerCell16.setCellStyle(headerStyle2);

        Cell headerCell17 = headerRow.createCell(15);
        headerCell17.setCellValue("ÖZ GEÇMİŞ PUANI");
        headerCell17.setCellStyle(headerStyle);
        
        Cell headerCell21 = headerRow.createCell(16);
        headerCell21.setCellValue("RESİM");
        headerCell21.setCellStyle(headerStyle2);

        Cell headerCell18 = headerRow.createCell(17);
        headerCell18.setCellValue("TELEFON");
        headerCell18.setCellStyle(headerStyle);

        
        Cell headerCell20 = headerRow.createCell(18);
        headerCell20.setCellValue("KAYIT TARİHİ");
        headerCell20.setCellStyle(headerStyle2);
        
        
        Cell headerCell19 = headerRow.createCell(19);
        headerCell19.setCellValue("SIRA NO");
        headerCell19.setCellStyle(headerStyle);
        




        Cell headerCell22 = headerRow.createCell(20);
        headerCell22.setCellValue("SÜRE");
        headerCell22.setCellStyle(headerStyle2);

        Cell headerCell23 = headerRow.createCell(21);
        headerCell23.setCellValue("HATA PUANI");
        headerCell23.setCellStyle(headerStyle);


        Cell headerCell25 = headerRow.createCell(22);
        headerCell25.setCellValue("HAM PUAN");
        headerCell25.setCellStyle(headerStyle2);

        Cell headerCell26 = headerRow.createCell(23);
        headerCell26.setCellValue("PUAN");
        headerCell26.setCellStyle(headerStyle);

        Cell headerCell27 = headerRow.createCell(24);
        headerCell27.setCellValue("PARKUR ZAMANI");
        headerCell27.setCellStyle(headerStyle2);

        Cell headerCell28 = headerRow.createCell(25);
        headerCell28.setCellValue("DURUM");
        headerCell28.setCellStyle(headerStyle);

        Cell headerCell29 = headerRow.createCell(26);
        headerCell29.setCellValue("SINAV TARİH");
        headerCell29.setCellStyle(headerStyle2);

        Cell headerCell30 = headerRow.createCell(27);
        headerCell30.setCellValue("SINAV SAAT");
        headerCell30.setCellStyle(headerStyle);
        
        Cell headerCell31 = headerRow.createCell(28);
        headerCell31.setCellValue("IP ADRESİ");
        headerCell31.setCellStyle(headerStyle2);
        
        
        int rowNum = 1;
        for (Ogrenci ogrenci: excelList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(ogrenci.getId());
            row.createCell(1).setCellValue(ogrenci.getTcNo());
            row.createCell(2).setCellValue(ogrenci.getAdSoyad());
            
            row.createCell(3).setCellValue(ogrenci.getCinsiyet());
            
            
            if(ogrenci.getDogumTarihi() != null) {
            	row.createCell(4).setCellValue(ogrenci.getDogumTarihi().toString());
            }
            
            
            row.createCell(5).setCellValue(ogrenci.getDogumYeri());
            row.createCell(6).setCellValue(ogrenci.getBasBolum());
            row.createCell(7).setCellValue(ogrenci.getYksPuani());
            row.createCell(8).setCellValue(ogrenci.getAobpPuani());
            row.createCell(9).setCellValue(ogrenci.getMezunLise());
            row.createCell(10).setCellValue(ogrenci.getMezunLiseTuru());          
            row.createCell(11).setCellValue(ogrenci.getMezunLiseBolum());          
            row.createCell(12).setCellValue(ogrenci.getLiseAlan());
            row.createCell(13).setCellValue(ogrenci.getOncekiSeneYerlesme());
            
            row.createCell(14).setCellValue(ogrenci.getMilliSporcu());
            row.createCell(15).setCellValue(ogrenci.getOzgecmisPuani());
            row.createCell(16).setCellValue(ogrenci.getResim().getYol());
            row.createCell(17).setCellValue(ogrenci.getTel());
            
            if(ogrenci.getKayitTarihi() != null) {
                row.createCell(18).setCellValue(ogrenci.getKayitTarihi().toString());
            }
            
            row.createCell(19).setCellValue(ogrenci.getSiraNo());
            row.createCell(20).setCellValue(ogrenci.getSinav().getSure());
            row.createCell(21).setCellValue(ogrenci.getSinav().getHataPuani());
            row.createCell(22).setCellValue(ogrenci.getSinav().getHamPuani());
            row.createCell(23).setCellValue(ogrenci.getSinav().getPuan());
            
            if(ogrenci.getSinav().getZaman() != null) {
                row.createCell(24).setCellValue(ogrenci.getSinav().getZaman().toString());
            }
            
            row.createCell(25).setCellValue(ogrenci.getDurum().getDurumu());
            row.createCell(26).setCellValue(ogrenci.getRandevu().getTarih().toString());
            row.createCell(27).setCellValue(ogrenci.getRandevu().getSaat().toString());
            row.createCell(28).setCellValue(ogrenci.getSinav().getIpAdresi());          
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
        sheet.autoSizeColumn(14);
        sheet.autoSizeColumn(15);
        sheet.autoSizeColumn(16);
        sheet.autoSizeColumn(17);
        sheet.autoSizeColumn(18);
        sheet.autoSizeColumn(19);
        sheet.autoSizeColumn(20);
        
        sheet.autoSizeColumn(21);
        sheet.autoSizeColumn(22);
        sheet.autoSizeColumn(23);
        sheet.autoSizeColumn(24);
        sheet.autoSizeColumn(25);
        sheet.autoSizeColumn(26);
        sheet.autoSizeColumn(27);
        sheet.autoSizeColumn(28);
        sheet.autoSizeColumn(29);
		}catch (Exception e) {
			e.printStackTrace();
		}
        return workbook;
	}

	@Override
	public Workbook createExcelFields() {
		Workbook workbook = new XSSFWorkbook();

		try {					
        Sheet sheet = workbook.createSheet("Sayfa 1");
       
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = excelCellStyle.createHeaderCellStyle(workbook);
        CellStyle headerStyle2 = excelCellStyle.createHeaderCellStyle2(workbook);
		
        
        Cell headerCell1 =  headerRow.createCell(0);
        headerCell1.setCellValue("ID");
        headerCell1.setCellStyle(headerStyle2);        
        Cell headerCell2 =  headerRow.createCell(1);
        headerCell2.setCellValue("TC");
        headerCell2.setCellStyle(headerStyle);
        
        Cell headerCell3 =  headerRow.createCell(2);
        headerCell3.setCellValue("AD SOYAD");
        headerCell3.setCellStyle(headerStyle2);        


        
        Cell headerCell5 =  headerRow.createCell(3);
        headerCell5.setCellValue("CİNSİYET");
        headerCell5.setCellStyle(headerStyle);
        Cell headerCell6 =  headerRow.createCell(4);
        headerCell6.setCellValue("DOĞUM TARİHİ");
        headerCell6.setCellStyle(headerStyle2);
        Cell headerCell7 =  headerRow.createCell(5);
        headerCell7.setCellValue("DOĞUM YERİ");
        headerCell7.setCellStyle(headerStyle);
        Cell headerCell8 =  headerRow.createCell(6);
        headerCell8.setCellValue("BAŞVURULAN BÖLÜM");
        headerCell8.setCellStyle(headerStyle2);
        Cell headerCell9 =  headerRow.createCell(7);
        headerCell9.setCellValue("TYT PUANI");
        headerCell9.setCellStyle(headerStyle);
        Cell headerCell10 =  headerRow.createCell(8);
        headerCell10.setCellValue("AOBP");
        headerCell10.setCellStyle(headerStyle2);
        Cell headerCell11 =  headerRow.createCell(9);
        headerCell11.setCellValue("MEZUN LİSE");
        headerCell11.setCellStyle(headerStyle);
        Cell headerCell12 =  headerRow.createCell(10);
        headerCell12.setCellValue("MEZUN LİSE TÜRÜ");
        headerCell12.setCellStyle(headerStyle2);

        Cell headerCell13 =  headerRow.createCell(11);
        headerCell13.setCellValue("MEZUN LİSE BÖLÜM");
        headerCell13.setCellStyle(headerStyle);
        
        Cell headerCell14 =  headerRow.createCell(12);
        headerCell14.setCellValue("LİSE ALAN");
        headerCell14.setCellStyle(headerStyle2);
        
        Cell headerCell15 =  headerRow.createCell(13);
        headerCell15.setCellValue("ÖNCEKİ SENE YERLEŞME");
        headerCell15.setCellStyle(headerStyle);
        Cell headerCell16 = headerRow.createCell(14);
        headerCell16.setCellValue("MİLLİ SPORCU");
        headerCell16.setCellStyle(headerStyle2);

        Cell headerCell17 = headerRow.createCell(15);
        headerCell17.setCellValue("ÖZ GEÇMİŞ PUANI");
        headerCell17.setCellStyle(headerStyle);
        
        Cell headerCell21 = headerRow.createCell(16);
        headerCell21.setCellValue("RESİM");
        headerCell21.setCellStyle(headerStyle2);

        Cell headerCell18 = headerRow.createCell(17);
        headerCell18.setCellValue("TELEFON");
        headerCell18.setCellStyle(headerStyle);

        
        Cell headerCell20 = headerRow.createCell(18);
        headerCell20.setCellValue("KAYIT TARİHİ");
        headerCell20.setCellStyle(headerStyle2);
        
        
        Cell headerCell19 = headerRow.createCell(19);
        headerCell19.setCellValue("SIRA NO");
        headerCell19.setCellStyle(headerStyle);
        




        Cell headerCell22 = headerRow.createCell(20);
        headerCell22.setCellValue("SÜRE");
        headerCell22.setCellStyle(headerStyle2);

        Cell headerCell23 = headerRow.createCell(21);
        headerCell23.setCellValue("HATA PUANI");
        headerCell23.setCellStyle(headerStyle);


        Cell headerCell25 = headerRow.createCell(22);
        headerCell25.setCellValue("HAM PUAN");
        headerCell25.setCellStyle(headerStyle2);

        Cell headerCell26 = headerRow.createCell(23);
        headerCell26.setCellValue("PUAN");
        headerCell26.setCellStyle(headerStyle);

        Cell headerCell27 = headerRow.createCell(24);
        headerCell27.setCellValue("PARKUR ZAMANI");
        headerCell27.setCellStyle(headerStyle2);

        Cell headerCell28 = headerRow.createCell(25);
        headerCell28.setCellValue("DURUM");
        headerCell28.setCellStyle(headerStyle);

        Cell headerCell29 = headerRow.createCell(26);
        headerCell29.setCellValue("SINAV TARİH");
        headerCell29.setCellStyle(headerStyle2);

        Cell headerCell30 = headerRow.createCell(27);
        headerCell30.setCellValue("SINAV SAAT");
        headerCell30.setCellStyle(headerStyle);
        
        Cell headerCell31 = headerRow.createCell(28);
        headerCell31.setCellValue("IP ADRESİ");
        headerCell31.setCellStyle(headerStyle2);
        
        
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
        sheet.autoSizeColumn(14);
        sheet.autoSizeColumn(15);
        sheet.autoSizeColumn(16);
        sheet.autoSizeColumn(17);
        sheet.autoSizeColumn(18);
        sheet.autoSizeColumn(19);
        sheet.autoSizeColumn(20);
        
        sheet.autoSizeColumn(21);
        sheet.autoSizeColumn(22);
        sheet.autoSizeColumn(23);
        sheet.autoSizeColumn(24);
        sheet.autoSizeColumn(25);
        sheet.autoSizeColumn(26);
        sheet.autoSizeColumn(27);
        sheet.autoSizeColumn(28);
        sheet.autoSizeColumn(29);
        
		}catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	
	
}

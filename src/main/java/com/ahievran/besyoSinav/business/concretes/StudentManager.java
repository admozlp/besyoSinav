package com.ahievran.besyoSinav.business.concretes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.StudentService;
import com.ahievran.besyoSinav.core.utilities.Columns;
import com.ahievran.besyoSinav.core.utilities.mapper.MapperService;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.Durum;
import com.ahievran.besyoSinav.entities.Ogrenci;
import com.ahievran.besyoSinav.entities.Randevu;
import com.ahievran.besyoSinav.entities.Resim;
import com.ahievran.besyoSinav.entities.Sinav;

@Service
public class StudentManager implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentManager(StudentRepository studentRepository, MapperService mapperService) {
		this.studentRepository = studentRepository;
	}
	
	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	Workbook workbook;
	Year thisyear = Year.now();

	@Override
	public List<Ogrenci> getExcelDataAsList() throws Exception {
		Columns columns = new Columns();
		HashMap<String, Integer> kolon = columns.Map();
		DataFormatter dataFormatter = new DataFormatter();

		try {
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
		} catch (Exception e) {
			throw new Exception(e);
		}

		// sayfa sayısı
		// System.out.println("-------Dosyada '" + workbook.getNumberOfSheets() + "' sayfa var-----");

		// ilk sayfayı al
		Sheet sheet = workbook.getSheetAt(0);

		// sayfadaki kolon sayısı
		// int noOfColumns = sheet.getRow(0).getLastCellNum();
		// System.out.println("-------Sayfada '" + noOfColumns + "' kolon var------");

		
		List<Ogrenci> studentList = new ArrayList<Ogrenci>();
		SimpleDateFormat dogum_tarihi = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat kayit_tarihi = new SimpleDateFormat("dd.MM.yyyy-HH:mm");// dogru
		SimpleDateFormat tarih = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat zaman = new SimpleDateFormat("dd.MM.yyyy-HH:mm");// dogru
		int a = 0;
		try
		{
			for (Row row : sheet) {
				Ogrenci ogrenci = new Ogrenci();
				if (a != 0) {
					ogrenci.setTcNo(dataFormatter.formatCellValue(row.getCell(kolon.get("tc_no"))));
					ogrenci.setBasvuruYil(Integer.parseInt(thisyear.toString()));
					ogrenci.setAdSoyad(dataFormatter.formatCellValue(row.getCell(kolon.get("adSoyad"))));
					ogrenci.setCinsiyet(dataFormatter.formatCellValue(row.getCell(kolon.get("cinsiyet"))));
					Cell cell = row.getCell(kolon.get("dogumTarihi"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						ogrenci.setDogumTarihi(dogum_tarihi
								.parse(dataFormatter.formatCellValue(row.getCell(kolon.get("dogumTarihi"))).trim().replaceAll("\\s+", "")));
					}
					ogrenci.setDogumYeri(dataFormatter.formatCellValue(row.getCell(kolon.get("dogumYeri"))));
					ogrenci.setBasBolum(dataFormatter.formatCellValue(row.getCell(kolon.get("basBolum"))));
					
					cell = row.getCell(kolon.get("yksPuani"));
					if(cell != null && cell.getCellType() != CellType.BLANK ) {
						ogrenci.setYksPuani(Double
								.valueOf(dataFormatter.formatCellValue(row.getCell(kolon.get("yksPuani"))).replace(',', '.')));
					}
					
					cell = row.getCell(kolon.get("aobpPuani"));
					if(cell != null && cell.getCellType() != CellType.BLANK ) {
						ogrenci.setAobpPuani(Double
								.valueOf(dataFormatter.formatCellValue(row.getCell(kolon.get("aobpPuani"))).replace(',', '.')));
					}
					ogrenci.setMezunLise(dataFormatter.formatCellValue(row.getCell(kolon.get("mezunLise"))));
					ogrenci.setMezunLiseTuru(dataFormatter.formatCellValue(row.getCell(kolon.get("mezunLiseTuru"))));
					ogrenci.setMezunLiseBolum(dataFormatter.formatCellValue(row.getCell(kolon.get("mezunLiseBolum"))));
					
					cell = row.getCell(kolon.get("liseAlan"));
					if(cell != null && cell.getCellType() != CellType.BLANK){
						ogrenci.setLiseAlan(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("liseAlan")))));
					}
					cell = row.getCell(kolon.get("oncekiSeneYerlesme"));
					if(cell != null && cell.getCellType() != CellType.BLANK){
						ogrenci.setOncekiSeneYerlesme(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("oncekiSeneYerlesme")))));
					}
					cell = row.getCell(kolon.get("milliSporcu"));
					if(cell != null && cell.getCellType() != CellType.BLANK){
						ogrenci.setMilliSporcu(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("milliSporcu")))));
					}
					cell = row.getCell(kolon.get("ozgecmisPuani"));
					if(cell != null && cell.getCellType() != CellType.BLANK){
						ogrenci.setOzgecmisPuani(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("ozgecmisPuani")))));
					}
					ogrenci.setTel(dataFormatter.formatCellValue(row.getCell(kolon.get("tel"))).trim().replaceAll("\\s+", ""));
					cell = row.getCell(kolon.get("id"));
					if(cell != null && cell.getCellType() != CellType.BLANK){
						ogrenci.setSiraNo(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("id")))));// if ile kontrol et
					}
					
					cell = row.getCell(kolon.get("kayitTarihi"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						ogrenci.setKayitTarihi(kayit_tarihi.parse(dataFormatter.formatCellValue(row.getCell(kolon.get("kayitTarihi")))));
					}
					ogrenci.setSilindimi(false);
					Resim resim = new Resim();
					resim.setYol(dataFormatter.formatCellValue(row.getCell(kolon.get("yol"))));
					ogrenci.setResim(resim);
					Sinav sinav = new Sinav();
					cell = row.getCell(kolon.get("sure"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						sinav.setSure(Double.valueOf(dataFormatter.formatCellValue(row.getCell(kolon.get("sure"))).replace(',', '.')));
					}
					cell = row.getCell(kolon.get("hataPuani"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						sinav.setHataPuani(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(kolon.get("hataPuani")))));
					}
					cell = row.getCell(kolon.get("hamPuani"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						sinav.setHamPuani(Double.valueOf(dataFormatter.formatCellValue(row.getCell(kolon.get("hamPuani")))));
					}
					cell = row.getCell(kolon.get("puan"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						sinav.setHamPuani(Double.valueOf(dataFormatter.formatCellValue(row.getCell(kolon.get("puan")))));
					}
					
					cell = row.getCell(kolon.get("zaman"));
					if(cell != null && cell.getCellType() != CellType.BLANK ) {
						sinav.setZaman(zaman.parse(dataFormatter.formatCellValue(row.getCell(kolon.get("zaman")))));
					}
					
					sinav.setIpAdresi(dataFormatter.formatCellValue(row.getCell(kolon.get("ipAdresi"))));
					ogrenci.setSinav(sinav);
					Durum durum = new Durum();
					durum.setDurumu(dataFormatter.formatCellValue(row.getCell(kolon.get("durumu"))));
					ogrenci.setDurum(durum);
					Randevu randevu = new Randevu();
					cell = row.getCell(kolon.get("tarih"));
					if(cell != null && cell.getCellType() != CellType.BLANK) {
						randevu.setTarih(tarih.parse(dataFormatter.formatCellValue(row.getCell(kolon.get("tarih"))).trim()));
					}
					randevu.setSaat(dataFormatter.formatCellValue(row.getCell(kolon.get("saat"))));
					ogrenci.setRandevu(randevu);
					studentList.add(ogrenci);
					System.out.println(a);
				}
				a++;
			}
			return studentList;
		}catch (Exception e) {
			try {
				workbook.close();
				throw new Exception(e);
			} catch (Exception error) {
				System.out.println("Kayit :" + error);
			}
		}finally {
			workbook.close();
		}
		 return null;
	}

	@Override
	public int saveExcelData(List<Ogrenci> ogrenciler) {
		ogrenciler = this.studentRepository.saveAll(ogrenciler);
		return ogrenciler.size();
	}

	@Override
	public Page<Ogrenci> getAllByYear(int year,  Pageable pageable) {
		return this.studentRepository.findAllByBasvuruYil(year, pageable);
	}
	
	@Override
	public Page<Ogrenci> getByName(String name,int selectedYear,  Pageable pageable) {
		Page<Ogrenci> ogrenci = studentRepository.findByAdSoyadContainingIgnoreCaseAndBasvuruYil(name, selectedYear, pageable);	
		return ogrenci;
	}

	@Override
	public Page<Ogrenci> getById(int id,int selectedYear,  Pageable pageable) {
		Page<Ogrenci> ogrenci = studentRepository.findByIdAndBasvuruYil(id, selectedYear, pageable);	
		return ogrenci;
	}

	@Override
	public Page<Ogrenci> findBySiraNo(int siraNo,int selectedYear,  Pageable pageable) {
		Page<Ogrenci> ogrenci = studentRepository.findBySiraNoAndBasvuruYil(siraNo, selectedYear, pageable);	
		return ogrenci;
	}

	@Override
	public Page<Ogrenci> findyByTel(String tel,int selectedYear,  Pageable pageable) {
		Page<Ogrenci> ogrenci = studentRepository.findByTelAndBasvuruYil(tel, selectedYear, pageable);	
		return ogrenci;
	}


	@Override
	public Page<Ogrenci> getByTcNo(String tc, int selectedYear,  Pageable pageable) {
		Page<Ogrenci> ogrenci = studentRepository.findByTcNoAndBasvuruYil(tc, selectedYear, pageable);	
		return ogrenci;
	}

	@Override
	public	long getCount() {
		return studentRepository.count();
	}
	
	
	
	@Override
	public List<Ogrenci> findtByTcno(String tc, int selectedYear) {
		List<Ogrenci> ogrenci = studentRepository.findByTcNoAndBasvuruYil(tc, selectedYear);	
		return ogrenci;
	}

	@Override
	public List<Ogrenci> findAll() {
		return studentRepository.findAllByBasvuruYil(Calendar.getInstance().get(Calendar.YEAR));
	}
}

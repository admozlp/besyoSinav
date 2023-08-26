package com.ahievran.besyoSinav.business.abstracts;


import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.Ogrenci;

public interface ResultService {
	 Page<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(String cinsiyet, String basBolum, String  milliSporcu, Pageable pageable, String durumu);
	 List<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, String milliSporcu);// excel yazmak için
	 List<Integer>  countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, String milliSporcu);
	 int findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, String milliSporcu, String durumu);
	 int countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, String basBolum, String milliSporcu,  String durumu);

	 
	 Page<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumu(String basBolum, String  milliSporcu, Pageable pageable, String durumu);
	 List<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu);// excel yazmak için
	 List<Integer>  countByBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu);
	 int findCountBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, String milliSporcu, String durumu);
	 int countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String basBolum, String milliSporcu,  String durumu);
	 Workbook createExcel(List<Ogrenci> excelList);
	 
	 AlanEtkisi alanEtki();
	 

}

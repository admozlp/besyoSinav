package com.ahievran.besyoSinav.business.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface StudentListService {
	Page<Ogrenci> findByCinsiyetAndMilliSporcu(String cinsiyet, String milliSporcu, Pageable pageable);
	int countByCinsiyetAndMilliSporcu(String cinsiyet, String milliSporcu);
}

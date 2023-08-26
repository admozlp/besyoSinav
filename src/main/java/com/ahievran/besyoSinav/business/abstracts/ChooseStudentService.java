package com.ahievran.besyoSinav.business.abstracts;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface ChooseStudentService {
	Ogrenci findByTcNo(String tcNo);
	void updateOgrenciDurum(Ogrenci ogrenci, String durumu);
	Ogrenci findByDurumAndCinsiyet(String durum, String cinsiyet);
}
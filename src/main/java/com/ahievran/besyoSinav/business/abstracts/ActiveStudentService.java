package com.ahievran.besyoSinav.business.abstracts;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface ActiveStudentService {
	Ogrenci findActiveStudent(String cinsiyet);
	Ogrenci findByTc(String tc);
}

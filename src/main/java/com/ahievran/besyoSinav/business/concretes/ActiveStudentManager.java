package com.ahievran.besyoSinav.business.concretes;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.ActiveStudentService;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.Ogrenci;


@Service
public class ActiveStudentManager  implements ActiveStudentService{
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public Ogrenci findActiveStudent(String cinsiyet) {
		Ogrenci ogrenci = studentRepository.findByCinsiyetAndDurumDurumuAndBasvuruYil(cinsiyet, "Parkurda", Calendar.getInstance().get(Calendar.YEAR));		
		return ogrenci;
	}
	@Override
	public Ogrenci findByTc(String tc) {
		List<Ogrenci> ogrenciler = studentRepository.findByTcNoAndBasvuruYil(tc, Calendar.getInstance().get(Calendar.YEAR));
		
		if(ogrenciler != null) {
			Ogrenci ogrenci = ogrenciler.get(0);
			return ogrenci;
		}
		return null;
	}

}

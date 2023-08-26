package com.ahievran.besyoSinav.business.concretes;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.ChooseStudentService;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.Durum;
import com.ahievran.besyoSinav.entities.Ogrenci;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChooseStudentManager implements ChooseStudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Ogrenci findByTcNo(String tcNo) {
		if (studentRepository.existsByTcNo(tcNo)) {
			return studentRepository.findByTcNo(tcNo);
		}
		return null;
	}

	@Override
	public void updateOgrenciDurum(Ogrenci ogrenci, String durumu) {
		Durum durum = ogrenci.getDurum();
		durum.setDurumu(durumu);
		ogrenci.setDurum(durum);
		studentRepository.save(ogrenci);
	}

	@Override
	public Ogrenci findByDurumAndCinsiyet(String durum, String cinsiyet) {
		Calendar cl = Calendar.getInstance();
		int currentYear = cl.get(Calendar.YEAR);
		Ogrenci ogrenci = studentRepository.findByDurumDurumuAndCinsiyetAndBasvuruYil(durum, cinsiyet, currentYear);
		return ogrenci;
	}

}

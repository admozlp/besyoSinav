package com.ahievran.besyoSinav.business.concretes;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.StudentListService;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.Ogrenci;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentListManager  implements StudentListService{
	private StudentRepository studentRepository;

	@Override
	public Page<Ogrenci> findByCinsiyetAndMilliSporcu(String cinsiyet, String milliSporcu, Pageable pageable) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}else if(milliSporcu.equals("Hepsi")) {
			Page<Ogrenci> ogrenciler = studentRepository.findByCinsiyetAndBasvuruYilAndDurumDurumu(cinsiyet, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi", pageable);
			return ogrenciler;
		}
		Page<Ogrenci> ogrenciler = studentRepository.findByCinsiyetAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi", pageable);		
		return ogrenciler;
	}

	@Override
	public int countByCinsiyetAndMilliSporcu(String cinsiyet, String milliSporcu) {
		int millilik = 0;
		if(milliSporcu.equals("Milli")) {
			millilik = 1;
		}else if(milliSporcu.equals("Hepsi")) {
			return  studentRepository.countByCinsiyetAndBasvuruYilAndDurumDurumu(cinsiyet, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi");
		}
		return studentRepository.countByCinsiyetAndMilliSporcuAndBasvuruYilAndDurumDurumu(cinsiyet, millilik, Calendar.getInstance().get(Calendar.YEAR), "Sınava Girdi");
	}
}

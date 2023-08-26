package com.ahievran.besyoSinav.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.StatisticsService;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticsManager implements StatisticsService{
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public int findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil) {
		return studentRepository.countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(cinsiyet, basBolum, milliSporcu, basvuruYil);	
	}
	@Override
	public int findByCinsiyetAndBasBolumAndBasvuruYil(String cinsiyet, String basBolum, int basvuruYil) {
		return studentRepository.countByCinsiyetAndBasBolumAndBasvuruYil(cinsiyet, basBolum,basvuruYil);		
	}
	@Override
	public int findByBasBolumAndBasvuruYil(String basBolum, int basvuruYil) {
		return studentRepository.countByBasBolumAndBasvuruYil(basBolum, basvuruYil);		
	}
	@Override
	public int findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(int milliSporcu, String cinsiyet, String basBolum, String durum, int basvuruYil) {
		return studentRepository.countByMilliSporcuAndCinsiyetAndBasBolumAndDurumDurumuAndBasvuruYil(milliSporcu, cinsiyet,  basBolum, durum, basvuruYil);		
	}
}

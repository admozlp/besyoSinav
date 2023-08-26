package com.ahievran.besyoSinav.business.abstracts;

public interface StatisticsService {
	public int findCountByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil);
	int findByCinsiyetAndBasBolumAndBasvuruYil(String cinsiyet, String basBolum, int basvuruYil);
	int findByBasBolumAndBasvuruYil(String basBolum, int basvuruYil);
	
	int findByMilliSporcuAndCinsiyetAndBasBolumAndDurumAndBasvuruYil(int milliSporcu, String cinsiyet, String basBolum, String durum, int basvuruYil);
}
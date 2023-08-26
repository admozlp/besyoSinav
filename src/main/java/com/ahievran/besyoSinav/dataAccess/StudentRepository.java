package com.ahievran.besyoSinav.dataAccess;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface StudentRepository extends JpaRepository<Ogrenci, Integer> {
	 Page<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil,Pageable pageable, String durumu);
	 List<Ogrenci> findByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil, String durumu);// excel yazmak için
	 int countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil, String durumu);	 
	 int countByCinsiyetAndBasBolumAndMilliSporcuAndBasvuruYil(String cinsiyet, String basBolum, int milliSporcu, int basvuruYil);

	 Page<Ogrenci> findByCinsiyetAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, int milliSporcu, int basvuruYil, String durumu, Pageable pageable);
	 int countByCinsiyetAndMilliSporcuAndBasvuruYilAndDurumDurumu(String cinsiyet, int milliSporcu, int basvuruYil, String durumu);
	 
	 Page<Ogrenci> findByCinsiyetAndBasvuruYilAndDurumDurumu(String cinsiyet, int basvuruYil, String durumu, Pageable pageable);
	 int countByCinsiyetAndBasvuruYilAndDurumDurumu(String cinsiyet, int basvuruYil, String durumu);

	 
	 Page<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String basBolum, int milliSporcu, int basvuruYil,Pageable pageable, String durumu);
	 int countByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String basBolum, int milliSporcu, int basvuruYil, String durumu);
	 List<Ogrenci> findByBasBolumAndMilliSporcuAndBasvuruYilAndDurumDurumu(String basBolum, int milliSporcu, int basvuruYil, String durumu);// excel yazmak için
	 int countByBasBolumAndMilliSporcuAndBasvuruYil(String basBolum, int milliSporcu, int basvuruYil);

	 List<Ogrenci> findAllByBasvuruYil(int basvuruYil);
	 
	 
	 int countByCinsiyetAndBasBolumAndBasvuruYil(String cinsiyet, String basBolum, int basvuruYil);
	 int countByBasBolumAndBasvuruYil(String basBolum, int basvuruYil);
	 int countByMilliSporcuAndCinsiyetAndBasBolumAndDurumDurumuAndBasvuruYil(int milliSporcu, String cinsiyet, String basBolum, String durum, int basvuruYil);
	 
	 boolean existsByTcNo(String tcNo);
	 List<Ogrenci> findByCinsiyet(String cinsiyet);
	 Ogrenci findByDurumDurumu(String durum);
	 
	 
	 Page<Ogrenci> findByAdSoyadContainingIgnoreCaseAndBasvuruYil(String adSoyad, int basvuruYil, Pageable pageable);
	 Page<Ogrenci> findByIdAndBasvuruYil(int id, int basvuruYil, Pageable pageable);
	 Page<Ogrenci> findBySiraNoAndBasvuruYil(int siraNo, int basvuruYil, Pageable pageable);
	 Page<Ogrenci> findByTelAndBasvuruYil(String tel, int basvuruYil, Pageable pageable);
	 Page<Ogrenci> findByTcNoAndBasvuruYil(String tcNo, int basvuruYil, Pageable pageable);
	 Page<Ogrenci> findAllByBasvuruYil(int basvuruYil, Pageable pageable);

	 
	 List<Ogrenci> findByTcNoAndBasvuruYil(String tcNo, int basvuruYil);
	 List<Ogrenci> findByIdAndBasvuruYilAndCinsiyet(int id, int basvuruYil, String cinsiyet);
	 List<Ogrenci> findBySiraNoAndBasvuruYil(int siraNo, int basvuruYil);
	 List<Ogrenci> findByTelAndBasvuruYilAndCinsiyet(String tel, int basvuruYil, String cinsiyet);
	 List<Ogrenci> findByTcNoAndBasvuruYilAndCinsiyet(String tcNo, int basvuruYil, String cinsiyet);
	 Ogrenci findByDurumDurumuAndCinsiyetAndBasvuruYil(String durum, String cinsiyet, int basvuruYil);
	 List<Ogrenci> findByAdSoyadContainingIgnoreCaseAndCinsiyetAndBasvuruYil(String adSoyad, String cinsiyet, int basvuruYil);
	 List<Ogrenci> findBySiraNoAndCinsiyetAndBasvuruYil(int siraNo, String cinsiyet, int basvuruYil);
	 
	 Ogrenci findByTcNo(String tcNo);
	 
	 
	 // updateRecord
	 int countByCinsiyetAndDurumDurumuAndBasvuruYil(String cinsiyet, String durum, int basvuruYil);
	 int countByBasBolumAndCinsiyetAndBasvuruYil(String basBolum, String cinsiyet, int basvuruYil);
	 int countByCinsiyetAndBasvuruYil(String cinsiyet, int basvuruYil);
	 

	 Ogrenci findByCinsiyetAndDurumDurumuAndBasvuruYil(String cinsiyet, String durumu, int basvuruYil);

}
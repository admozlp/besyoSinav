package com.ahievran.besyoSinav.business.abstracts;

import java.util.List;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface MalePhysicalTestService {
	void updateSinav(String tcno , String durum, String ipAdresi) throws Exception;
	void updateScore(String tcno, double sure, int hataPuani, double hamPuan, double puan);
	double calculateRawScore(double sure);
	double calculateScore(double sure, int hataPuani);	
	
	boolean updateDurum(String tcno);
	
	Ogrenci getStudentByTc(String tcNo);
	Ogrenci getByDurumAndCinsiyet(String durum);
	List<Ogrenci> getByName(String name);
	List<Ogrenci> getById(int id);
	List<Ogrenci> findBySiraNo(int siraNo);
	List<Ogrenci> findyByTel(String tel);
}
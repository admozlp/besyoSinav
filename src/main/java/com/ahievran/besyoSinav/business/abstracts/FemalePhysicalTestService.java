package com.ahievran.besyoSinav.business.abstracts;

import java.util.List;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface FemalePhysicalTestService {
	Ogrenci getStudentByTc(String tcNo);
	Ogrenci getByDurumAndCinsiyet(String durum);
	void updateScore(String tcno, double sure, int hataPuani, double hamPuan, double puan);
	void updateSinav(String tcno, String durum, String ipAdresi) throws Exception;
	double calculateRawScore(double sure);
	double calculateScore(double sure, int hataPuani);	
	List<Ogrenci> getByName(String name);
	List<Ogrenci> getById(int id);
	List<Ogrenci> findBySiraNo(int siraNo);
	List<Ogrenci> findyByTel(String tel);
	
	public boolean updateDurum(String tcno);
}

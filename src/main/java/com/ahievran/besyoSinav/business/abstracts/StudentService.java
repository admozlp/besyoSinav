package com.ahievran.besyoSinav.business.abstracts;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahievran.besyoSinav.entities.Ogrenci;

public interface StudentService {
	List<Ogrenci> getExcelDataAsList() throws ParseException, Exception;
	int saveExcelData(List<Ogrenci> ogrenciler);
	
	
	Page<Ogrenci>  getAllByYear(int year, Pageable pageable);	
	Page<Ogrenci> getById(int id, int selectedYear, Pageable pageable);
	Page<Ogrenci> findBySiraNo(int siraNo,int selectedYear, Pageable pageable);
	Page<Ogrenci> findyByTel(String tel,int selectedYear, Pageable pageable);	
	Page<Ogrenci> getByName(String name,int selectedYear, Pageable pageable);
	Page<Ogrenci> getByTcNo(String tc,int selectedYear, Pageable pageable);
	long getCount();
	List<Ogrenci> findtByTcno(String tc,int selectedYear);
	List<Ogrenci>  findAll();	

}
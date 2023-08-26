package com.ahievran.besyoSinav.business.concretes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.MalePhysicalTestService;
import com.ahievran.besyoSinav.dataAccess.FieldEffectRepository;
import com.ahievran.besyoSinav.dataAccess.MaleScoreRepository;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.Durum;
import com.ahievran.besyoSinav.entities.ErkekPuan;
import com.ahievran.besyoSinav.entities.Ogrenci;
import com.ahievran.besyoSinav.entities.Sinav;

@Service
public class MalePhysicalTestManager implements MalePhysicalTestService{
	@Autowired
	private StudentRepository studentRepository;
	private MaleScoreRepository maleScoreRepository;
	private FieldEffectRepository fieldEffectRepository;
	int currentYear;

	@Autowired
	public MalePhysicalTestManager(StudentRepository studentRepository, MaleScoreRepository maleScoreRepository,FieldEffectRepository fieldEffectRepository) {
		this.studentRepository = studentRepository;
		this.maleScoreRepository = maleScoreRepository;
		this.fieldEffectRepository = fieldEffectRepository;
		Calendar cl = Calendar.getInstance();
		currentYear = cl.get(Calendar.YEAR);
	}
	
	@Override
	public Ogrenci getStudentByTc(String tcNo) {			
		List<Ogrenci> ogrenciler =  studentRepository.findByTcNoAndBasvuruYilAndCinsiyet(tcNo,currentYear, "Erkek");
		if(ogrenciler.size() > 0) {
			Ogrenci ogrenci = ogrenciler.get(0);
			return ogrenci;
		}
		return null;
	}
	
	@Override
	public Ogrenci getByDurumAndCinsiyet(String durum) {
		Ogrenci ogrenci = studentRepository.findByDurumDurumuAndCinsiyetAndBasvuruYil(durum, "Erkek", currentYear);
		return ogrenci;
	}
	
	@Override
	public List<Ogrenci> getByName(String name) {
		List<Ogrenci> ogrenci = studentRepository.findByAdSoyadContainingIgnoreCaseAndCinsiyetAndBasvuruYil(name, "Erkek", currentYear);	
		return ogrenci;
	}

	@Override
	public double calculateRawScore(double sure) {

		List<ErkekPuan> erkekPuanlar = maleScoreRepository.findAll();
		Collections.sort(erkekPuanlar, Comparator.comparingDouble(ErkekPuan::getPuan).reversed());

		for(ErkekPuan erkekPuan : erkekPuanlar) {
			if(sure <= erkekPuan.getSure()) {
				return erkekPuan.getPuan();
			}
		}
		return  0;
	}

	@Override
	public double calculateScore(double sure, int hataPuani) {

			double toplamScore = sure + hataPuani;
			
			List<ErkekPuan> erkekPuanlar = maleScoreRepository.findAll();
			Collections.sort(erkekPuanlar, Comparator.comparingDouble(ErkekPuan::getPuan).reversed());

			for(ErkekPuan erkekPuan : erkekPuanlar) {
				if(toplamScore <= erkekPuan.getSure()) {
					return erkekPuan.getPuan();
				}
			}
		return 0;
	}
	
	@Override
	public void updateScore(String tcno, double sure, int hataPuani, double hamPuan, double puan) {
		try {
			Ogrenci dbOgrenci = studentRepository.findByTcNo(tcno);
			Sinav sinav = dbOgrenci.getSinav();
			sinav.setSure(sure);
			sinav.setHataPuani(hataPuani);
			sinav.setHamPuani(hamPuan);
			sinav.setPuan(puan);
			dbOgrenci.setSinav(sinav);
			studentRepository.save(dbOgrenci);
		}catch (Exception e) {
		}
	}
	
	@Override
	public void updateSinav(String tcno, String durum, String ipAdresi) throws Exception {
		try {
			Ogrenci dbOgrenci = studentRepository.findByTcNo(tcno);
	
			Sinav sinav = dbOgrenci.getSinav();
			sinav.setIpAdresi(ipAdresi);
			Date currentDate = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
	        String formattedDateTime = dateFormat.format(currentDate);
	        Date parsedDate = dateFormat.parse(formattedDateTime);
	    	sinav.setZaman(parsedDate);
	    	dbOgrenci.setSinav(sinav);
	    	
	    	Durum dbDurum = dbOgrenci.getDurum();
	    	dbDurum.setDurumu(durum);
	    	dbOgrenci.setDurum(dbDurum);
	    	
	    	int liseAlan = dbOgrenci.getLiseAlan();
	    	int oncekiSeneYerlesme = dbOgrenci.getOncekiSeneYerlesme();
	    	double oysp = sinav.getPuan();
	    	double obp = dbOgrenci.getAobpPuani();
	    	double tyt = dbOgrenci.getYksPuani();
			
	    	List<AlanEtkisi> dbAlanEtkileri = fieldEffectRepository.findAll();
			AlanEtkisi dbAlanEtkisi = dbAlanEtkileri.get(0);
	    	double oyspYuzde =dbAlanEtkisi.getOysp();
	    	double obpYuzde = dbAlanEtkisi.getAobp();
	    	double tytYuzde = dbAlanEtkisi.getTyt();
	    	
	    	double obpEkstraYuzde = dbAlanEtkisi.getAobpEkstraKatSayi();
	    	double osypTamKatSayi = dbAlanEtkisi.getOyspTamKatSayi();
	    	
	    	double yerlestirmePuani = 0;
	    	
	    	if(liseAlan == 0 && oncekiSeneYerlesme == 0) {
	    		yerlestirmePuani = (oyspYuzde * oysp * osypTamKatSayi ) + (obpYuzde * obp) + (tytYuzde * tyt);
	    	}else if(liseAlan == 0 && oncekiSeneYerlesme == 1) {
	    		yerlestirmePuani = (oyspYuzde * oysp * osypTamKatSayi ) + (obpYuzde * obp) / 2 + (tytYuzde * tyt);

	    	}else if(liseAlan == 1 && oncekiSeneYerlesme == 0) {	    		
	    		yerlestirmePuani = (oyspYuzde * oysp * osypTamKatSayi ) + (obpYuzde * obp) + (tytYuzde * tyt) + (obpEkstraYuzde * obp) ;
	    		//yerlestirmePuani = (0.55 * oysp * 5 ) + (0.12 * obp) + (0.45 * tyt) + (0.06 * obp) ;
	    	}else if(liseAlan == 1 && oncekiSeneYerlesme == 1) {
	    		yerlestirmePuani = (oyspYuzde * oysp * osypTamKatSayi ) + (obpYuzde * obp) / 2 + (tytYuzde * tyt) + (obpEkstraYuzde * obp) ;
	    	}
	    	dbOgrenci.setYp(yerlestirmePuani);
			studentRepository.save(dbOgrenci);			
			
		}catch (Exception e) {
			System.out.println("Sınav kaydı : " + e.getLocalizedMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<Ogrenci> getById(int id) {
		List<Ogrenci> ogrenci = studentRepository.findByIdAndBasvuruYilAndCinsiyet(id, currentYear, "Erkek");	
		return ogrenci;
	}

	@Override
	public List<Ogrenci> findBySiraNo(int siraNo) {
		List<Ogrenci> ogrenci = studentRepository.findBySiraNoAndCinsiyetAndBasvuruYil(siraNo, "Erkek", currentYear);	
		return ogrenci;
	}

	@Override
	public List<Ogrenci> findyByTel(String tel) {
		List<Ogrenci> ogrenci = studentRepository.findByTelAndBasvuruYilAndCinsiyet(tel, currentYear, "Erkek");	
		return ogrenci;
	}

	@Override
	public boolean updateDurum(String tcno) {
		Ogrenci aktifOgrenci = studentRepository.findByDurumDurumuAndCinsiyetAndBasvuruYil("Parkurda", "Erkek", currentYear);

		if(aktifOgrenci == null || aktifOgrenci.getTcNo().equals(tcno)) {
			List<Ogrenci> ogrenciler =  studentRepository.findByTcNoAndBasvuruYilAndCinsiyet(tcno,currentYear, "Erkek");
			if(ogrenciler.size() > 0) {
				Ogrenci ogrenci = ogrenciler.get(0);
				ogrenci.getDurum().setDurumu("Parkurda");
				studentRepository.save(ogrenci);
				return true;
			}
		}
		return false;		
	}
}
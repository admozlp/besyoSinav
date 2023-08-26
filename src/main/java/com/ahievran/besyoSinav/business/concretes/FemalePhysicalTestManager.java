package com.ahievran.besyoSinav.business.concretes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.FemalePhysicalTestService;
import com.ahievran.besyoSinav.dataAccess.FemaleScoreRepository;
import com.ahievran.besyoSinav.dataAccess.FieldEffectRepository;
import com.ahievran.besyoSinav.dataAccess.StudentRepository;
import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.BayanPuan;
import com.ahievran.besyoSinav.entities.Durum;
import com.ahievran.besyoSinav.entities.Ogrenci;
import com.ahievran.besyoSinav.entities.Sinav;

@Service
public class FemalePhysicalTestManager implements FemalePhysicalTestService {
	@Autowired
	private StudentRepository studentRepository;
	private FemaleScoreRepository femaleScoreRepository;
	private FieldEffectRepository fieldEffectRepository;

	int currentYear;

	@Autowired
	public FemalePhysicalTestManager(StudentRepository studentRepository, FemaleScoreRepository femaleScoreRepository,FieldEffectRepository fieldEffectRepository) {
		this.studentRepository = studentRepository;
		this.femaleScoreRepository = femaleScoreRepository;
		this.fieldEffectRepository = fieldEffectRepository;
		Calendar cl = Calendar.getInstance();
		currentYear = cl.get(Calendar.YEAR);
	}
	@Override
	public Ogrenci getStudentByTc(String tcNo) {
		List<Ogrenci> ogrenciler =  studentRepository.findByTcNoAndBasvuruYilAndCinsiyet(tcNo,currentYear, "Bayan");
		if(ogrenciler.size() > 0) {
			Ogrenci ogrenci = ogrenciler.get(0);
			return ogrenci;
		}
		return null;
	}

	@Override
	public Ogrenci getByDurumAndCinsiyet(String durum) {

		Ogrenci ogrenci = studentRepository.findByDurumDurumuAndCinsiyetAndBasvuruYil(durum, "Bayan", currentYear);
		return ogrenci;
	}

	@Override
	public List<Ogrenci> getByName(String name) {
		List<Ogrenci> ogrenci = studentRepository.findByAdSoyadContainingIgnoreCaseAndCinsiyetAndBasvuruYil(name, "Bayan", currentYear);
		return ogrenci;
	}

	@Override
	public double calculateRawScore(double sure) {
		List<BayanPuan> bayanPuanlar = femaleScoreRepository.findAll();
		Collections.sort(bayanPuanlar, Comparator.comparingDouble(BayanPuan::getPuan).reversed());

		for (BayanPuan bayanPuan : bayanPuanlar) {
			if (sure <= bayanPuan.getSure()) {
				return bayanPuan.getPuan();
			}
		}
		return 0;
	}

	@Override
	public double calculateScore(double sure, int hataPuani) {
		double toplamScore = sure + hataPuani;

		List<BayanPuan> bayanPuanlar = femaleScoreRepository.findAll();
		Collections.sort(bayanPuanlar, Comparator.comparingDouble(BayanPuan::getPuan).reversed());

		for (BayanPuan bayanPuan : bayanPuanlar) {
			if (toplamScore <= bayanPuan.getSure()) {
				return bayanPuan.getPuan();
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
		} catch (Exception e) {
		}
	}

	@Override
	public void updateSinav(String tcno, String durum, String ipAdresi) throws Exception {
		try {
			Ogrenci dbOgrenci = studentRepository.findByTcNo(tcno);
			dbOgrenci.getSinav().setIpAdresi(ipAdresi);
			Sinav sinav = dbOgrenci.getSinav();
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
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public List<Ogrenci> getById(int id) {
		List<Ogrenci> ogrenci = studentRepository.findByIdAndBasvuruYilAndCinsiyet(id, currentYear,"Bayan");	
		return ogrenci;
	}

	@Override
	public List<Ogrenci> findBySiraNo(int siraNo) {
		List<Ogrenci> ogrenci = studentRepository.findBySiraNoAndCinsiyetAndBasvuruYil(siraNo, "Bayan", currentYear);	
		return ogrenci;
	}

	@Override
	public List<Ogrenci> findyByTel(String tel) {
		List<Ogrenci> ogrenci = studentRepository.findByTelAndBasvuruYilAndCinsiyet(tel, currentYear,"Bayan");	
		return ogrenci;
	}
	
	@Override
	public boolean updateDurum(String tcno) {
		Ogrenci aktifOgrenci = studentRepository.findByDurumDurumuAndCinsiyetAndBasvuruYil("Parkurda", "Bayan", currentYear);

		if(aktifOgrenci == null || aktifOgrenci.getTcNo().equals(tcno)) {
			List<Ogrenci> ogrenciler =  studentRepository.findByTcNoAndBasvuruYilAndCinsiyet(tcno,currentYear, "Bayan");
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

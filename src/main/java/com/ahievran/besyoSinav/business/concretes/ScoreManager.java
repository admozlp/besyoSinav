package com.ahievran.besyoSinav.business.concretes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ahievran.besyoSinav.business.abstracts.ScoreService;
import com.ahievran.besyoSinav.business.requests.BayanPuanListe;
import com.ahievran.besyoSinav.business.requests.ErkekPuanListe;
import com.ahievran.besyoSinav.business.requests.UpdateFieldEffectsRequest;
import com.ahievran.besyoSinav.core.utilities.mapper.MapperService;
import com.ahievran.besyoSinav.dataAccess.FemaleScoreRepository;
import com.ahievran.besyoSinav.dataAccess.FieldEffectRepository;
import com.ahievran.besyoSinav.dataAccess.MaleScoreRepository;
import com.ahievran.besyoSinav.entities.AlanEtkisi;
import com.ahievran.besyoSinav.entities.BayanPuan;
import com.ahievran.besyoSinav.entities.ErkekPuan;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScoreManager implements ScoreService{
	private MaleScoreRepository maleScoreRepository;
	private FemaleScoreRepository femaleScoreRepository;
	private FieldEffectRepository fieldEffectRepository;
	private MapperService mapperService;
	
	@Override
	public ErkekPuanListe getAllMaleScores() {
		List<ErkekPuan> erkekPuanlar = maleScoreRepository.findAll();
		
		Collections.sort(erkekPuanlar, Comparator.comparingDouble(ErkekPuan::getPuan).reversed());
		
		ErkekPuanListe erkekPuanlarListe = new ErkekPuanListe();
		
		erkekPuanlarListe.setErkekPuanlar(erkekPuanlar);	
		return erkekPuanlarListe;
	}
	@Override
	public void updateMaleScores(ErkekPuanListe erkekPuanListe) {
		List<ErkekPuan> dbErkekPuanlar = maleScoreRepository.findAll();
		
		List<ErkekPuan> gelenErkekPuanlar = erkekPuanListe.getErkekPuanlar();
				
		int a = 0;
		for(ErkekPuan erkekPuan : gelenErkekPuanlar) {			
			dbErkekPuanlar.get(a).setSure(erkekPuan.getSure());
			dbErkekPuanlar.get(a).setPuan(erkekPuan.getPuan());
			a++;
		}
					
		maleScoreRepository.saveAll(dbErkekPuanlar);		
	}

	@Override
	public BayanPuanListe getAllFemaleScores() {
		List<BayanPuan> bayanPuanlar = femaleScoreRepository.findAll();
		
		Collections.sort(bayanPuanlar, Comparator.comparingDouble(BayanPuan::getPuan).reversed());
		
		BayanPuanListe bayanPuanlarListe = new BayanPuanListe();
		
		bayanPuanlarListe.setBayanPuanlar(bayanPuanlar);
		
		return bayanPuanlarListe;
	}
	
	@Override
	public void updateFeMaleScores(BayanPuanListe bayanPuanListe) {
		List<BayanPuan> dbBayanPuanlar = femaleScoreRepository.findAll();
		
		List<BayanPuan> gelenBayanPuanlar = bayanPuanListe.getBayanPuanlar();
		int a = 0;
		for(BayanPuan bayanPuan : gelenBayanPuanlar) {			
			dbBayanPuanlar.get(a).setSure(bayanPuan.getSure());
			dbBayanPuanlar.get(a).setPuan(bayanPuan.getPuan());
			a++;
		}					
		femaleScoreRepository.saveAll(dbBayanPuanlar);			
	}
	
	
	
	@Override
	public List<AlanEtkisi> getFieldEffects() {
		List<AlanEtkisi> alanEtkileri = fieldEffectRepository.findAll();
		return alanEtkileri;
	}
	@Override
	public void updateFieldEffects(UpdateFieldEffectsRequest updateFieldEffectsRequest) {
		List<AlanEtkisi> dbAlanEtkileri = fieldEffectRepository.findAll();
		
		AlanEtkisi dbAlanEtkisi = dbAlanEtkileri.get(0);
		
		mapperService.forRequest().map(updateFieldEffectsRequest, dbAlanEtkisi);
		
		fieldEffectRepository.save(dbAlanEtkisi);
	}
}

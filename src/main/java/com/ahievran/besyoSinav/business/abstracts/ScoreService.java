package com.ahievran.besyoSinav.business.abstracts;

import java.util.List;

import com.ahievran.besyoSinav.business.requests.BayanPuanListe;
import com.ahievran.besyoSinav.business.requests.ErkekPuanListe;
import com.ahievran.besyoSinav.business.requests.UpdateFieldEffectsRequest;
import com.ahievran.besyoSinav.entities.AlanEtkisi;

public interface ScoreService {
	ErkekPuanListe getAllMaleScores();
	void updateMaleScores(ErkekPuanListe erkekPuanListe);
	BayanPuanListe getAllFemaleScores();
	void updateFeMaleScores(BayanPuanListe bayanPuanListe);
	List<AlanEtkisi> getFieldEffects();
	void updateFieldEffects(UpdateFieldEffectsRequest updateFieldEffectsRequest);
}

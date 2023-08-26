package com.ahievran.besyoSinav.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFieldEffectsRequest {
	
	private double oysp;
	private double oyspTamKatSayi;


	private double aobp;
	private double aobpEkstraKatSayi;


	private double tyt;
}

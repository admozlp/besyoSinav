package com.ahievran.besyoSinav.business.requests;


import com.ahievran.besyoSinav.entities.Sinav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
	private int id;
	private String adSoyad;
	private String tcNo;
	private String basBolum;
	private double ozgecmisPuani;
	
	private Sinav sinav;
}
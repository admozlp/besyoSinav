package com.ahievran.besyoSinav.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="alanEtki")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlanEtkisi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
		
	@Column(name="oysp")
	private double oysp;
	@Column(name="oyspTamKatSayi")
	private double oyspTamKatSayi;
	
	@Column(name = "aobp")
	private double aobp;
	@Column(name = "aobpEkstraKatSayi")
	private double aobpEkstraKatSayi;
	
	
	@Column(name="tyt")
	private double tyt;
}

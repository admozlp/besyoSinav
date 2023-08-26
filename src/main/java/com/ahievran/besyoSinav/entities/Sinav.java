package com.ahievran.besyoSinav.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sinavlar")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sinav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "sure")
	private double sure;
	
	@Column(name = "hataPuani")
	private int hataPuani;
	
	@Column(name = "hamPuani")
	private double hamPuani;
	
	@Column(name = "puan")
	private double puan;
	
	@Column(name = "zaman")
	private Date zaman;
	
	@Column(name = "ipAdresi")
	private String ipAdresi;
	
    @OneToOne(mappedBy = "sinav")
    private Ogrenci ogrenci;// one to one -> ogrenci tablosu
}

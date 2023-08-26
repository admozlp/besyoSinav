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
@Table(name = "randevular")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Randevu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
		
	@Column(name = "tarih")
	private Date tarih;
	 
	
	@Column(name = "saat")
	private String saat;
	
	@OneToOne(mappedBy = "randevu")
	private Ogrenci ogrenci; // one to one -> ogrenci tablosu
}

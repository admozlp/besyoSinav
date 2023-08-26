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

@Table(name="erkekPuan")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErkekPuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="sure")
	private double sure;
	
	@Column(name="puan")
	private double puan;
}
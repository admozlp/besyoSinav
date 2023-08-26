package com.ahievran.besyoSinav.entities;

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
@Table(name = "resimler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "yol")
	private String yol;
	
    @OneToOne(mappedBy = "resim")
    private Ogrenci ogrenci;// one to one -> ogrenci tablosu
}

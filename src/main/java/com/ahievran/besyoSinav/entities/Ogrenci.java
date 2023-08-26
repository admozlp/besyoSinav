package com.ahievran.besyoSinav.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ogrenciler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ogrenci {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
  	@Column(name = "basvuruYil")
	private int basvuruYil;
	
	@Column(name = "tcNo")
	private String tcNo;
	
	@Column(name = "adSoyad")
	private String adSoyad;
	
	@Column(name = "cinsiyet")
	private String cinsiyet;
	
	@Column(name="dogumTarihi")
	private Date dogumTarihi;
	
	@Column(name = "dogumYeri")
	private String dogumYeri;
	
	@Column(name = "basBolum")
	private String basBolum;
	
	@Column(name = "yksPuani")
	private double yksPuani;
	
	@Column(name = "aobpPuani")
	private double aobpPuani;
	
	@Column(name = "mezunLise")
	private String mezunLise;
	
	@Column(name = "mezunLiseTuru")
	private String mezunLiseTuru;
	
	@Column(name = "mezunLiseBolum")
	private String mezunLiseBolum;
	
	@Column(name = "liseAlan")
	private int liseAlan;
	
	@Column(name = "oncekiSeneYerlesme")
	private int oncekiSeneYerlesme;
	
	@Column(name = "milliSporcu")
	private int milliSporcu;
	
	@Column(name = "ozgecmisPuani")
	private double ozgecmisPuani;
		
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "siraNo")
	private int siraNo;
	
	@Column(name = "kayitTarihi")
	private Date kayitTarihi;
	
	@Column(name = "silindimi")
	private boolean silindimi;
	
	@Column(name="yp")
	private double yp;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resimId", referencedColumnName = "id")
	private Resim resim;// one to one -> resim tablosu;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sinavId",  referencedColumnName = "id")
    private Sinav sinav;// one to one -> sinav tablosu : kayit yapılırken ogrencinin sınavı olmak zorunda değil
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "durumId", referencedColumnName = "id")
	private Durum durum;// one to one  -> durum tablosu -> ogerenciye tası
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "randevuId", referencedColumnName = "id")
	private Randevu randevu;
	
	
}

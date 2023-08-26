package com.ahievran.besyoSinav.business.requests;

import java.util.List;

import com.ahievran.besyoSinav.entities.BayanPuan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BayanPuanListe {
	List<BayanPuan> bayanPuanlar;
}

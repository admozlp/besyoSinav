package com.ahievran.besyoSinav.core.utilities;

import java.util.HashMap;

public class Columns {
	public HashMap<String, Integer> Map() {
		HashMap<String, Integer> kolon = new HashMap<String, Integer>();

		kolon.put("id", 0);
		kolon.put("tc_no", 1);
		kolon.put("adSoyad", 2);
		kolon.put("cinsiyet", 3);
		kolon.put("dogumTarihi", 4);
		kolon.put("dogumYeri", 5);
		kolon.put("basBolum", 6);
		kolon.put("yksPuani", 7);
		kolon.put("aobpPuani", 8);
		kolon.put("mezunLise", 9);
		kolon.put("mezunLiseTuru", 10);
		kolon.put("mezunLiseBolum", 11);
		kolon.put("liseAlan", 12);
		kolon.put("oncekiSeneYerlesme", 13);
		kolon.put("milliSporcu", 14);
		kolon.put("ozgecmisPuani", 15);
		kolon.put("tel", 17);
		kolon.put("siraNo", 19);
		kolon.put("kayitTarihi", 18);
		kolon.put("yol", 16);
		kolon.put("sure", 20);
		kolon.put("hataPuani", 21);
		kolon.put("hamPuani", 22);
		kolon.put("puan", 23);
		kolon.put("zaman", 23);
		kolon.put("ipAdresi", 28);
		kolon.put("durumu", 25);
		kolon.put("tarih", 26);
		kolon.put("saat", 27);
		
		return kolon;
	}
}

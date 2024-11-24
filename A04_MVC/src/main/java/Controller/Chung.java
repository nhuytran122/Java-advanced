package Controller;

import java.util.ArrayList;

import loaimodal.loai;
import loaimodal.loaibo;

public class Chung {
	public static ArrayList<loai> getDsLoai() throws Exception {
        loaibo lbo = new loaibo();
        return lbo.getListLoai();
    }
	
	public static boolean isNullOrWhiteSpace(String str) {
	    return str == null || str.trim().isEmpty();
	}
}

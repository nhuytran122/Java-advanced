package Controller;

import java.util.ArrayList;

import loaimodal.loai;
import loaimodal.loaibo;

public class Chung {
	public static ArrayList<loai> getDsLoai() {
        loaibo lbo = new loaibo();
        return lbo.getLoai();
    }
}

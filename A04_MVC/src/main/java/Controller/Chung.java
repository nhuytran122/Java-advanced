package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public static Date convertToDate(String stDMY) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date kq = null;
		try {
			kq = sdf.parse(stDMY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public static String convertDateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDay = sdf.format(d);
		return strDay;
	}
}

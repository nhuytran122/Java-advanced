package Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import loaimodal.loai;
import loaimodal.loaibo;

public class Chung {
	public static void getDsLoai(HttpServletRequest request) {
        loaibo lbo = new loaibo();
        ArrayList<loai> dsLoai = lbo.getLoai();
        request.setAttribute("dsLoai", dsLoai);
    }
}

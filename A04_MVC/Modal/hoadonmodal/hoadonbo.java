package hoadonmodal;

import java.util.ArrayList;

public class hoadonbo {
	hoadondao hddao = new hoadondao();
	ArrayList<hoadon> ds;
	public ArrayList<hoadon> getListHoaDon(long maKh) throws Exception{
		ds = hddao.getListHoaDon(maKh);
		return ds;
	}
	
	public long themHoaDon(long maKh) throws Exception {
		return hddao.themHoaDon(maKh);
	}
}

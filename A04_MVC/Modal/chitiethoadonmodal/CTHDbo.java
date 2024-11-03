package chitiethoadonmodal;

import java.util.ArrayList;

import sachmodal.sachbo;

public class CTHDbo {
	CTHDdao cthddao = new CTHDdao();
	ArrayList<CTHD> ds;
	public ArrayList<CTHD> getListCTHD(long maHD) throws Exception{
		ds = cthddao.getListHoaDon(maHD);
		return ds;
	}
	
	public long themCTHD(String maSach, int  slm, long maHD) throws Exception {
		return cthddao.themCTHD(maSach, slm, maHD);
	}
}

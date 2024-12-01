package hoadonmodal;

import java.util.ArrayList;

public class hoadonbo {
	hoadondao hddao = new hoadondao();
	
	public long themHoaDon(long maKh) throws Exception {
		return hddao.themHoaDon(maKh);
	}
	public long getMaxHD() throws Exception {
		return hddao.getMaxHD();
	}
	public long updateHDByKH(long makh) throws Exception {
		return hddao.updateHDByKH(makh);
	}
	public int countAllHD() throws Exception {
		return hddao.countAllHD();
	}
	public int countHDPaid() throws Exception {
		return hddao.countHDPaid();
	}
	public ArrayList<hoadon> getAllHD(String searchValue, int page, int pageSize) throws Exception {
		return hddao.getAllHD(searchValue, page, pageSize);
	}
	public int countHDByCondition(String searchValue) throws Exception {
		return hddao.countHDByCondition(searchValue);
	}
	public int deleteHD(long maHD) throws Exception {
		return hddao.deleteHD(maHD);
	}
}

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
	
//	public long updateHDByKH(long makh) throws Exception {
//		return hddao.updateHDByKH(makh);
//	}
	
	public int countAllHD() throws Exception {
		return hddao.countAllHD();
	}
	
	public int countHDPaid() throws Exception {
		return hddao.countHDPaid();
	}
	
	public ArrayList<hoadon> getAllHDFromView(String searchValue, int page, int pageSize) throws Exception {
		return hddao.getAllHDFromView(searchValue, page, pageSize);
	}
	
	public ArrayList<hoadon> getListHDUnpaidFromView(String searchValue, int page, int pageSize) throws Exception {
		return hddao.getListHDUnpaidFromView(searchValue, page, pageSize);
	}
	
	public int countAllHDByCondition(String searchValue) throws Exception {
		return hddao.countAllHDByCondition(searchValue);
	}
	
	public int countListHDUnpaidByCondition(String searchValue) throws Exception {
		return hddao.countListHDUnpaidByCondition(searchValue);
	}
	
	public int payHD(long maHD) throws Exception {
		return hddao.payHD(maHD);
	}
	
	public int deleteHD(long maHD) throws Exception {
		return hddao.deleteHD(maHD);
	}
	
	public hoadon getHDByMaHD(Long maHD) throws Exception {
		return hddao.getHDByMaHD(maHD);
	}
}

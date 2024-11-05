package hoadonmodal;

public class hoadonbo {
	hoadondao hddao = new hoadondao();
	
	public long themHoaDon(long maKh) throws Exception {
		return hddao.themHoaDon(maKh);
	}
	public long getMaxHD() throws Exception {
		return hddao.getMaxHD();
	}
}

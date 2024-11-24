package chitiethoadonmodal;

public class CTHDbo {
	CTHDdao cthddao = new CTHDdao();
	
	public long themCTHD(String maSach, long  slm, long MaHD) throws Exception {
		return cthddao.themCTHD(maSach, slm, MaHD);
	}
	
	public long thanhtoanCTHD(long MaCTHD) throws Exception {
		return  cthddao.thanhtoanCTHD(MaCTHD);
	}
	
	public long thanhtoanAll(long maKh) throws Exception {
		return cthddao.thanhtoanAll(maKh);
	}
	
	public long xoaCTHD(long MaCTHD) throws Exception {
		return cthddao.xoaCTHD(MaCTHD);
	}
	
	public long xoaAllCTHDNotPay(long maKh) throws Exception {
		return cthddao.xoaAllCTHDNotPay(maKh);
	}
}

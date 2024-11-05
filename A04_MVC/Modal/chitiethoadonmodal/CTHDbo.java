package chitiethoadonmodal;

public class CTHDbo {
	CTHDdao cthddao = new CTHDdao();
	
	public long themCTHD(String maSach, long  slm, long MaHD) throws Exception {
		return cthddao.themCTHD(maSach, slm, MaHD);
	}
}

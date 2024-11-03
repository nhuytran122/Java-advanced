package chitiethoadonmodal;


public class CTHD {
	private Long MaChiTietHD;
	private String MaSach;
	private int SoLuongMua;
	private Long MaHoaDon;
	public CTHD() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CTHD(Long maChiTietHD, String maSach, int soLuongMua, Long maHoaDon) {
		super();
		MaChiTietHD = maChiTietHD;
		MaSach = maSach;
		SoLuongMua = soLuongMua;
		MaHoaDon = maHoaDon;
	}
	public Long getMaChiTietHD() {
		return MaChiTietHD;
	}
	public void setMaChiTietHD(Long maChiTietHD) {
		MaChiTietHD = maChiTietHD;
	}
	public String getMaSach() {
		return MaSach;
	}
	public void setMaSach(String maSach) {
		MaSach = maSach;
	}
	public int getSoLuongMua() {
		return SoLuongMua;
	}
	public void setSoLuongMua(int soLuongMua) {
		SoLuongMua = soLuongMua;
	}
	public Long getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(Long maHoaDon) {
		MaHoaDon = maHoaDon;
	}
}

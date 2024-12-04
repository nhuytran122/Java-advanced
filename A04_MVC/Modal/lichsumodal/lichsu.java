package lichsumodal;

import java.util.Date;

public class lichsu {
	private Long makh;
	private String tensach;
	private Long SoLuongMua;
	private Long gia;
	private Long ThanhTien;
	private Date NgayMua;
	private Long maCTHD;
	private Long MaHoaDon;
	private boolean DaThanhToan;
	public lichsu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public lichsu(Long makh, String tensach, Long soLuongMua, Long gia, Long thanhTien, Date ngayMua, Long maCTHD, Long maHD, boolean DaThanhToan) {
		super();
		this.makh = makh;
		this.tensach = tensach;
		SoLuongMua = soLuongMua;
		this.gia = gia;
		ThanhTien = thanhTien;
		NgayMua = ngayMua;
		this.maCTHD = maCTHD;
		this.MaHoaDon = maHD;
		this.DaThanhToan = DaThanhToan;
	}
	public Long getMakh() {
		return makh;
	}
	public void setMakh(Long makh) {
		this.makh = makh;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public Long getSoLuongMua() {
		return SoLuongMua;
	}
	public void setSoLuongMua(Long soLuongMua) {
		SoLuongMua = soLuongMua;
	}
	public Long getGia() {
		return gia;
	}
	public void setGia(Long gia) {
		this.gia = gia;
	}
	public Long getThanhTien() {
		return ThanhTien;
	}
	public void setThanhTien(Long thanhTien) {
		ThanhTien = thanhTien;
	}
	public Date getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}
	public Long getMaCTHD() {
		return this.maCTHD;
	}
	public void setMaCTHD(Long maCTHD) {
		this.maCTHD = maCTHD;
	}
	public Long getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(Long maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public boolean isDaThanhToan() {
		return DaThanhToan;
	}
	public void setDaThanhToan(boolean daThanhToan) {
		DaThanhToan = daThanhToan;
	}
}

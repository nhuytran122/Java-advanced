package hoadonmodal;

import java.util.Date;

public class hoadon {
	private Long MaHoaDon;
	private String hoten;
	private Date NgayMua;
	private Long TongSoLuong;
	private Long ThanhTien;
	private boolean damua;
	public hoadon() {
		super();
	}
	
	public hoadon(Long maHoaDon, String hoten, Date ngayMua, Long tongSoLuong, Long thanhTien, boolean daMua) {
		super();
		MaHoaDon = maHoaDon;
		this.hoten = hoten;
		NgayMua = ngayMua;
		TongSoLuong = tongSoLuong;
		ThanhTien = thanhTien;
		damua = daMua;
	}

	public Long getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(Long maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public Date getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}

	public Long getTongSoLuong() {
		return TongSoLuong;
	}

	public void setTongSoLuong(Long tongSoLuong) {
		TongSoLuong = tongSoLuong;
	}

	public Long getThanhTien() {
		return ThanhTien;
	}

	public void setThanhTien(Long thanhTien) {
		ThanhTien = thanhTien;
	}

	public boolean isDamua() {
		return damua;
	}

	public void setDamua(boolean damua) {
		this.damua = damua;
	}
}

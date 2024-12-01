package hoadonmodal;

import java.util.Date;

public class hoadon {
	private Long MaHoaDon;
	private Long makh;
	private String hoten;
	private Date NgayMua;
	private boolean damua;
	public hoadon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public hoadon(Long maHoaDon, Long makh, String hoten, Date ngayMua, boolean damua) {
		super();
		MaHoaDon = maHoaDon;
		this.makh = makh;
		this.hoten = hoten;
		NgayMua = ngayMua;
		this.damua = damua;
	}
	public Long getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(Long maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public Long getMakh() {
		return makh;
	}
	public void setMakh(Long makh) {
		this.makh = makh;
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
	public boolean isDamua() {
		return damua;
	}
	public void setDamua(boolean damua) {
		this.damua = damua;
	}
}

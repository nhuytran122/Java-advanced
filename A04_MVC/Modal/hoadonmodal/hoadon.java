package hoadonmodal;

import java.util.Date;

public class hoadon {
    private long MaHoaDon;
    private long makh;
    private Date NgayMua;
    private boolean damua;
	public hoadon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public hoadon(long maHoaDon, long makh, Date ngayMua, boolean damua) {
		super();
		MaHoaDon = maHoaDon;
		this.makh = makh;
		NgayMua = ngayMua;
		this.damua = damua;
	}
	public long getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(long maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public long getMakh() {
		return makh;
	}
	public void setMakh(long makh) {
		this.makh = makh;
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

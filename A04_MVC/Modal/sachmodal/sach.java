package sachmodal;

import java.util.Date;

public class sach {
	private String masach;
	private String tensach;
	private String tacgia;
	private Long soluong;
	private Long gia;
	private String anh;
	private String maloai;
	private String sotap;
	private Date ngaynhap;
	public sach() {
		super();
	}
	public sach(String masach, String tensach, String tacgia, Long soluong, Long gia, String anh, String maloai, String sotap, Date ngaynhap) {
		super();
		this.masach = masach;
		this.tensach = tensach;
		this.tacgia = tacgia;
		this.soluong = soluong;
		this.gia = gia;
		this.anh = anh;
		this.maloai = maloai;
		this.sotap = sotap;
		this.ngaynhap = ngaynhap;
	}
	public String getMasach() {
		return masach;
	}
	public void setMasach(String masach) {
		this.masach = masach;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public String getTacgia() {
		return tacgia;
	}
	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}
	public Long getSoluong() {
		return soluong;
	}
	public void setSoluong(Long soluong) {
		this.soluong = soluong;
	}
	public Long getGia() {
		return gia;
	}
	public void setGia(Long gia) {
		this.gia = gia;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	public void setSoTap(String sotap) {
		this.sotap = sotap;
	}
	public String getSoTap() {
		return this.sotap;
	}
	public Date getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
}

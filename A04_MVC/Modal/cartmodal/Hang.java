package cartmodal;

import sachmodal.sach;

public class Hang {
	private sach sach;
	private int soluong;
	private Long thanhtien;
	public Hang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hang(sachmodal.sach sach, int soluong) {
		super();
		this.sach = sach;
		this.soluong = soluong;
		this.thanhtien=sach.getGia()*soluong;
	}
	public sach getSach() {
		return sach;
	}
	public void setSach(sach sach) {
		this.sach = sach;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public Long getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(Long thanhtien) {
		this.thanhtien = thanhtien;
	}
}

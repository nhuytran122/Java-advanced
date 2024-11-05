package cartmodal;

public class Hang {
    private String masach;
    private String tensach;
    private Long gia;
    private Long soluong;
    private Long thanhtien;
	public Hang(String masach, String tensach, Long gia, Long soluong) {
		super();
		this.masach = masach;
		this.tensach = tensach;
		this.gia = gia;
		this.soluong = soluong;
		this.thanhtien=soluong*gia;
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
	public Long getGia() {
		return gia;
	}
	public void setGia(Long gia) {
		this.gia = gia;
	}
	public Long getSoluong() {
		return soluong;
	}
	public void setSoluong(Long soluong) {
		this.soluong = soluong;
	}
	public Long getThanhtien() {
		return soluong*gia;
	}
	public void setThanhtien(Long thanhtien) {
		this.thanhtien = thanhtien;
	}
}

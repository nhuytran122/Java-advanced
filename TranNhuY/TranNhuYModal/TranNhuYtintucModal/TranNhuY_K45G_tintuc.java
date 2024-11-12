package TranNhuYtintucModal;

import java.util.Date;

public class TranNhuY_K45G_tintuc {

	private String MaTin;
	private String TieuDe;
	private Date NgayDuaTin;
	private String TomTat;
	private String NoiDung;
	private String MaLoai;
	
	public TranNhuY_K45G_tintuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TranNhuY_K45G_tintuc(String maTin, String tieuDe, Date ngayDuaTin, String tomTat, String noiDung,
			String maLoai) {
		super();
		MaTin = maTin;
		TieuDe = tieuDe;
		NgayDuaTin = ngayDuaTin;
		TomTat = tomTat;
		NoiDung = noiDung;
		MaLoai = maLoai;
	}
	public String getMaTin() {
		return MaTin;
	}
	public void setMaTin(String maTin) {
		MaTin = maTin;
	}
	public String getTieuDe() {
		return TieuDe;
	}
	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}
	public Date getNgayDuaTin() {
		return NgayDuaTin;
	}
	public void setNgayDuaTin(Date ngayDuaTin) {
		NgayDuaTin = ngayDuaTin;
	}
	public String getTomTat() {
		return TomTat;
	}
	public void setTomTat(String tomTat) {
		TomTat = tomTat;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public String getMaLoai() {
		return MaLoai;
	}
	public void setMaLoai(String maLoai) {
		MaLoai = maLoai;
	}
}

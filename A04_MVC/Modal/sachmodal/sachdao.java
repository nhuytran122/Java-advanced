package sachmodal;

import java.util.ArrayList;

public class sachdao {
	public ArrayList<sach> getSach(){
		ArrayList<sach> ds = new ArrayList<sach>(); 
		//String masach, String tensach, String tacgia, Long soluong, Long gia, String anh, String maloai
		ds.add(new sach("s1", "CSDL1", "Le Nam", (long)0, (long)1000, "image_sach/b1.jpg", "cntt"));
		ds.add(new sach("s2", "CSDL2", "Phan Nam", (long)0, (long)1000, "image_sach/b2.jpg", "cntt"));
		ds.add(new sach("s3", "Vat Ly", "Le Nam", (long)0, (long)1000, "image_sach/b3.jpg", "ly"));
		ds.add(new sach("s4", "Giai tinh", "Le Nam", (long)0, (long)1000, "image_sach/b4.jpg", "ly"));
		ds.add(new sach("s5", "Dai So", "Le Nam", (long)0, (long)1000, "image_sach/b5.jpg", "ly"));
		ds.add(new sach("s6", "Chinh tri", "Le Nam", (long)0, (long)1000, "image_sach/b6.jpg", "toan"));
		ds.add(new sach("s7", "Chat ran", "Le Nam", (long)0, (long)1000, "image_sach/b7.jpg", "toan"));
		return ds;
	}
}

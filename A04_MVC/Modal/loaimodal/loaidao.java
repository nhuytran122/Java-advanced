package loaimodal;

import java.util.ArrayList;

public class loaidao {
	public ArrayList<loai> getLoai(){
		ArrayList<loai> ds = new ArrayList<loai>();
		ds.add(new loai("ct", "Chính trị"));
		ds.add(new loai("cntt", "Công nghệ thông tin"));
		ds.add(new loai("toan", "Sách Toán"));
		ds.add(new loai("ly", "Vật Lý"));
		ds.add(new loai("sinh", "Công nghệ sinh học"));
		return ds;
	}
}

package loaimodal;

import java.util.ArrayList;

public class loaibo {
	loaidao ldao = new loaidao();
	public ArrayList<loai> getLoai(){
		return ldao.getLoai();	
	}
}

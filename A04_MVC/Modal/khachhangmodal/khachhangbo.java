package khachhangmodal;

import java.util.ArrayList;

public class khachhangbo {
	khachhangdao khdao = new khachhangdao();
	ArrayList<khachhang> ds;
	public ArrayList<khachhang> getKH() throws Exception{
		ds = khdao.getKH();
		return ds;
	}
	
	public khachhang checkLogin(String tenDn, String Pass) throws Exception{
		return khdao.checkLogin(tenDn, Pass);
	}
}

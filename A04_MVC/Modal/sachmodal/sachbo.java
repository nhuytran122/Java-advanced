package sachmodal;

import java.util.ArrayList;

public class sachbo {
	sachdao sdao = new sachdao();
	ArrayList<sach> ds;
	
	public ArrayList<sach> getListSach(int page, int pageSize, String searchValue) throws Exception{
		ds =  sdao.getListSach(page, pageSize, searchValue);
		return ds;
	}
	
	public int getRowCount(String searchValue) throws Exception {
		return sdao.getRowCount(searchValue);
	}
	
	public int addSach(sach s) throws Exception {
		return sdao.addSach(s);
	}
	
	public int updateSach(sach s) throws Exception {
		return sdao.updateSach(s);
	}
	
	public int deleteSach(String masach) throws Exception {
		return sdao.deleteSach(masach);
	}
}
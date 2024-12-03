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
	
	public int addSach(String masach, String tensach, String tacgia, 
			Long soluong, Long gia, String anh, String maloai, String sotap) throws Exception {
		return sdao.addSach(masach, tensach, tacgia, soluong, gia, anh, maloai, sotap);
	}
	
	public int updateSach(String masach, String tensach, String tacgia, 
			Long soluong, Long gia, String anh, String maloai, String sotap) throws Exception {
		return sdao.updateSach(masach, tensach, tacgia, soluong, gia, anh, maloai, sotap);
	}
	
	public int deleteSach(String masach) throws Exception {
		return sdao.deleteSach(masach);
	}
	
	public sach getSach(String masach) throws Exception {
		return sdao.getSach(masach);
	}
	
	public boolean inUsedSach(String masach) throws Exception {
		return sdao.inUsedSach(masach);
	}
}
package loaimodal;

import java.util.ArrayList;

public class loaibo {
	loaidao ldao = new loaidao();
	
	public ArrayList<loai> getListLoai() throws Exception{
		return ldao.getListLoai();	
	}
	
	public ArrayList<loai> getListLoai(int page, int pageSize, String searchValue) throws Exception{
		return ldao.getListLoai(page, pageSize, searchValue);
	}
	
	public int getRowCount(String searchValue) throws Exception {
		return ldao.getRowCount(searchValue);
	}
	
	public loai getLoai(String maloai) throws Exception {
		return ldao.getLoai(maloai);
	}
	
	public int addLoai(loai l) throws Exception {
		return ldao.addLoai(l);
	}
	
	public int updateLoai(loai l) throws Exception {
		return ldao.updateLoai(l);
	}
	
	public int deleteLoai(String maloai) throws Exception {
		return ldao.deleteLoai(maloai);
	}
	
	public boolean inUsedLoai(String maloai) throws Exception {
		return ldao.inUsedLoai(maloai);
	}
}

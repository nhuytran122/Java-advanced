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
	
	public int addLoai(String maLoai, String tenLoai) throws Exception {
		return ldao.addLoai(maLoai, tenLoai);
	}
	
	public int updateLoai(String maLoai, String tenLoai) throws Exception {
		return ldao.updateLoai(maLoai, tenLoai);
	}
	
	public int deleteLoai(String maloai) throws Exception {
		return ldao.deleteLoai(maloai);
	}
	
	public boolean inUsedLoai(String maloai) throws Exception {
		return ldao.inUsedLoai(maloai);
	}
}

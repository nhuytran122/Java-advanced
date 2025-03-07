package loaimodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class loaidao {
	
	public ArrayList<loai> getListLoai() throws Exception{
		ArrayList<loai> ds = new ArrayList<loai>();
		// In:
		//B1: Ket noi vao CSDL
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		//B2: Tao cau lenh SQL
		String sql = "select * from loai";
		//B3: Tao cau lenh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		//B4: Thuc hien cau lenh
		ResultSet rs = cmd.executeQuery();
		//B5: Duyet qua rs
		while(rs.next()) {
			String maloai = rs.getString("maloai");
			String tenloai = rs.getString("tenloai");
			ds.add(new loai(maloai, tenloai));
		} 
		rs.close();
		kn.cn.close();
		return ds;
	}
	
	public ArrayList<loai> getListLoai(int page, int pageSize, String searchValue) throws Exception {
	    ArrayList<loai> ds = new ArrayList<loai>();
	    searchValue = "%" + searchValue + "%";
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    int offset = (page - 1) * pageSize;

	    String sql = "SELECT * " +
	                 "FROM loai " +
	                 "WHERE tenloai LIKE ? " +
	                 "ORDER BY tenloai " +
	                 "OFFSET ? ROWS " +         
	                 "FETCH NEXT ? ROWS ONLY";  

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, searchValue);   
	    cmd.setInt(2, offset);          
	    cmd.setInt(3, pageSize);         

	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        loai s = new loai();
	        s.setMaloai(rs.getString("maloai"));
	        s.setTenloai(rs.getString("tenloai"));
	        ds.add(s);
	    }
	    return ds;
	}

	
	public int getRowCount(String searchValue) throws Exception {
	    int count = 0;
	    searchValue = "%" + searchValue + "%"; 
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(*) FROM loai WHERE tenloai LIKE ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, searchValue);
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }
	    return count;
	}
	
	public boolean checkLoaiExists(String maLoai) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT 1 FROM loai WHERE maloai = ?";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, maLoai); 
	    ResultSet rs = cmd.executeQuery();
	    
	    if (rs.next()) {
	        return true; 
	    }
	    return false;
	}

	
	public int addLoai(String maLoai, String tenLoai) throws Exception {
	    if (checkLoaiExists(maLoai)) {
	        return -1;
	    }
	    
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "INSERT INTO loai (maloai, tenloai) VALUES (?, ?)";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, maLoai);
	    cmd.setString(2, tenLoai);
	    
	    int kq = cmd.executeUpdate();
	    
	    return kq;
	}



	
	public int updateLoai(String maLoai, String tenLoai) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE loai SET tenloai = ? WHERE maloai = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, tenLoai);  
	    cmd.setString(2, maLoai);  
	    int kq = cmd.executeUpdate();
	    return kq;  
	}
	
	public int deleteLoai(String maloai) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "DELETE FROM loai WHERE maloai = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, maloai);  
	    int kq = cmd.executeUpdate();
	    return kq;  
	}
	
	public loai getLoai(String maloai) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "SELECT * FROM loai WHERE maloai = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, maloai);
	    
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        String ml = rs.getString("maloai");
	        String tenloai = rs.getString("tenloai");
	        return new loai(ml, tenloai);
	    }
	    return null;
	}


}
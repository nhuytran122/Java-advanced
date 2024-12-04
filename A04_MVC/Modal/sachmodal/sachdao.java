package sachmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ketnoimodal.KetNoi;

public class sachdao {
	
	public ArrayList<sach> getListSach(int page, int pageSize, String searchValue) throws Exception {
        ArrayList<sach> ds = new ArrayList<sach>();
        searchValue = "%" + searchValue + "%";
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        /*String sql = "SELECT * FROM ("
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY tensach) AS RowNumber "
                + "    FROM sach "
                + "    WHERE tensach LIKE ?  OR tacgia LIKE ? OR maloai = ?"
                + ") AS t "
                + "WHERE (? = 0) OR (RowNumber BETWEEN (? - 1) * ? + 1 AND ? * ?) "
                + "ORDER BY RowNumber";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, searchValue); 
        cmd.setString(2, searchValue); 
        cmd.setString(3, searchValue.replace("%", ""));
        cmd.setInt(4, pageSize);       
        cmd.setInt(5, page);            
        cmd.setInt(6, pageSize);        
        cmd.setInt(7, page);            
        cmd.setInt(8, pageSize);  */
        String sql = "SELECT * " +
                "FROM sach " +
                "WHERE tensach LIKE ? OR tacgia LIKE ? OR maloai = ? " +
                "ORDER BY tensach " +
                "OFFSET (? - 1) * ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";

	   PreparedStatement cmd = kn.cn.prepareStatement(sql);
	   cmd.setString(1, searchValue);            
	   cmd.setString(2, searchValue);           
	   cmd.setString(3, searchValue.replace("%", "")); 
	   cmd.setInt(4, page);                      
	   cmd.setInt(5, pageSize);                  
	   cmd.setInt(6, pageSize);                  

       	ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
        	String ms = rs.getString("masach");
	        String ten = rs.getString("tensach");
	        String tg = rs.getString("tacgia");
	        long sl = rs.getLong("soluong");
	        long gia = rs.getLong("gia");
	        String anh = rs.getString("anh");
	        String maloai = rs.getString("maloai");
	        String sotap = rs.getString("sotap");
	        Date ngaynhap = rs.getDate("NgayNhap");
	        ds.add(new sach(ms, ten, tg, sl, gia, anh, maloai, sotap, ngaynhap));
        }
        return ds;
    }
	
	public int getRowCount(String searchValue) throws Exception {
	    int count = 0;
	    searchValue = "%" + searchValue + "%"; 
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(*) FROM sach WHERE tensach LIKE ? OR tacgia LIKE ? OR maloai = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, searchValue);
	    cmd.setString(2, searchValue);
	    cmd.setString(3, searchValue.replace("%", "")); 
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }
	    return count;
	}
	
	public boolean checkSachExists(String masach) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "SELECT 1 FROM sach WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, masach);
	    
	    ResultSet rs = cmd.executeQuery();
	    
	    if (rs.next()) {
	        return true; 
	    }
	    
	    return false;
	}

	
	public int addSach(String masach, String tensach, String tacgia, 
			Long soluong, Long gia, String anh, String maloai, String sotap) throws Exception {
	    if (checkSachExists(masach)) {
	        return -1;
	    }

	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "INSERT INTO sach (masach, tensach, tacgia, soluong, gia, anh, maloai, sotap, NgayNhap) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE());";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, masach);
	    cmd.setString(2, tensach);
	    cmd.setString(3, tacgia);
	    cmd.setLong(4, soluong);
	    cmd.setLong(5, gia);
	    cmd.setString(6, anh);
	    cmd.setString(7, maloai);
	    cmd.setString(8, sotap);
//	    cmd.setDate(9, new java.sql.Date(ngaynhap.getTime()));
	    
	    int kq = cmd.executeUpdate();
	    
	    if (kq > 0) {
	        return 1; 
	    }
	    return -1;
	}


	
	public int updateSach(String masach, String tensach, String tacgia, Long soluong,
			Long gia, String anh, String maloai, String sotap) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "UPDATE sach " +
	             "SET tensach = ?, tacgia = ?, soluong = ?, gia = ?, " +
	             "anh = ?, maloai = ?, sotap = ? " + 
	             "WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, tensach);   
	    cmd.setString(2, tacgia);    
	    cmd.setLong(3, soluong);    
	    cmd.setLong(4, gia);      
	    cmd.setString(5, anh);     
	    cmd.setString(6, maloai);   
	    cmd.setString(7, sotap);
//	    cmd.setDate(8, new java.sql.Date(ngaynhap.getTime()));
	    cmd.setString(8, masach);   
	    
	    int kq = cmd.executeUpdate();
	    return kq;  
	}
	
	public int deleteSach(String masach) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "DELETE FROM sach WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, masach);  
	    int kq = cmd.executeUpdate();
	    return kq; 
	}
	
	public sach getSach(String masach) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    
	    String sql = "SELECT * FROM sach WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, masach);
	    
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        String ms = rs.getString("masach");
	        String ten = rs.getString("tensach");
	        String tg = rs.getString("tacgia");
	        long sl = rs.getLong("soluong");
	        long gia = rs.getLong("gia");
	        String anh = rs.getString("anh");
	        String maloai = rs.getString("maloai");
	        String sotap = rs.getString("sotap");
	        Date ngaynhap = rs.getDate("NgayNhap");
	        
	        return new sach(ms, ten, tg, sl, gia, anh, maloai, sotap, ngaynhap);
	    }
	    return null;
	}
	
	public boolean inUsedSach(String masach) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "IF EXISTS (SELECT * FROM ChiTietHoaDon WHERE MaSach = ?) "
	               + "SELECT 1 "
	               + "ELSE "
	               + "SELECT 0";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, masach);
	    ResultSet rs = cmd.executeQuery();
	    
	    int result = 0;
	    if (rs.next()) {
	        result = rs.getInt(1);
	    }
	    rs.close();
	    kn.cn.close();
	    return result > 0;
	}

}
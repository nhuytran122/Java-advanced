package sachmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        	sach s = new sach();
            s.setMasach(rs.getString("masach"));
            s.setTensach(rs.getString("tensach"));
            s.setTacgia(rs.getString("tacgia"));
            s.setSoluong(rs.getLong("soluong"));
            s.setGia(rs.getLong("gia"));
            s.setAnh(rs.getString("anh"));
            s.setMaloai(rs.getString("maloai"));
            ds.add(s);
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

}
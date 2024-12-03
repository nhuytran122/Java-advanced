package khachhangmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class khachhangdao {
	
	public ArrayList<khachhang> getKHByCondition(int page, int pageSize, String searchValue) throws Exception {
	    ArrayList<khachhang> ds = new ArrayList<khachhang>();
	    searchValue = "%" + searchValue + "%";
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT * " +
	                 "FROM KhachHang " +
	                 "WHERE hoten LIKE ? OR email LIKE ? " +
	                 "ORDER BY makh " +
	                 "OFFSET (? - 1) * ? ROWS " +
	                 "FETCH NEXT ? ROWS ONLY";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, searchValue); 
	    cmd.setString(2, searchValue);
	    cmd.setInt(3, page);         
	    cmd.setInt(4, pageSize);     
	    cmd.setInt(5, pageSize);      

	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        Long makh = rs.getLong("makh");
	        String hoten = rs.getString("hoten");
	        String diachi = rs.getString("diachi");
	        String sodt = rs.getString("sodt");
	        String email = rs.getString("email");
	        String tendn = rs.getString("tendn");
	        String pass = rs.getString("pass");
	        ds.add(new khachhang(makh, hoten, diachi, sodt, email, tendn, pass));
	    }
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return ds;
	}

	public int countKHWithSearch(String searchValue) throws Exception {
	    int count = 0;
	    searchValue = "%" + searchValue + "%";
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT COUNT(*) " +
	                 "FROM KhachHang " +
	                 "WHERE hoten LIKE ? OR diachi LIKE ? OR email LIKE ? ";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, searchValue);
	    cmd.setString(2, searchValue);
	    cmd.setString(3, searchValue);

	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return count;
	}

	public khachhang checkLogin(String tenDn, String Pass) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM KhachHang WHERE tendn = ? AND pass = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, tenDn);
	    cmd.setString(2, Pass);
	    ResultSet rs = cmd.executeQuery();

	    khachhang kh = null;
	    if (rs.next()) {
	        Long makh = rs.getLong("makh");
	        String hoten = rs.getString("hoten");
	        String diachi = rs.getString("diachi");
	        String sodt = rs.getString("sodt");
	        String email = rs.getString("email");
	        String tendn = rs.getString("tendn");
	        String pass = rs.getString("pass");
	        kh = new khachhang(makh, hoten, diachi, sodt, email, tendn, pass);
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return kh;
	}
	
	public int themKH(String hoten, String diachi, String sodt, String email, String tendn, String pass) throws Exception {
        if (checkTendnExists(tendn)) {
        	return -1;
        }
		KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO KhachHang (hoten, diachi, sodt, email, tendn, pass) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
            
            cmd.setString(1, hoten);
            cmd.setString(2, diachi);
            cmd.setString(3, sodt);
            cmd.setString(4, email);
            cmd.setString(5, tendn);
            cmd.setString(6, pass);

        int kq = cmd.executeUpdate();
        kn.cn.close();
            
        return kq;
    }
	
	public int countAllKH() throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(makh) FROM KhachHang";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    ResultSet rs = cmd.executeQuery();

	    int count = 0;
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return count;
	}
	
	public boolean checkTendnExists(String tendn) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT 1 FROM KhachHang WHERE tendn = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, tendn);
	    ResultSet rs = cmd.executeQuery();

	    boolean exists = rs.next();
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return exists;
	}


}

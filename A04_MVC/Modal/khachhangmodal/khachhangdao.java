package khachhangmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class khachhangdao {
	
	public ArrayList<khachhang> getKH() throws Exception{
		ArrayList<khachhang> ds = new ArrayList<khachhang>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from KhachHang";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
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
		kn.cn.close();
		return ds;
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




}

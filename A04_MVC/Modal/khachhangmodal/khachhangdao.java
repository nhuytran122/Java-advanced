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
	
	public khachhang themKH(String hoten, String diachi, String sodt, String email, String tendn, String pass) throws Exception {
        long maKH = -1; 
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO KhachHang (hoten, diachi, sodt, email, tendn, pass) VALUES (?, ?, ?, ?, ?, ?);";
             PreparedStatement cmd = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            cmd.setString(1, hoten);
            cmd.setString(2, diachi);
            cmd.setString(3, sodt);
            cmd.setString(4, email);
            cmd.setString(5, tendn);
            cmd.setString(6, pass);

            int affectedRows = cmd.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = cmd.getGeneratedKeys();
                    if (rs.next()) {
                        maKH = rs.getLong(1);
                    }
            }
            khachhang kh = new khachhang(maKH, hoten, diachi, sodt, email, tendn, pass);
        kn.cn.close();
            
        return kh;
    }
}

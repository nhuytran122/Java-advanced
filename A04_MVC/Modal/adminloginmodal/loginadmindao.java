package adminloginmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ketnoimodal.KetNoi;

public class loginadmindao {
	public String checkLogin(String tenDn, String Pass) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM DangNhap WHERE TenDangNhap = ? AND MatKhau = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, tenDn);
	    cmd.setString(2, Pass);
	    ResultSet rs = cmd.executeQuery();
	    String kt = null;
	    if (rs.next()) {
	        kt = tenDn;
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return kt;
	}
}

package lichsumodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ketnoimodal.KetNoi;

public class lichsudao {
	
	public ArrayList<lichsu> getLichsu(long maKH) throws Exception {
		ArrayList<lichsu> ds = new ArrayList<lichsu>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "select * from Vlichsu where makh = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, maKH);
	    ResultSet rs = cmd.executeQuery();

	    while(rs.next()) {
	        Long makh = rs.getLong("makh");
	        String tensach = rs.getString("tensach");
	        Long SoLuongMua = rs.getLong("SoLuongMua");
	        Long gia = rs.getLong("gia");
	        Long ThanhTien = rs.getLong("ThanhTien");
	        Date NgayMua = rs.getDate("NgayMua");
	        boolean damua = rs.getBoolean("damua");

	        ds.add(new lichsu(makh, tensach, SoLuongMua, gia, ThanhTien, NgayMua, damua));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return ds;
	}
}

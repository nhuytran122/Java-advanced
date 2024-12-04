package lichsumodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ketnoimodal.KetNoi;

public class lichsudao {
	
	public ArrayList<lichsu> getLichsu(long maKH, boolean flagPurchased) throws Exception {
		ArrayList<lichsu> ds = new ArrayList<lichsu>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "";
	    if(flagPurchased) {
	    	sql = "select * from Vlichsu where makh = ? AND DaThanhToan = 1";
	    }
	    else {
	    	sql = "select * from Vlichsu where makh = ? AND DaThanhToan = 0";
	    }
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, maKH);
	    ResultSet rs = cmd.executeQuery();

	    while(rs.next()) {
	    	ds.add(mapLichsu(rs));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return ds;
	}
	
	public ArrayList<lichsu> getCTHDByOrderID(long maHD) throws Exception {
		ArrayList<lichsu> ds = new ArrayList<lichsu>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "select * from Vlichsu where MaHoaDon = ?";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, maHD);
	    ResultSet rs = cmd.executeQuery();

	    while(rs.next()) {
	    	ds.add(mapLichsu(rs));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return ds;
	}
	
    private lichsu mapLichsu(ResultSet rs) throws Exception {
        Long makh = rs.getLong("makh");
        String tensach = rs.getString("tensach");
        Long SoLuongMua = rs.getLong("SoLuongMua");
        Long gia = rs.getLong("gia");
        Long ThanhTien = rs.getLong("ThanhTien");
        Date NgayMua = rs.getDate("NgayMua");
        Long maCTHD = rs.getLong("MaChiTietHD");
        Long maHD = rs.getLong("MaHoaDon");
        Boolean DaThanhToan = rs.getBoolean("DaThanhToan");
        
        return new lichsu(makh, tensach, SoLuongMua, gia, ThanhTien, NgayMua, maCTHD, maHD, DaThanhToan);
    }
}

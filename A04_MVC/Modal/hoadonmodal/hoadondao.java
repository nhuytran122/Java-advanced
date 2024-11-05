package hoadonmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ketnoimodal.KetNoi;

public class hoadondao {
	public int themHoaDon(long maKh) throws Exception {
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO hoadon (makh, NgayMua, damua) VALUES (?, GETDATE(), 0)";
            PreparedStatement cmd = kn.cn.prepareStatement(sql);
            cmd.setLong(1, maKh);

        int kq = cmd.executeUpdate();
        kn.cn.close();
            
        return kq;
    }
	
	public long getMaxHD() throws Exception {
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "select max(MaHoaDon) as MaxHD from hoadon";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        ResultSet rs = cmd.executeQuery();
        long max = 0;
        if(rs.next())
        	max = rs.getLong("MaxHD");
        cmd.close();
        kn.cn.close();
            
        return max;
    }	
}

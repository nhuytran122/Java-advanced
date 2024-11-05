package chitiethoadonmodal;

import java.sql.PreparedStatement;

import ketnoimodal.KetNoi;

public class CTHDdao {
	
	public long themCTHD(String maSach, long  slm, long MaHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon) VALUES (?, ?, ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, maSach);
        cmd.setLong(2, slm);
        cmd.setLong(3, MaHD);

        long kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
}

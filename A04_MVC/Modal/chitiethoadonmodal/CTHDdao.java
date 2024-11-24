package chitiethoadonmodal;

import java.sql.PreparedStatement;

import ketnoimodal.KetNoi;

public class CTHDdao {
	
	public long themCTHD(String maSach, long  slm, long MaHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon, DaThanhToan) VALUES (?, ?, ?, 0)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, maSach);
        cmd.setLong(2, slm);
        cmd.setLong(3, MaHD);

        long kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
	
	public long thanhtoanCTHD(long MaCTHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "UPDATE ChiTietHoaDon SET DaThanhToan = 1 WHERE MaChiTietHD = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, MaCTHD);
        long kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
	
	public long thanhtoanAll(long makh) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE ChiTietHoaDon " +
                "SET DaThanhToan = 1 " +
                "FROM ChiTietHoaDon INNER JOIN hoadon ON ChiTietHoaDon.MaHoaDon = hoadon.MaHoaDon " +
                "WHERE hoadon.makh = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, makh);
	    long kq = cmd.executeUpdate();
	    kn.cn.close();
	    return kq;
	}
	
	public long xoaCTHD(long MaCTHD) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "DELETE FROM ChiTietHoaDon WHERE MaChiTietHD = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, MaCTHD);
	    long kq = cmd.executeUpdate(); 
	    kn.cn.close();
	    return kq;
	}
	
	public long xoaAllCTHDNotPay(long makh) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "DELETE ChiTietHoaDon " +
	                 "FROM ChiTietHoaDon " +
	                 "INNER JOIN hoadon ON ChiTietHoaDon.MaHoaDon = hoadon.MaHoaDon " +
	                 "WHERE ChiTietHoaDon.DaThanhToan = 0 AND hoadon.makh = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, makh);
	    long kq = cmd.executeUpdate();
	    kn.cn.close();
	    return kq;
	}

}

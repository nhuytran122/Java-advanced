package chitiethoadonmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class CTHDdao {
	
    public ArrayList<CTHD> getListHoaDon(long maHD) throws Exception {
    	KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maHD);

        ResultSet rs = cmd.executeQuery();
        ArrayList<CTHD> listCTHD = new ArrayList<CTHD>();
        while (rs.next()) {
            Long MaCTHD =  rs.getLong("MaChiTietHD");
            String masach = rs.getString("MaSach");
            int slm = rs.getInt("SoLuongMua");
            CTHD cthd = new CTHD(MaCTHD, masach, slm, maHD);
            listCTHD.add(cthd);
        }
        rs.close();
        kn.cn.close();
        return listCTHD;
    }
    
	public long themCTHD(String maSach, int  slm, long maHD) throws Exception {
        long maCTHD = -1; 
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO chitiethoadon (MaSach, SoLuongMua, MaHoaDon) VALUES (?, ?, ?);";
             PreparedStatement cmd = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            cmd.setString(1, maSach);
            cmd.setInt(2, slm);
            cmd.setLong(3, maHD);

            int affectedRows = cmd.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = cmd.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        maCTHD = generatedKeys.getLong(1);
                    }
            }
        kn.cn.close();
        return maCTHD;
    }
}

package hoadonmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ketnoimodal.KetNoi;


public class hoadondao {

    public ArrayList<hoadon> getListHoaDon(long maKh) throws Exception {
    	KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "SELECT * FROM hoadon WHERE makh = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maKh);

        ResultSet rs = cmd.executeQuery();
        ArrayList<hoadon> listHoaDon = new ArrayList<hoadon>();
        while (rs.next()) {
            hoadon hoaDon = new hoadon();
            hoaDon.setMaHoaDon(rs.getLong("MaHoaDon"));
            hoaDon.setMakh(rs.getLong("makh"));
            hoaDon.setNgayMua(rs.getDate("NgayMua"));
            hoaDon.setDamua(rs.getBoolean("damua"));
            listHoaDon.add(hoaDon);
        }
        rs.close();
        kn.cn.close();
        return listHoaDon;
    }
    
	public long themHoaDon(long maKh) throws Exception {
        long maHoaDon = -1; 
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO hoadon (makh, NgayMua, damua) VALUES (?, ?, ?);";
             PreparedStatement cmd = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            cmd.setLong(1, maKh);
            cmd.setDate(2, new java.sql.Date(new Date().getTime()));
            cmd.setBoolean(3, true);

            int affectedRows = cmd.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = cmd.getGeneratedKeys();
                    if (rs.next()) {
                        maHoaDon = rs.getLong(1);
                    }
            }
        kn.cn.close();
            
        return maHoaDon;
    }
}

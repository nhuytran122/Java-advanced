package TranNhuYthongkeModal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import TranNhuYketnoimodal.TranNhuY_K45G_KetNoi;

public class TranNhuY_K45G_thongkedao {
    public ArrayList<TranNhuY_K45G_thongke> thongKeSoLuongTinTheoLoai() throws Exception {
        ArrayList<TranNhuY_K45G_thongke> dsThongKe = new ArrayList<TranNhuY_K45G_thongke>();
        TranNhuY_K45G_KetNoi kn = new TranNhuY_K45G_KetNoi();
        kn.ketnoi();
        
        String sql = "SELECT l.MaLoai, l.TenLoai, COUNT(t.MaTin) AS SoLuongTin "
                   + "FROM tbl_LoaiTin l "
                   + "LEFT JOIN tbl_TinTuc t ON l.MaLoai = t.MaLoai "
                   + "GROUP BY l.MaLoai, l.TenLoai";

        PreparedStatement statement = kn.cn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String maLoai = rs.getString("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                int soLuongTin = rs.getInt("SoLuongTin");

                TranNhuY_K45G_thongke thongKe = new TranNhuY_K45G_thongke(maLoai, tenLoai, soLuongTin);
                dsThongKe.add(thongKe);
            }
        return dsThongKe;
    }
}

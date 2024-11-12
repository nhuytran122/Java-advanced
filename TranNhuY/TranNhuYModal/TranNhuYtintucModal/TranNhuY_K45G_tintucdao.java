package TranNhuYtintucModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import TranNhuYketnoimodal.TranNhuY_K45G_KetNoi;

public class TranNhuY_K45G_tintucdao {
	public ArrayList<TranNhuY_K45G_tintuc> getFullTin() throws Exception {
        ArrayList<TranNhuY_K45G_tintuc> ds = new ArrayList<TranNhuY_K45G_tintuc>();
        TranNhuY_K45G_KetNoi kn = new TranNhuY_K45G_KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_TinTuc";

	   PreparedStatement cmd = kn.cn.prepareStatement(sql);              

       	ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
        	String maTin = rs.getString("MaTin");
        	String TieuDe = rs.getString("TieuDe");
        	Date NgayDuaTin = rs.getDate("NgayDuaTin");
        	String Tomtat = rs.getString("TomTat");
        	String NoiDung = rs.getString("NoiDung");
        	String MaLoai = rs.getString("MaLoai");
        	
            ds.add(new TranNhuY_K45G_tintuc(maTin, TieuDe, NgayDuaTin, Tomtat, NoiDung, MaLoai));
        }
        rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
    }
	
	public ArrayList<TranNhuY_K45G_tintuc> searchTin(String searchValue) throws Exception {
        ArrayList<TranNhuY_K45G_tintuc> ds = new ArrayList<TranNhuY_K45G_tintuc>();
        searchValue = "%" + searchValue + "%";
        TranNhuY_K45G_KetNoi kn = new TranNhuY_K45G_KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_TinTuc WHERE TieuDe like ? OR TomTat like ?";

	   PreparedStatement cmd = kn.cn.prepareStatement(sql);       
        cmd.setString(1, searchValue);            
 	   	cmd.setString(2, searchValue);  
 	   ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
        	String maTin = rs.getString("MaTin");
        	String TieuDe = rs.getString("TieuDe");
        	Date NgayDuaTin = rs.getDate("NgayDuaTin");
        	String Tomtat = rs.getString("TomTat");
        	String NoiDung = rs.getString("NoiDung");
        	String MaLoai = rs.getString("MaLoai");
        	
            ds.add(new TranNhuY_K45G_tintuc(maTin, TieuDe, NgayDuaTin, Tomtat, NoiDung, MaLoai));
        }
        rs.close();
		cmd.close();
		kn.cn.close();
		return ds;
    }
	
}

package sachmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class sachdao {
	
	public ArrayList<sach> getSach() throws Exception{
			ArrayList<sach> ds = new ArrayList<sach>();
			KetNoi kn = new KetNoi();
			kn.ketnoi();
			String sql = "select * from sach";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while(rs.next()) {
				String masach = rs.getString("masach");
				String tensach = rs.getString("tensach");
				String tacgia = rs.getString("tacgia");
				Long soluong = rs.getLong("soluong");
				Long gia = rs.getLong("gia");
				String maloai = rs.getString("maloai");
				String anh = rs.getString("anh");
				ds.add(new sach(masach, tensach, tacgia, soluong, gia, anh, maloai));
			} 
			rs.close();
			kn.cn.close();
			return ds;
	}
}
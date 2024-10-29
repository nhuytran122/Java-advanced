package loaimodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ketnoimodal.KetNoi;

public class loaidao {
	
	public ArrayList<loai> getLoai() throws Exception{
		ArrayList<loai> ds = new ArrayList<loai>();
		// In:
		//B1: Ket noi vao CSDL
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		//B2: Tao cau lenh SQL
		String sql = "select * from loai";
		//B3: Tao cau lenh
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		//B4: Thuc hien cau lenh
		ResultSet rs = cmd.executeQuery();
		//B5: Duyet qua rs
		while(rs.next()) {
			String maloai = rs.getString("maloai");
			String tenloai = rs.getString("tenloai");
			ds.add(new loai(maloai, tenloai));
		} 
		rs.close();
		kn.cn.close();
		return ds;
}
}
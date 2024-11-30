package MaterialModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class MaterialDao {
	public ArrayList<Material> getListMaterials() throws Exception{
		ArrayList<Material> ds = new ArrayList<Material>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from tbl_Materials";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			Long MaterialID = rs.getLong("MaterialID");
			String MaterialName = rs.getString("MaterialName");
			String Description = rs.getString("Description");
			ds.add(new Material(MaterialID, MaterialName, Description));
		} 
		rs.close();
		kn.cn.close();
		return ds;
	}
}

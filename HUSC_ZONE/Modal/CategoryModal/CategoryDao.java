package CategoryModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class CategoryDao {
	
	public ArrayList<Category> getListCategories() throws Exception{
		ArrayList<Category> ds = new ArrayList<Category>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from tbl_Categories";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			Long CategoryID = rs.getLong("CategoryID");
			String CategoryName = rs.getString("CategoryName");
			String Description = rs.getString("Description");
			ds.add(new Category(CategoryID, CategoryName, Description));
		} 
		rs.close();
		kn.cn.close();
		return ds;
	}
}

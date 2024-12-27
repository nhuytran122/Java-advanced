package CategoryModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class CategoryDao {

    private Category mapCategory(ResultSet rs) throws Exception {
        Long CategoryID = rs.getLong("CategoryID");
        String CategoryName = rs.getString("CategoryName");
        String Description = rs.getString("Description");
        String Image = rs.getString("Image");
        return new Category(CategoryID, CategoryName, Description, Image);
    }

    public ArrayList<Category> getListCategories() throws Exception {
        ArrayList<Category> ds = new ArrayList<Category>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Categories";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            ds.add(mapCategory(rs));
        }
        
        rs.close();
        kn.cn.close();
        return ds;
    }

    public Category getCategoryByID(Long cateID) throws Exception {
        Category category = null;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM tbl_Categories WHERE CategoryID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, cateID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            category = mapCategory(rs);
        }
        
        rs.close();
        kn.cn.close();
        return category;
    }
}

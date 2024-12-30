package CategoryModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class CategoryDao {

    private Category mapCategory(ResultSet rs) throws Exception {
        Long CategoryID = rs.getLong("CategoryID");
        String CategoryName = rs.getString("CategoryName");
        String Image = rs.getString("Image");
        return new Category(CategoryID, CategoryName, Image);
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
    
    public ArrayList<Category> getListCategoriesByCondition(int page, int pageSize, String searchValue) throws Exception {
        ArrayList<Category> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * " +
                     "FROM tbl_Categories " +
                     "WHERE CategoryName LIKE ? " +
                     "ORDER BY CategoryID " +
                     "OFFSET ? ROWS " +
                     "FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, "%" + searchValue + "%"); 
        cmd.setInt(2, offset); 
        cmd.setInt(3, pageSize); 
        
        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapCategory(rs));
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    
    public int countCategoriesByCondition(String searchValue) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(CategoryID) " +
	            "FROM tbl_Categories " +
	            "WHERE CategoryName LIKE ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, "%" + searchValue + "%");
	    ResultSet rs = cmd.executeQuery();
	    int count = 0;
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }
	    rs.close();
	    cmd.close();
	    kn.cn.close();

	    return count;
	}
    
    public int addCategory(String cateName, String img) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO tbl_Categories (CategoryName, Image) VALUES (?, ?)";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, cateName);
	    cmd.setString(2, img);
	    int kq = cmd.executeUpdate();
	    return kq;
	}
    
    public int updateCategory(Long cateID, String cateName, String img) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE tbl_Categories SET CategoryName = ?, Image = ? "
	    		+ "WHERE CategoryID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, cateName);  
	    cmd.setString(2, img);
	    cmd.setLong(3, cateID);
	    int kq = cmd.executeUpdate();
	    return kq;  
	}
    
    public int deleteCategory(Long cateID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "DELETE FROM tbl_Categories WHERE CategoryID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, cateID);
		int result = cmd.executeUpdate();

		cmd.close();
		kn.cn.close();
		return result;
	}
}

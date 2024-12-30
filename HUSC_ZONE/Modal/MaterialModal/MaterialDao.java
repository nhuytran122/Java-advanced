package MaterialModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class MaterialDao {

    private Material mapMaterial(ResultSet rs) throws Exception {
        Long MaterialID = rs.getLong("MaterialID");
        String MaterialName = rs.getString("MaterialName");
        String Description = rs.getString("Description");
        return new Material(MaterialID, MaterialName, Description);
    }

    public Material getMaterialByID(Long mateID) throws Exception {
        Material material = null;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM tbl_Materials WHERE MaterialID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, mateID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            material = mapMaterial(rs);
        }

        rs.close();
        kn.cn.close();
        return material;
    }
    
    //Để show ở nav, khi add docs
    public ArrayList<Material> getListMaterials() throws Exception {
        ArrayList<Material> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Materials";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            ds.add(mapMaterial(rs));
        }

        rs.close();
        kn.cn.close();
        return ds;
    }
    
    public ArrayList<Material> getListMaterialByCondition(int page, int pageSize, String searchValue) throws Exception {
        ArrayList<Material> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * " +
                     "FROM tbl_Materials " +
                     "WHERE MaterialName LIKE ? " +
                     "ORDER BY MaterialID " +
                     "OFFSET ? ROWS " +
                     "FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, "%" + searchValue + "%"); 
        cmd.setInt(2, offset); 
        cmd.setInt(3, pageSize); 
        
        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapMaterial(rs));
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    
    public int countMaterialsByCondition(String searchValue) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(MaterialID) " +
	            "FROM tbl_Materials " +
	            "WHERE MaterialName LIKE ?";
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
    
	public int addMaterial(String mateName, String description) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO tbl_Materials (MaterialName, Description) VALUES (?, ?)";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, mateName);
	    cmd.setString(2, description);
	    int kq = cmd.executeUpdate();
	    return kq;
	}
	
	public int updateMaterial(Long mateID, String mateName, String description) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "UPDATE tbl_Materials SET MaterialName = ?, Description = ? "
	    		+ "WHERE MaterialID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    
	    cmd.setString(1, mateName);  
	    cmd.setString(2, description);
	    cmd.setLong(3, mateID);
	    int kq = cmd.executeUpdate();
	    return kq;  
	}
    
    public int deleteMaterial(Long mateID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "DELETE FROM tbl_Materials WHERE MaterialID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mateID);
		int result = cmd.executeUpdate();

		cmd.close();
		kn.cn.close();
		return result;
	}
}

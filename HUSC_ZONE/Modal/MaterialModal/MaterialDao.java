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
}

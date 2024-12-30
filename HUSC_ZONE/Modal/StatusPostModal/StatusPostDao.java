package StatusPostModal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import CommonModal.KetNoi;

public class StatusPostDao {

    private StatusPost mapStatusPost(ResultSet rs) throws Exception {
        Long postID = rs.getLong("PostID");
        String postContent = rs.getString("PostContent");
        String imagePath = rs.getString("ImagePath");
        Long uploadedBy = rs.getLong("UploadedBy");
        Date createdAt = rs.getDate("CreatedAt");
        Date updatedAt = rs.getDate("UpdatedAt");
        boolean isPublic = rs.getBoolean("IsPublic");

        return new StatusPost(postID, postContent, uploadedBy, createdAt,
        		updatedAt, imagePath, isPublic);
    }

    public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_StatusPosts (PostContent, UploadedBy, CreatedAt, ImagePath, IsPublic) "
                + "VALUES (?, ?, GETDATE(), ?, 1)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, content);
        cmd.setLong(2, userID);
        cmd.setString(3, imgPath);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public int updateStatusPost(Long PostID, String postContent, String imgPath) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_StatusPosts " +
                "SET PostContent = ?, ImagePath = ?, UpdatedAt = GETDATE() " +
                "WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, postContent);
        cmd.setString(2, imgPath);
        cmd.setLong(3, PostID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public int deleteStatusPost(Long PostID) throws Exception {     
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_StatusPosts WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, PostID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public StatusPost getStatusPostByID(Long PostID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_StatusPosts WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, PostID);
        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            return mapStatusPost(rs);
        }
        rs.close();
        cmd.close();
        kn.cn.close();
        return null;
    }
    
    public int updateVisibilityStatusPost(Long PostID, boolean status) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_StatusPosts " +
                "SET IsPublic = ? " +
                "WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setBoolean(1, status);
        cmd.setLong(2, PostID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

}

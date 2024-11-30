package StatusPostModal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class StatusPostDao {

	public ArrayList<StatusPost> getListPosts(int page, int pageSize, Long userID) throws Exception {
	    ArrayList<StatusPost> ds = new ArrayList<StatusPost>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "";
	    if (userID != null && userID > 0) {
	        sql = "SELECT * " +
	              "FROM tbl_StatusPosts " +
	              "WHERE UploadedBy = ? " +
	              "ORDER BY CreatedAt DESC " +
	              "OFFSET (? - 1) * ? ROWS " +
	              "FETCH NEXT ? ROWS ONLY";
	    } else {
	        sql = "SELECT * " +
	              "FROM tbl_StatusPosts " +
	              "ORDER BY CreatedAt DESC " +
	              "OFFSET (? - 1) * ? ROWS " +
	              "FETCH NEXT ? ROWS ONLY";
	    }

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    if (userID != null && userID > 0) {
	        cmd.setLong(1, userID);
	        cmd.setInt(2, page);
	        cmd.setInt(3, pageSize);
	        cmd.setInt(4, pageSize);
	    } else {
	        cmd.setInt(1, page);
	        cmd.setInt(2, pageSize);
	        cmd.setInt(3, pageSize);
	    }

	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        Long postID = rs.getLong("PostID");
	        String postContent = rs.getString("PostContent");
	        String imagePath = rs.getString("ImagePath");
	        Long uploadedBy = rs.getLong("UploadedBy");
	        Date createdAt = rs.getDate("CreatedAt");
	        Date updatedAt = rs.getDate("UpdatedAt");

	        ds.add(new StatusPost(postID, postContent, uploadedBy, createdAt, updatedAt, imagePath));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();

	    return ds;
	}

	public int getCountPosts(Long userID) throws Exception {
	    int count = 0;
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "";
	    if (userID != null && userID > 0) {
	        sql = "SELECT COUNT(*) " +
	              "FROM tbl_StatusPosts " +
	              "WHERE UploadedBy = ?";
	    } else {
	        sql = "SELECT COUNT(*) " +
	              "FROM tbl_StatusPosts";
	    }

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);

	    if (userID != null && userID > 0) {
	        cmd.setLong(1, userID);
	    }

	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();

	    return count;
	}

    public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_StatusPosts (PostContent, UploadedBy, CreatedAt, ImagePath) "
                + "VALUES (?, ?, GETDATE(), ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, content);
        cmd.setLong(2, userID);
        cmd.setString(3, imgPath);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public int updateStatusPost(Long StatusPostID, String postContent, String imgPath) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_StatusPosts " +
                "SET PostContent = ?, ImagePath = ?, UpdatedAt = GETDATE() " +
                "WHERE StatusPostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, postContent);
        cmd.setString(2, imgPath);
        cmd.setLong(3, StatusPostID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public int deleteStatusPost(Long StatusPostID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        try {
            String sqlDltLikes = "DELETE FROM tbl_Likes WHERE PostID = ?";
            PreparedStatement cmdDltLikes = kn.cn.prepareStatement(sqlDltLikes);
            cmdDltLikes.setLong(1, StatusPostID);
            cmdDltLikes.executeUpdate();
            cmdDltLikes.close();

            String sqlDltCmts = "DELETE FROM tbl_Comments WHERE PostID = ?";
            PreparedStatement cmdDltCmts = kn.cn.prepareStatement(sqlDltCmts);
            cmdDltCmts.setLong(1, StatusPostID);
            cmdDltCmts.executeUpdate();
            cmdDltCmts.close();

            String sqlDltRpts = "DELETE FROM tbl_Comments WHERE PostID = ?";
            PreparedStatement cmdDltRpts = kn.cn.prepareStatement(sqlDltRpts);
            cmdDltRpts.setLong(1, StatusPostID);
            cmdDltRpts.executeUpdate();
            cmdDltRpts.close();

            // Xóa bài viết
            String sqlDeletePost = "DELETE FROM tbl_StatusPosts WHERE PostID = ?";
            PreparedStatement cmdDeletePost = kn.cn.prepareStatement(sqlDeletePost);
            cmdDeletePost.setLong(1, StatusPostID);
            int result = cmdDeletePost.executeUpdate();
            cmdDeletePost.close();

            return result;
        } finally {
            kn.cn.close();
        }
    }

    public StatusPost getStatusPost(Long StatusPostID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_StatusPosts WHERE StatusPostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, StatusPostID);
        ResultSet rs = cmd.executeQuery();

        if (rs.next()) {
            Long postID = rs.getLong("StatusPostID");
            String postContent = rs.getString("PostContent");
            String imagePath = rs.getString("ImagePath");
            Long uploadedBy = rs.getLong("UploadedBy");
            Date createdAt = rs.getDate("CreatedAt");
            Date updatedAt = rs.getDate("UpdatedAt");
            rs.close();
            cmd.close();
            kn.cn.close();
            return new StatusPost(postID, postContent, uploadedBy, createdAt, updatedAt, imagePath);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return null;
    }

}

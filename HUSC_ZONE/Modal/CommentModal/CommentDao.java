package CommentModal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class CommentDao {

	public ArrayList<Comment> getListCommentsByUserID(Long userID) throws Exception {
        ArrayList<Comment> ds = new ArrayList<Comment>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM tbl_Comments WHERE CommentedBy = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapComment(rs));
        }
        rs.close();
        cmd.close();
        kn.cn.close();

        return ds;
    }

    public ArrayList<Comment> getListCommentsByPostID(Long postID) throws Exception {
        ArrayList<Comment> ds = new ArrayList<Comment>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * FROM tbl_Comments WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapComment(rs));
        }
        rs.close();
        cmd.close();
        kn.cn.close();

        return ds;
    }
	
	private Comment mapComment(ResultSet rs) throws Exception {
        Long commentID = rs.getLong("CommentID");
        String content = rs.getString("CommentContent");
        Long postID = rs.getLong("PostID");
        Long commentedBy = rs.getLong("CommentedBy");
        Date commentedAt = rs.getDate("CommentedAt");
        Date updatedAt = rs.getDate("UpdatedAt");
        
        return new Comment(commentID, content, postID, commentedBy, commentedAt, updatedAt);
    }
	
    public int addComment(Long postID, Long userID, String content) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_Comments (PostID, CommentedBy, CommentContent, CommentedAt) " +
                "VALUES (?, ?, ?, GETDATE())";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        cmd.setLong(2, userID);
        cmd.setString(3, content);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
    
    public int updateComment(Long commentID, String content) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Comments " +
                     "SET CommentContent = ?, UpdatedAt = GETDATE() " +
                     "WHERE CommentID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, content);
        cmd.setLong(2, commentID); 
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result; 
    }

    public int deleteComment(Long CommentID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Comments WHERE CommentID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, CommentID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
}

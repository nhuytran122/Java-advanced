package LikeModal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class LikeDao {
	public ArrayList<Like> getListLikesByUserID(Long userID) throws Exception {
	    ArrayList<Like> ds = new ArrayList<Like>();
	    
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM tbl_Likes WHERE LikedBy = ?";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, userID); 
	    
	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        Long likeID = rs.getLong("LikeID");
	        Long postID = rs.getLong("PostID");
	        Date likedAt = rs.getDate("LikedAt");
	        Long likedBy = rs.getLong("LikedBy");
	        ds.add(new Like(likeID, postID, likedAt, likedBy));
	    }
	    
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    
	    return ds;
	}
	
    public int addLike(Long postID, Long userID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_Likes (PostID, LikedAt, LikedBy) " +
                "VALUES (?, ?, GETDATE())";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        cmd.setLong(2, userID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }


    public int unLike(Long LikeID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Likes WHERE LikeID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, LikeID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
    
    public boolean hasUserLikedPost(Long userID, Long postID) throws Exception {
        boolean isLiked = false;

        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM tbl_Likes WHERE LikedBy = ? AND PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        cmd.setLong(2, postID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            isLiked = rs.getInt(1) > 0; 
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return isLiked;
    }
    
    public int countLikesByPostID(Long postID) throws Exception {
        int count = 0;

        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM tbl_Likes WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1); 
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return count;
    }
}

package V_DetailsCommentModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.KetNoi;

public class DetailCommentDao {
	public ArrayList<DetailsComment> getCommentsByPostID(Long postID) throws Exception {
		ArrayList<DetailsComment> ds = new ArrayList<DetailsComment>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Comments " +
				"WHERE PostID = ? ";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, postID);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsComment(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	public ArrayList<DetailsComment> getCommentsByUserID(Long userID) throws Exception {
		ArrayList<DetailsComment> ds = new ArrayList<DetailsComment>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Comments " +
				"WHERE CommentedBy = ? ";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsComment(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	private DetailsComment mapDetailsComment(ResultSet rs) throws Exception {
		Long commentID = rs.getLong("CommentID");
		Long postID = rs.getLong("PostID");
        String content = rs.getString("CommentContent");
        Long commentedBy = rs.getLong("CommentedBy");
        Date commentedAt = rs.getDate("CommentedAt");
        Date updatedAt = rs.getDate("UpdatedAt");
        String name = rs.getString("Name");
        String avatar = rs.getString("Avatar");

		return new DetailsComment(commentID, content, postID, commentedBy, commentedAt, 
				updatedAt, name, avatar);
	}
}

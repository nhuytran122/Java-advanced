package V_DetailsCommentModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.KetNoi;

public class DetailsCommentDao {
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

	public ArrayList<DetailsComment> getCommentsByUserID(int page, int pageSize, Long userID) throws Exception {
		ArrayList<DetailsComment> ds = new ArrayList<DetailsComment>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Comments " +
				"WHERE CommentedBy = ? " +
				"ORDER BY CommentedAt DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setInt(2, page);
		cmd.setInt(3, pageSize);
		cmd.setInt(4, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsComment(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public int getCountCommentsByUserID(Long userID) throws Exception {
		int count = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) FROM V_Details_Comments WHERE CommentedBy = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	private DetailsComment mapDetailsComment(ResultSet rs) throws Exception {
		Long commentID = rs.getLong("CommentID");
		Long postID = rs.getLong("PostID");
		String content = rs.getString("CommentContent");
		Long commentedBy = rs.getLong("CommentedBy");
		Date commentedAt = rs.getDate("CommentedAt");
		Date updatedAt = rs.getDate("UpdatedAt");
		String commentedByName = rs.getString("CommentedByName");
		String commentedByAvatar = rs.getString("CommentedByAvatar");
		String posterName = rs.getString("PosterName");
		String posterAvatar = rs.getString("PosterAvatar");
		Long posterID = rs.getLong("PosterID");
		String imagePath = rs.getString("ImagePath");

		return new DetailsComment(commentID, content, postID, commentedBy, commentedAt,
				updatedAt, commentedByName, commentedByAvatar, posterName, posterAvatar, posterID, imagePath);
	}
}

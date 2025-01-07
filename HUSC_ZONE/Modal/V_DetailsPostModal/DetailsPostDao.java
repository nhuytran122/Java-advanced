package V_DetailsPostModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class DetailsPostDao {
	public DetailsPost getDetailsPostByID(Long postID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from V_Details_Posts where PostID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, postID);
		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			return mapDetailsPost(rs);
		}

		rs.close();
		cmd.close();
		kn.cn.close();
		return null;
	}
	
    public int getCountPostsByUserID(Long userID) throws Exception {
        int count = 0;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM V_Details_Posts "
        		+ "WHERE UploadedBy = ? AND PostVisibility = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        cmd.setString(2, Constants.POST_PUBLIC);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return count;
    }

	public int getCountPostsByConditions(String searchValue) throws Exception {
		int count = 0;
		searchValue = "%" + searchValue + "%";
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM V_Details_Posts " +
				"WHERE (PostContent LIKE ? OR Name LIKE ? ) AND PostVisibility = ? ";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);
		cmd.setString(3, Constants.POST_PUBLIC);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public int getCountAllPostsByConditions(String searchValue) throws Exception {
		int count = 0;
		searchValue = "%" + searchValue + "%";
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM V_Details_Posts " +
				"WHERE (PostContent LIKE ? OR Name LIKE ? )";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}
	
	public ArrayList<DetailsPost> getPostsByUserID(int page, int pageSize, Long userID) throws Exception {
		ArrayList<DetailsPost> ds = new ArrayList<DetailsPost>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Posts " +
				"WHERE UploadedBy = ?  AND PostVisibility = ? " +
				"ORDER BY CreatedAt DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setString(2, Constants.POST_PUBLIC);
		cmd.setInt(3, page);
		cmd.setInt(4, pageSize);
		cmd.setInt(5, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsPost(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<DetailsPost> getPostsByConditions(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<DetailsPost> ds = new ArrayList<DetailsPost>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_Details_Posts " +
	             "WHERE (PostContent LIKE ? OR Name LIKE ?) AND PostVisibility = ? " + 
	             "ORDER BY CreatedAt DESC " +
	             "OFFSET (? - 1) * ? ROWS " +  
	             "FETCH NEXT ? ROWS ONLY";

	   searchValue = "%" + searchValue + "%";
	   PreparedStatement cmd = kn.cn.prepareStatement(sql);
	   cmd.setString(1, searchValue);
	   cmd.setString(2, searchValue);
	   cmd.setString(3, Constants.POST_PUBLIC);
	   cmd.setInt(4, page);
	   cmd.setInt(5, pageSize);
	   cmd.setInt(6, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsPost(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<DetailsPost> getAllPostsByConditions(int page, int pageSize, String searchValue) throws Exception {
		ArrayList<DetailsPost> ds = new ArrayList<DetailsPost>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_Details_Posts " +
	             "WHERE (PostContent LIKE ? OR Name LIKE ?) " + 
	             "ORDER BY CreatedAt DESC " +
	             "OFFSET (? - 1) * ? ROWS " +  
	             "FETCH NEXT ? ROWS ONLY";

	   searchValue = "%" + searchValue + "%";
	   PreparedStatement cmd = kn.cn.prepareStatement(sql);
	   cmd.setString(1, searchValue);
	   cmd.setString(2, searchValue);
	   cmd.setInt(3, page);
	   cmd.setInt(4, pageSize);
	   cmd.setInt(5, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsPost(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	private DetailsPost mapDetailsPost(ResultSet rs) throws Exception {
		Long postID = rs.getLong("PostID");
        String postContent = rs.getString("PostContent");
        String imagePath = rs.getString("ImagePath");
        Long uploadedBy = rs.getLong("UploadedBy");
        Date createdAt = rs.getDate("CreatedAt");
        Date updatedAt = rs.getDate("UpdatedAt");
		String name = rs.getString("Name");
		String avatar = rs.getString("Avatar");
		Long countLikes = rs.getLong("CountLikes");
		Long countComments = rs.getLong("CountComments");
		String postVisibility = rs.getString("PostVisibility");

		return new DetailsPost(postID, postContent, uploadedBy, createdAt,
				updatedAt, imagePath, name, avatar, countLikes, countComments, postVisibility);
	}
}

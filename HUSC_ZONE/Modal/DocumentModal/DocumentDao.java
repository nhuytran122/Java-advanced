package DocumentModal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class DocumentDao {
	public ArrayList<Document> getDocsByUserID(int page, int pageSize, Long userID) throws Exception {
		ArrayList<Document> ds = new ArrayList<Document>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM tbl_Documents " +
				"WHERE UploadedBy = ? " +
				"ORDER BY CreatedAt DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setInt(2, page);
		cmd.setInt(3, pageSize);
		cmd.setInt(4, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDocument(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<Document> getDocsByConditions(int page, int pageSize, String searchValue,
			Long categoryID, Long materialID) throws Exception {
		ArrayList<Document> ds = new ArrayList<Document>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM tbl_Documents " +
				"WHERE (Title LIKE ? OR Description LIKE ?) " +
				"AND (? = 0 OR CategoryID = ?) " +
				"AND (? = 0 OR MaterialID = ?) " +
				"ORDER BY CreatedAt DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		searchValue = "%" + searchValue + "%";
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);
		cmd.setLong(3, categoryID);
		cmd.setLong(4, categoryID);
		cmd.setLong(5, materialID);
		cmd.setLong(6, materialID);
		cmd.setInt(7, page);
		cmd.setInt(8, pageSize);
		cmd.setInt(9, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDocument(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	private Document mapDocument(ResultSet rs) throws Exception {
		Long documentID = rs.getLong("DocumentID");
		String title = rs.getString("Title");
		String description = rs.getString("Description");
		String filePath = rs.getString("FilePath");
		Long catID = rs.getLong("CategoryID");
		Long matID = rs.getLong("MaterialID");
		Long uploadedBy = rs.getLong("UploadedBy");
		Date createdAt = rs.getDate("CreatedAt");
		Date updatedAt = rs.getDate("UpdatedAt");

		return new Document(documentID, title, description, createdAt, updatedAt, filePath, catID, matID, uploadedBy);
	}

	public int getCountDocsByUserID(Long userID) throws Exception {
		int count = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM tbl_Documents " +
				"WHERE UploadedBy = ?";

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

	public int getCountDocsByConditions(String searchValue, Long categoryID, Long materialID) throws Exception {
		int count = 0;
		searchValue = "%" + searchValue + "%";
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM tbl_Documents " +
				"WHERE (Title LIKE ? OR Description LIKE ?) " +
				"AND (? = 0 OR CategoryID = ?) " +
				"AND (? = 0 OR MaterialID = ?)";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);
		cmd.setLong(3, categoryID);
		cmd.setLong(4, categoryID);
		cmd.setLong(5, materialID);
		cmd.setLong(6, materialID);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public int addDocument(String title, String description, String filePath, Long categoryID, Long materialID,
			Long uploadedBy) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Documents (Title, Description, FilePath, CategoryID, MaterialID, UploadedBy, CreatedAt) "
				+ "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, title);
		cmd.setString(2, description);
		cmd.setString(3, filePath);
		cmd.setLong(4, categoryID);
		cmd.setLong(5, materialID);
		cmd.setLong(6, uploadedBy);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}

	public int updateDocument(Long documentID, String title, String description, String filePath, Long categoryID,
			Long materialID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "UPDATE tbl_Documents " +
				"SET Title = ?, Description = ?, FilePath = ?, CategoryID = ?, MaterialID = ? , UpdatedAt = GETDATE()" +
				"WHERE DocumentID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, title);
		cmd.setString(2, description);
		cmd.setString(3, filePath);
		cmd.setLong(4, categoryID);
		cmd.setLong(5, materialID);
		cmd.setLong(6, documentID);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}

	public int deleteDocument(Long documentID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sqlDltBookmarks = "DELETE FROM tbl_Bookmarks WHERE DocumentID = ?";
		PreparedStatement cmdDltBookmarks = kn.cn.prepareStatement(sqlDltBookmarks);
		cmdDltBookmarks.setLong(1, documentID);
		cmdDltBookmarks.executeUpdate();

		String sql = "DELETE FROM tbl_Documents WHERE DocumentID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, documentID);
		int result = cmd.executeUpdate();

		cmd.close();
		kn.cn.close();
		return result;
	}

	public Document getDocument(Long documentID) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM tbl_Documents WHERE DocumentID = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, documentID);
	    ResultSet rs = cmd.executeQuery();
	    if (rs.next()) {
	        return mapDocument(rs);
	    }
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return null;
	}
}

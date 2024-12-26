package DocumentModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import CommonModal.KetNoi;

public class DocumentDao {
	
	public Document getDocumentByID(Long documentID) throws Exception {
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

		String sql = "DELETE FROM tbl_Documents WHERE DocumentID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, documentID);
		int result = cmd.executeUpdate();

		cmd.close();
		kn.cn.close();
		return result;
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
}

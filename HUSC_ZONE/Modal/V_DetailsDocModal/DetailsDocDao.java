package V_DetailsDocModal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class DetailsDocDao {
	public DetailsDoc getDetailsDocByID(Long docID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from V_Details_Docs where DocumentID = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, docID);
		ResultSet rs = cmd.executeQuery();

		if (rs.next()) {
			return mapDetailsDoc(rs);
		}

		rs.close();
		cmd.close();
		kn.cn.close();
		return null;
	}

	public ArrayList<DetailsDoc> getListDocsSuggest(Long docID, Long cateID) throws Exception {
		ArrayList<DetailsDoc> ds = new ArrayList<DetailsDoc>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT TOP 4 * "
				+ "FROM V_Details_Docs "
				+ "WHERE CategoryID = ? AND DocumentID != ?";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, cateID);
		cmd.setLong(2, docID);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsDoc(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	public int getCountDocsByUserID(Long userID) throws Exception {
		int count = 0;
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM V_Details_Docs " +
				"WHERE (UploadedBy = ? ) ";

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
				"FROM V_Details_Docs " +
				"WHERE (Title LIKE ? OR Description LIKE ? OR Name Like ?) " +
				"AND (? = 0 OR CategoryID = ?) " +
				"AND (? = 0 OR MaterialID = ?)";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);
		cmd.setString(3, searchValue);
		cmd.setLong(4, categoryID);
		cmd.setLong(5, categoryID);
		cmd.setLong(6, materialID);
		cmd.setLong(7, materialID);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public ArrayList<DetailsDoc> getDocsByUserIDPagination(int page, int pageSize, Long userID) throws Exception {
		ArrayList<DetailsDoc> ds = new ArrayList<DetailsDoc>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Docs " +
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
			ds.add(mapDetailsDoc(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<DetailsDoc> getDocsByConditions(int page, int pageSize, String searchValue,
			Long categoryID, Long materialID) throws Exception {
		ArrayList<DetailsDoc> ds = new ArrayList<DetailsDoc>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Docs " +
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
			ds.add(mapDetailsDoc(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	public ArrayList<DetailsDoc> getListDocsByUserID(Long userID) throws Exception {
		ArrayList<DetailsDoc> ds = new ArrayList<DetailsDoc>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Docs " +
				"WHERE UploadedBy = ? " +
				"ORDER BY CreatedAt DESC ";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsDoc(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	private DetailsDoc mapDetailsDoc(ResultSet rs) throws Exception {
		Long DocumentID = rs.getLong("DocumentID");
		String title = rs.getString("Title");
		String description = rs.getString("Description");
		String filePath = rs.getString("FilePath");
		Long catID = rs.getLong("CategoryID");
		Long matID = rs.getLong("MaterialID");
		Long uploadedBy = rs.getLong("UploadedBy");
		Date createdAt = rs.getDate("CreatedAt");
		Date updatedAt = rs.getDate("UpdatedAt");
		String categoryName = rs.getString("CategoryName");
		String materialName = rs.getString("MaterialName");
		String name = rs.getString("Name");
		Long countBookmarks = rs.getLong("CountBookmarks");
		String categoryImage = rs.getString("CategoryImage");

		return new DetailsDoc(DocumentID, title, description, createdAt, updatedAt, filePath, catID, matID, uploadedBy,
				categoryName, materialName, name, countBookmarks, categoryImage);
	}
}

package V_DetailsBookmarkModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.KetNoi;

public class DetailsBookmarkDao {
	public ArrayList<DetailsBookmark> getListBookmarksByUserID(int page, int pageSize, Long userID) throws Exception {
		ArrayList<DetailsBookmark> ds = new ArrayList<DetailsBookmark>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * " +
				"FROM V_Details_Bookmarks " +
				"WHERE MarkedBy = ? " +
				"ORDER BY MarkedAt DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setInt(2, page);
		cmd.setInt(3, pageSize);
		cmd.setInt(4, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsBookmark(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
	
	public int getCountBookmarksByUserID(Long userID) throws Exception {
        int count = 0;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM V_Details_Bookmarks WHERE MarkedBy = ?";
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

	private DetailsBookmark mapDetailsBookmark(ResultSet rs) throws Exception {
		Long MarkedBy = rs.getLong("MarkedBy");
		Long DocumentID = rs.getLong("DocumentID");
        Long BookmarkID = rs.getLong("BookmarkID");
        Long CategoryID = rs.getLong("CategoryID");
        Long MaterialID = rs.getLong("MaterialID");
        String Title = rs.getString("Title");
        String CategoryName = rs.getString("CategoryName");
        String MaterialName = rs.getString("MaterialName");
        Date MarkedAt = rs.getDate("MarkedAt");
        String CategoryImage = rs.getString("CategoryImage");
		
		return new DetailsBookmark(MarkedBy, DocumentID, BookmarkID, CategoryID,
				MaterialID, Title, CategoryName, MaterialName, MarkedAt, CategoryImage);
	}
}

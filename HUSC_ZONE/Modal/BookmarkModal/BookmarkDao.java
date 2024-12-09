package BookmarkModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import CommonModal.KetNoi;

public class BookmarkDao {
	public int addBookmark(Long documentID, Long userID) throws Exception {
        if (hasUserMarkedDocs(userID, documentID)) {
            return 0; 
        }

        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_Bookmarks (DocumentID, MarkedBy, MarkedAt) " +
                "VALUES (?, ?, GETDATE())";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, documentID);
        cmd.setLong(2, userID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }


    public int deleteBookmark(Long docID, Long userID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Bookmarks WHERE DocumentID = ? AND MarkedBy = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, docID);
        cmd.setLong(2, userID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
    
    public boolean hasUserMarkedDocs(Long userID, Long docID) throws Exception {
        boolean isLiked = false;

        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM tbl_Bookmarks WHERE MarkedBy = ? AND DocumentID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        cmd.setLong(2, docID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            isLiked = rs.getInt(1) > 0; 
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return isLiked;
    }

}

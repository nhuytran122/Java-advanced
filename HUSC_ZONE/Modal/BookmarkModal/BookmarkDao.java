package BookmarkModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class BookmarkDao {
	public ArrayList<Bookmark> getListBookmarks(Long userID, int page, int pageSize) throws Exception {
	    ArrayList<Bookmark> ds = new ArrayList<Bookmark>();

	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT * " +
	                 "FROM tbl_Bookmarks " +
	                 "WHERE MarkedBy = ? " +
	                 "ORDER BY BookmarkID ASC " +
	                 "OFFSET (? - 1) * ? ROWS " +
	                 "FETCH NEXT ? ROWS ONLY";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, userID); 
	    cmd.setInt(2, page);
	    cmd.setInt(3, pageSize);
	    cmd.setInt(4, pageSize);

	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        Long bookmarkID = rs.getLong("BookmarkID");
	        Long documentID = rs.getLong("DocumentID");
	        Long markedBy = rs.getLong("MarkedBy");
	        ds.add(new Bookmark(bookmarkID, documentID, markedBy));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();

	    return ds;
	}

	public int getCountBookmarks(Long userID) throws Exception {
	    int count = 0;
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT COUNT(*) " +
	                 "FROM tbl_Bookmarks " +
	                 "WHERE MarkedBy = ?";    

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
	
    public int addBookmark(Long documentID, Long userID) throws Exception {
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


    public int deleteBookmark(Long bookmarkID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Bookmarkss WHERE BookmarkID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, bookmarkID);
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

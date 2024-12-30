package NotificationModal;

import java.sql.PreparedStatement;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class NotificationDao {
	public int createNotiRelatedToCmtPost(Long cmtID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (CreatedAt, CmtID, ActivityTypeID) "
				+ "VALUES (GETDATE(), ? , ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, cmtID);
		cmd.setLong(2, Constants.ACTIVITY_COMMENT);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
	
	public int createNotiRelatedToReportPost(Long reportID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (CreatedAt, ReportID, ActivityTypeID) "
				+ "VALUES (GETDATE(), ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, reportID);
		cmd.setLong(2, Constants.ACTIVITY_REPORT);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
	
	public int createNotiRelatedToHandleReport(Long reportID, Long actiTypeID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (CreatedAt, ReportID, ActivityTypeID) "
				+ "VALUES (GETDATE(), ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, reportID);
		cmd.setLong(2, actiTypeID);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
	
	public int deleteNotificationByCmtID(Long cmtID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications WHERE CmtID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, cmtID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
	
	public int deleteNotificationByReportID(Long reportID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications WHERE ReportID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, reportID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
    // Xóa tb liên quan đến bài viết qua Reports
    public int deleteNotificationsByPostIDFromReports(Long postID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications " +
                     "FROM tbl_StatusPosts as sp, tbl_Reports as r " +
                     "WHERE sp.PostID = r.PostID AND r.PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
    
    /* Đã set Cascade
	// Xóa tb liên quan đến bài viết qua Comments
    public int deleteNotificationsByPostIDFromComments(Long postID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications " +
                     "FROM tbl_StatusPosts as sp, tbl_Comments as c " +
                     "WHERE sp.PostID = c.PostID AND c.PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }*/
}

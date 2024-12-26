package NotificationModal;

import java.sql.PreparedStatement;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class NotificationDao {
	public int createNotiRelatedToCmtPost(Long userID, Long cmtID, Long actorID, Long postID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (SendTo, CreatedAt, CmtID, ActorID, ActivityTypeID, PostID) "
				+ "VALUES (?, GETDATE(), ? , ?, ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setLong(2, cmtID);
		cmd.setLong(3, actorID);
		cmd.setLong(4, Constants.ACTIVITY_COMMENT);
		cmd.setLong(5, postID);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
	
	public int createNotiRelatedToReportPost(Long userID, Long reportID, Long actorID, Long postID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (SendTo, CreatedAt, ReportID, ActorID, ActivityTypeID, PostID) "
				+ "VALUES (?, GETDATE(), ? , ?, ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setLong(2, reportID);
		cmd.setLong(3, actorID);
		cmd.setLong(4, Constants.ACTIVITY_REPORT);
		cmd.setLong(5, postID);
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
	
	public int deleteNotificationByLikeID(Long likeID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications WHERE LikeID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, likeID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
	
	public int deleteNotificationByPostID(Long postID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Notifications WHERE PostID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
}

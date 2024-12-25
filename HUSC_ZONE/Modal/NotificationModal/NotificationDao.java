package NotificationModal;

import java.sql.PreparedStatement;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class NotificationDao {
	public int createNotiRelatedToCmtPost(Long userID, Long cmtID, Long actorID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (SendTo, CreatedAt, CmtID, ActorID, ActivityTypeID) "
				+ "VALUES (?, GETDATE(), ? , ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setLong(2, cmtID);
		cmd.setLong(3, actorID);
		cmd.setLong(4, Constants.ACTIVITY_COMMENT);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
	
	public int createNotiRelatedToReportPost(Long userID, Long reportID, Long actorID) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO tbl_Notifications (SendTo, CreatedAt, ReportID, ActorID, ActivityTypeID) "
				+ "VALUES (?, GETDATE(), ? , ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);
		cmd.setLong(2, reportID);
		cmd.setLong(3, actorID);
		cmd.setLong(4, Constants.ACTIVITY_REPORT);
		int result = cmd.executeUpdate();
		cmd.close();
		kn.cn.close();
		return result;
	}
}

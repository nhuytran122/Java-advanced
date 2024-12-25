package V_DetailsNotificationModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.KetNoi;

public class DetailsNotificationDao {
	public ArrayList<DetailsNotification> getNotificationsByUserID(Long userID) throws Exception {
		ArrayList<DetailsNotification> ds = new ArrayList<DetailsNotification>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT TOP 10 *" +
				"FROM V_Details_Notifications " +
				"WHERE SendTo = ? " +
				"ORDER BY CreatedAt DESC ";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, userID);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsNotification(rs));
		}
		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
    
	private DetailsNotification mapDetailsNotification(ResultSet rs) throws Exception {
		Long NotificationID = rs.getLong("NotificationID");
        Long SendTo = rs.getLong("SendTo");
        Date CreatedAt = rs.getDate("CreatedAt");
        Long PostID = rs.getLong("PostID");
        Long ActorID = rs.getLong("ActorID");
        String ActorName = rs.getString("ActorName");
        String StatusContent = rs.getString("StatusContent");
        String ActivityContent = rs.getString("ActivityContent");
        Long ActivityTypeID = rs.getLong("ActivityTypeID");
		return new DetailsNotification(NotificationID, SendTo, CreatedAt, PostID, ActorID, ActorName, StatusContent, ActivityContent, ActivityTypeID);
	}
}

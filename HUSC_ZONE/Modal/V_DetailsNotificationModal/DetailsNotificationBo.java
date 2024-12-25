package V_DetailsNotificationModal;

import java.util.ArrayList;

public class DetailsNotificationBo {
	DetailsNotificationDao dtNotiDao = new DetailsNotificationDao();
	
	public ArrayList<DetailsNotification> getNotificationsByUserID(Long userID) throws Exception {
		return dtNotiDao.getNotificationsByUserID(userID);
	}
}

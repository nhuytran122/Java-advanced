package NotificationModal;

public class NotificationBo {
	NotificationDao notiDao = new NotificationDao();
	
	public int createNotiRelatedToCmtPost(Long userID, Long cmtID, Long actorID) throws Exception {
		return notiDao.createNotiRelatedToCmtPost(userID, cmtID, actorID);
	}
	
	public int createNotiRelatedToReportPost(Long userID, Long reportID, Long actorID) throws Exception {
		return notiDao.createNotiRelatedToReportPost(userID, reportID, actorID);
	}
}

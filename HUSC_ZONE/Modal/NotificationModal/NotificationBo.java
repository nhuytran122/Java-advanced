package NotificationModal;

public class NotificationBo {
	NotificationDao notiDao = new NotificationDao();
	
	public int createNotiRelatedToCmtPost(Long userID, Long cmtID, Long actorID, Long postID) throws Exception {
		return notiDao.createNotiRelatedToCmtPost(userID, cmtID, actorID, postID);
	}
	
	public int createNotiRelatedToReportPost(Long userID, Long reportID, Long actorID, Long postID) throws Exception {
		return notiDao.createNotiRelatedToReportPost(userID, reportID, actorID, postID);
	}
	
	public int deleteNotificationsByCmtID(Long cmtID) throws Exception {
		return notiDao.deleteNotificationByCmtID(cmtID);
	}
	
	public int deleteNotificationsByReportID(Long reportID) throws Exception {
		return notiDao.deleteNotificationByReportID(reportID);
	}
	
	public int deleteNotificationByLikeID(Long likeID) throws Exception {
		return notiDao.deleteNotificationByLikeID(likeID);
	}
	
	public int deleteNotificationByPostID(Long postID) throws Exception {
		return notiDao.deleteNotificationByPostID(postID);
	}
}

package NotificationModal;

public class NotificationBo {
	NotificationDao notiDao = new NotificationDao();
	
	public int createNotiRelatedToCmtPost(Long cmtID) throws Exception {
		return notiDao.createNotiRelatedToCmtPost(cmtID);
	}
	
	public int createNotiRelatedToReportPost(Long reportID) throws Exception {
		return notiDao.createNotiRelatedToReportPost(reportID);
	}
	
	public int deleteNotificationsByCmtID(Long cmtID) throws Exception {
		return notiDao.deleteNotificationByCmtID(cmtID);
	}
	
	public int deleteNotificationsByReportID(Long reportID) throws Exception {
		return notiDao.deleteNotificationByReportID(reportID);
	}
	
	//Đã set Cascade
//	public void deleteNotificationsByPostID(Long postID) throws Exception {
//		notiDao.deleteNotificationsByPostIDFromComments(postID);
//		notiDao.deleteNotificationsByPostIDFromReports(postID);
//	}
}
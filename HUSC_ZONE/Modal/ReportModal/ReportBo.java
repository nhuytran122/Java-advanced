package ReportModal;

import NotificationModal.NotificationBo;

public class ReportBo {

	ReportDao rpDao = new ReportDao();
	
	public Long addReport(Long postID, Long userID, String reason) throws Exception {
		return rpDao.addReport(postID, userID, reason);
	}
	
	public int deleteReport(Long ReportID) throws Exception {
		NotificationBo notiBo = new NotificationBo();
		notiBo.deleteNotificationsByReportID(ReportID);
		return rpDao.deleteReport(ReportID);
	}
	
	public int deleteReportsByPostID(Long postID) throws Exception {
	    return rpDao.deleteReportsByPostID(postID);
	}
	
	public int getCountPendingReports() throws Exception {
		return rpDao.getCountPendingReports();
	}

}

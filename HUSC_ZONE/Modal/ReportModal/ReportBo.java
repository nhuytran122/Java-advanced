package ReportModal;

import java.util.ArrayList;

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
	
	public int getCountPendingReports() throws Exception {
		return rpDao.getCountPendingReports();
	}
	
	public int updateReport(Long reportID, Long statusID) throws Exception {
		return rpDao.updateReport(reportID, statusID);
	}
	
	public int deleteReportsByUserID(Long userID) throws Exception {
	    NotificationBo notiBo = new NotificationBo();
	    ArrayList<Long> reportIDs = rpDao.getReportIDsByUserID(userID);
	    
	    for (Long reportID : reportIDs) {
	        notiBo.deleteNotificationsByReportID(reportID);
	    }
	    return rpDao.deleteReportsByUserID(userID);
	}
}

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
	
	public ArrayList<Long> getReportIDsByPostID(Long postID) throws Exception {
		return rpDao.getReportIDsByPostID(postID);
	}
	
	public int deleteReportsByPostID(Long postID) throws Exception {
	    NotificationBo notiBo = new NotificationBo();
	    ReportDao reportDao = new ReportDao();

	    // Lấy ds ReportID liên quan PostID
	    ArrayList<Long> reportIDs = reportDao.getReportIDsByPostID(postID);

	    // Xóa tb liên quan đến từng Report
	    for (Long reportID : reportIDs) {
	        notiBo.deleteNotificationsByReportID(reportID);
	    }

	    // Xóa các Report liên quan PostID
	    return reportDao.deleteReportsByPostID(postID);
	}

}

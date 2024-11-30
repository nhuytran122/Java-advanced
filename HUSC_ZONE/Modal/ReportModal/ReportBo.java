package ReportModal;

import java.util.ArrayList;

public class ReportBo {

	ReportDao rpDao = new ReportDao();
	
	public ArrayList<Report> getListReports(Long userID) throws Exception {
		return rpDao.getListReports(userID);
	}
	
	public int addReport(Long postID, Long userID, String reason) throws Exception {
		return rpDao.addReport(postID, userID, reason);
	}
	
	public int deleteReport(Long ReportID) throws Exception {
		return rpDao.deleteReport(ReportID);
	}
}

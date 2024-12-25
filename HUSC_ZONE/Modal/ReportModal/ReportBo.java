package ReportModal;

public class ReportBo {

	ReportDao rpDao = new ReportDao();
	
	public Long addReport(Long postID, Long userID, String reason) throws Exception {
		return rpDao.addReport(postID, userID, reason);
	}
	
	public int deleteReport(Long ReportID) throws Exception {
		return rpDao.deleteReport(ReportID);
	}
}

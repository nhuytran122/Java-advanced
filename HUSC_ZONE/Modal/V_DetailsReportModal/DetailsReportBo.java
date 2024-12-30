package V_DetailsReportModal;

import java.util.ArrayList;

public class DetailsReportBo {
	DetailsReportDao dtRDao = new DetailsReportDao();
	
	public ArrayList<DetailsReport> getListReportsByUserID(int page, int pageSize, Long userID) throws Exception {
		return dtRDao.getListReportsByUserID(page, pageSize, userID);
	}
	
	public int getCountReportsByUserID(Long userID) throws Exception {
		return dtRDao.getCountReportsByUserID(userID);
	}
	
	public ArrayList<DetailsReport> getReportsByConditions(int page, int pageSize, String searchValue, Long statusID) throws Exception {
		return dtRDao.getReportsByConditions(page, pageSize, searchValue, statusID);
	}
	
	public int getCountReportsByConditions(String searchValue, Long statusID) throws Exception {
		return dtRDao.getCountReportsByConditions(searchValue, statusID);
	}
	
	public DetailsReport getReportByID(Long reportID) throws Exception {
		return dtRDao.getReportByID(reportID);
	}
}

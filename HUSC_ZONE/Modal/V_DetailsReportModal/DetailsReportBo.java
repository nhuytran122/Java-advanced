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
}

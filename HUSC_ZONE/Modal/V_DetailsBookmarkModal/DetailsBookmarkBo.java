package V_DetailsBookmarkModal;

import java.util.ArrayList;

public class DetailsBookmarkBo {
	DetailsBookmarkDao dtBmDao = new DetailsBookmarkDao();
	
	public ArrayList<DetailsBookmark> getListBookmarksByUserID(int page, int pageSize, Long userID) throws Exception {
		return dtBmDao.getListBookmarksByUserID(page, pageSize, userID);
	}
	
	public int getCountBookmarksByUserID(Long userID) throws Exception {
		return dtBmDao.getCountBookmarksByUserID(userID);
	}
}

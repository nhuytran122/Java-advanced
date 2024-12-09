package V_DetailsCommentModal;

import java.util.ArrayList;

public class DetailsCommentBo {
	DetailsCommentDao dtCmtDao = new DetailsCommentDao();
	
	public ArrayList<DetailsComment> getCommentsByPostID(Long postID) throws Exception {
		return dtCmtDao.getCommentsByPostID(postID);
	}
	
	public ArrayList<DetailsComment> getCommentsByUserID(int page, int pageSize, Long userID) throws Exception {
		return dtCmtDao.getCommentsByUserID(page, pageSize, userID);
	}
	
	public int getCountCommentsByUserID(Long userID) throws Exception {
		return dtCmtDao.getCountCommentsByUserID(userID);
	}
}

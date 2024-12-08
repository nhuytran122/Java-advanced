package V_DetailsCommentModal;

import java.util.ArrayList;

public class DetailCommentBo {
	DetailCommentDao dtCmtDao = new DetailCommentDao();
	
	public ArrayList<DetailsComment> getCommentsByPostID(Long postID) throws Exception {
		return dtCmtDao.getCommentsByPostID(postID);
	}
	
	public ArrayList<DetailsComment> getCommentsByUserID(Long userID) throws Exception {
		return dtCmtDao.getCommentsByUserID(userID);
	}
}

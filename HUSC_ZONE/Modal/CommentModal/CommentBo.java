package CommentModal;

import java.util.ArrayList;

public class CommentBo {
	CommentDao cmtDao = new CommentDao();
	
	public ArrayList<Comment> getListComments(Long userID) throws Exception {
		return cmtDao.getListComments(userID);
	}
	
	public int addComment(Long postID, Long userID, String content) throws Exception {
		return cmtDao.addComment(postID, userID, content);
	}
	
	public int updateComment(Long commentID, String content) throws Exception {
		return cmtDao.updateComment(commentID, content);
	}
	
	public int deleteComment(Long CommentID) throws Exception {
		return cmtDao.deleteComment(CommentID);
	}
}

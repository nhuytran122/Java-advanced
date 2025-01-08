package CommentModal;

import java.util.ArrayList;

import NotificationModal.NotificationBo;

public class CommentBo {
	CommentDao cmtDao = new CommentDao();
	
	public ArrayList<Comment> getListCommentsByUserID(Long userID) throws Exception {
		return cmtDao.getListCommentsByUserID(userID);
	}
	
	public ArrayList<Comment> getListCommentsByPostID(Long postID) throws Exception {
		return cmtDao.getListCommentsByPostID(postID);
	}
	
	public Long addComment(Long postID, Long userID, String content) throws Exception {
		return cmtDao.addComment(postID, userID, content);
	}
	
	public int updateComment(Long commentID, String content) throws Exception {
		return cmtDao.updateComment(commentID, content);
	}
	
	public int deleteComment(Long CommentID) throws Exception {
		return cmtDao.deleteComment(CommentID);
	}
    
	public ArrayList<Long> getCommentIDsByUserID(Long userID) throws Exception {
		return cmtDao.getCommentIDsByUserID(userID);
	}
	
	public int deleteCommentsByUserID(Long userID) throws Exception {
	    NotificationBo notiBo = new NotificationBo();
	    ArrayList<Long> commentIDs = cmtDao.getCommentIDsByUserID(userID);
	    
	    for (Long cmtID : commentIDs) {
	        notiBo.deleteNotificationsByCmtID(cmtID);
	    }
	    return cmtDao.deleteCommentsByUserID(userID);
	}

}

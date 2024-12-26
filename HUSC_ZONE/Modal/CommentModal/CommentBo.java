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
		NotificationBo notiBo = new NotificationBo();
		notiBo.deleteNotificationsByCmtID(CommentID);
		return cmtDao.deleteComment(CommentID);
	}
    
    public ArrayList<Long> getCommentIDsByPostID(Long postID) throws Exception {
    	return cmtDao.getCommentIDsByPostID(postID);
    }
    
    public int deleteCommentsByPostID(Long postID) throws Exception {
        NotificationBo notiBo = new NotificationBo();
        CommentDao cmtDao = new CommentDao();

        // Lấy danh sách CommentID liên quan PostID
        ArrayList<Long> commentIDs = cmtDao.getCommentIDsByPostID(postID);

        // Xóa noti liên quan đến từng comment
        for (Long commentID : commentIDs) {
            notiBo.deleteNotificationsByCmtID(commentID);
        }

        // Xóa các comment liên quan đến PostID
        return cmtDao.deleteCommentsByPostID(postID);
    }

}

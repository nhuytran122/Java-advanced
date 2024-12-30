package StatusPostModal;

import NotificationModal.NotificationBo;
import ReportModal.ReportBo;

public class StatusPostBo {

	StatusPostDao sttDao = new StatusPostDao();

	public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
		return sttDao.addStatusPost(content, userID, imgPath);
	}

	public int updateStatusPost(Long PostID, String postContent, String imgPath) throws Exception {
		return sttDao.updateStatusPost(PostID, postContent, imgPath);
	}

	public int deleteStatusPost(Long PostID) throws Exception {
		//Đã set Cascade
//	    new NotificationBo().deleteNotificationsByPostID(PostID);
//	    new CommentBo().deleteCommentsByPostID(PostID);
		
	    new NotificationBo().deleteNotificationsByPostID(PostID);
	    return sttDao.deleteStatusPost(PostID);
	}

	public StatusPost getStatusPostByID(Long PostID) throws Exception {
		return sttDao.getStatusPostByID(PostID);
	}
	
	public int updateVisibilityStatusPost(Long PostID, boolean status) throws Exception {
		return sttDao.updateVisibilityStatusPost(PostID, status);
	}
}

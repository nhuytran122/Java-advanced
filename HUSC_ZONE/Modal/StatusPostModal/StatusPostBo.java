package StatusPostModal;

import NotificationModal.NotificationBo;

public class StatusPostBo {

	StatusPostDao sttDao = new StatusPostDao();

	public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
		return sttDao.addStatusPost(content, userID, imgPath);
	}

	public int updateStatusPost(Long PostID, String postContent, String imgPath) throws Exception {
		return sttDao.updateStatusPost(PostID, postContent, imgPath);
	}

	public int deleteStatusPost(Long PostID) throws Exception {
	    new NotificationBo().deleteNotificationByPostID(PostID);

	    return sttDao.deleteStatusPost(PostID);
	}

	public StatusPost getStatusPostByID(Long PostID) throws Exception {
		return sttDao.getStatusPostByID(PostID);
	}
}

package LikeModal;

import java.util.ArrayList;

import NotificationModal.NotificationBo;

public class LikeBo {
	LikeDao likeDao = new LikeDao();
	
	public int addLike(Long postID, Long userID) throws Exception {
		return likeDao.addLike(postID, userID);
	}
	
	public int unLike(Long postID, Long userID) throws Exception {
		return likeDao.unLike(postID, userID);
	}
	
	public boolean hasUserLikedPost(Long userID, Long postID) throws Exception {
		return likeDao.hasUserLikedPost(userID, postID);
	}
	
	public int countLikesByPostID(Long postID) throws Exception {
		return likeDao.countLikesByPostID(postID);
	}
	
	public ArrayList<Long> getLikeIDsByPostID(Long postID) throws Exception {
		return likeDao.getLikeIDsByPostID(postID);
	}
	
	public int deleteLikesByPostID(Long postID) throws Exception {
	    NotificationBo notiBo = new NotificationBo();
	    LikeDao likeDao = new LikeDao();

	    // Lấy ds LikeID liên quan PostID
	    ArrayList<Long> likeIDs = likeDao.getLikeIDsByPostID(postID);

	    // Xóa tb liên quan đến từng Like
	    for (Long likeID : likeIDs) {
	        notiBo.deleteNotificationByLikeID(likeID);
	    }

	    // Xóa các Like liên quan PostID
	    return likeDao.deleteLikesByPostID(postID);
	}

}

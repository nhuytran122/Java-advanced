package LikeModal;

import java.util.ArrayList;

public class LikeBo {
	LikeDao likeDao = new LikeDao();
	
	public ArrayList<Like> getListLikesByUserID(Long userID) throws Exception {
		return likeDao.getListLikesByUserID(userID);
	}
	
	public int addLike(Long postID, Long userID) throws Exception {
		return likeDao.addLike(postID, userID);
	}
	
	public int unLike(Long LikeID) throws Exception {
		return likeDao.unLike(LikeID);
	}
	
	public boolean hasUserLikedPost(Long userID, Long postID) throws Exception {
		return likeDao.hasUserLikedPost(userID, postID);
	}
	
	public int countLikesByPostID(Long postID) throws Exception {
		return likeDao.countLikesByPostID(postID);
	}
}

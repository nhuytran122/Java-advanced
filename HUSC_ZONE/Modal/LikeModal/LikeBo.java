package LikeModal;

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
	
	//Đã set Cascade
//	public int deleteLikesByPostID(Long postID) throws Exception {
//	    return likeDao.deleteLikesByPostID(postID);
//	}

}

package StatusPostModal;

import java.util.ArrayList;

public class StatusPostBo {
	
	StatusPostDao sttDao = new StatusPostDao();
	
	public ArrayList<StatusPost> getPostsByUserID(int page, int pageSize, Long userID) throws Exception {
		return sttDao.getPostsByUserID(page, pageSize, userID);
	}
	
	public ArrayList<StatusPost> getAllPosts(int page, int pageSize, String searchValue) throws Exception {
		return sttDao.getAllPosts(page, pageSize, searchValue);
	}
	
	public int getCountPostsByUserID(Long userID) throws Exception {
		return sttDao.getCountPostsByUserID(userID);
	}
	
	public int getCountAllPost() throws Exception {
		return sttDao.getCountAllPost();
	}
	
	public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
		return sttDao.addStatusPost(content, userID, imgPath);
	}
	
	public int updateStatusPost(Long StatusPostID, String postContent, String imgPath) throws Exception {
		return sttDao.updateStatusPost(StatusPostID, postContent, imgPath);
	}
	
	public int deleteStatusPost(Long StatusPostID) throws Exception {
		return sttDao.deleteStatusPost(StatusPostID);
	}
}

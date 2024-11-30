package StatusPostModal;

import java.util.ArrayList;

public class StatusPostBo {
	
	StatusPostDao sttDao = new StatusPostDao();
	
	public ArrayList<StatusPost> getListPosts(int page, int pageSize, Long userID) throws Exception {
		return sttDao.getListPosts(page, pageSize, userID);
	}
	
	public StatusPost getStatusPost(Long StatusPostID) throws Exception {
		return sttDao.getStatusPost(StatusPostID);
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

package StatusPostModal;

public class StatusPostBo {
	
	StatusPostDao sttDao = new StatusPostDao();
	
	public int addStatusPost(String content, Long userID, String imgPath) throws Exception {
		return sttDao.addStatusPost(content, userID, imgPath);
	}
	
	public int updateStatusPost(Long StatusPostID, String postContent, String imgPath) throws Exception {
		return sttDao.updateStatusPost(StatusPostID, postContent, imgPath);
	}
	
	public int deleteStatusPost(Long StatusPostID) throws Exception {
		return sttDao.deleteStatusPost(StatusPostID);
	}
	
	public StatusPost getStatusPostByID(Long StatusPostID) throws Exception {
		return sttDao.getStatusPostByID(StatusPostID);
	}
}

package V_DetailsPostModal;

import java.util.ArrayList;

public class DetailsPostBo {
	DetailsPostDao dtSttDao = new DetailsPostDao();
	
	public DetailsPost getDetailsPostByID(Long postID) throws Exception {
		return dtSttDao.getDetailsPostByID(postID);
	}
	
	public int getCountPostsByUserID(Long userID) throws Exception {
		return dtSttDao.getCountPostsByUserID(userID);
	}
	
	public int getCountPostsByConditions(String searchValue) throws Exception {
		return dtSttDao.getCountPostsByConditions(searchValue);
	}
	
	public ArrayList<DetailsPost> getPostsByUserID(int page, int pageSize, Long userID) throws Exception {
		return dtSttDao.getPostsByUserID(page, pageSize, userID);
	}
	
	public ArrayList<DetailsPost> getPostsByConditions(int page, int pageSize, String searchValue) throws Exception {
		return dtSttDao.getPostsByConditions(page, pageSize, searchValue);
	}
}

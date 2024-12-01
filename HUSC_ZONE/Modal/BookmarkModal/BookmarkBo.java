package BookmarkModal;

import java.util.ArrayList;

public class BookmarkBo {
	BookmarkDao bDao = new BookmarkDao();
	
	public ArrayList<Bookmark> getListBookmarks(Long userID, int page, int pageSize) throws Exception {
		return bDao.getListBookmarks(userID, page, pageSize);
	}
	
	public int getCountBookmarks(Long userID) throws Exception {
		return bDao.getCountBookmarks(userID);
	}
	
	public int addBookmark(Long documentID, Long userID) throws Exception {
		return bDao.addBookmark(documentID, userID);
	}
	
	public int deleteBookmark(Long bookmarkID) throws Exception {
		return bDao.deleteBookmark(bookmarkID);
	}
	
	public boolean hasUserMarkedDocs(Long userID, Long docID) throws Exception {
		return bDao.hasUserMarkedDocs(userID, docID);
	}
}

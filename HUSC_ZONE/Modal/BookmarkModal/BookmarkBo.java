// ! use
package BookmarkModal;

public class BookmarkBo {
	BookmarkDao bDao = new BookmarkDao();
	
	public int addBookmark(Long documentID, Long userID) throws Exception {
		return bDao.addBookmark(documentID, userID);
	}
	
	public int deleteBookmark(Long docID, Long userID) throws Exception {
		return bDao.deleteBookmark(docID, userID);
	}
	
	public boolean hasUserMarkedDocs(Long userID, Long docID) throws Exception {
		return bDao.hasUserMarkedDocs(userID, docID);
	}
}

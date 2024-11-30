package BookmarkModal;

public class Bookmark {
	private Long BookmarkID;
	private Long DocumentID;
	private Long MarkedBy;
	public Bookmark() {
		super();
	}
	public Bookmark(Long bookmarkID, Long documentID, Long markedBy) {
		super();
		BookmarkID = bookmarkID;
		DocumentID = documentID;
		MarkedBy = markedBy;
	}
	public Long getBookmarkID() {
		return BookmarkID;
	}
	public void setBookmarkID(Long bookmarkID) {
		BookmarkID = bookmarkID;
	}
	public Long getDocumentID() {
		return DocumentID;
	}
	public void setDocumentID(Long documentID) {
		DocumentID = documentID;
	}
	public Long getMarkedBy() {
		return MarkedBy;
	}
	public void setMarkedBy(Long markedBy) {
		MarkedBy = markedBy;
	}
}

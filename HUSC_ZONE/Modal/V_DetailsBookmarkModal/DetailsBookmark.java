package V_DetailsBookmarkModal;

import java.util.Date;

public class DetailsBookmark {
	private Long MarkedBy;
	private Long DocumentID;
	private Long BookmarkID;
	private Long CategoryID;
	private Long MaterialID;
	private String Title;
	private String CategoryName;
	private String MaterialName;
	private Date MarkedAt;
	private String CategoryImage;
	public DetailsBookmark() {
		super();
	}
	public DetailsBookmark(Long markedBy, Long documentID, Long bookmarkID, Long categoryID, Long materialID,
			String title, String categoryName, String materialName, Date markedAt, String categoryImage) {
		super();
		MarkedBy = markedBy;
		DocumentID = documentID;
		BookmarkID = bookmarkID;
		CategoryID = categoryID;
		MaterialID = materialID;
		Title = title;
		CategoryName = categoryName;
		MaterialName = materialName;
		MarkedAt = markedAt;
		CategoryImage = categoryImage;
	}
	public Long getMarkedBy() {
		return MarkedBy;
	}
	public void setMarkedBy(Long markedBy) {
		MarkedBy = markedBy;
	}
	public Long getDocumentID() {
		return DocumentID;
	}
	public void setDocumentID(Long documentID) {
		DocumentID = documentID;
	}
	public Long getBookmarkID() {
		return BookmarkID;
	}
	public void setBookmarkID(Long bookmarkID) {
		BookmarkID = bookmarkID;
	}
	public Long getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(Long categoryID) {
		CategoryID = categoryID;
	}
	public Long getMaterialID() {
		return MaterialID;
	}
	public void setMaterialID(Long materialID) {
		MaterialID = materialID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getMaterialName() {
		return MaterialName;
	}
	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
	public Date getMarkedAt() {
		return MarkedAt;
	}
	public void setMarkedAt(Date markedAt) {
		MarkedAt = markedAt;
	}
	public String getCategoryImage() {
		return CategoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		CategoryImage = categoryImage;
	}
}

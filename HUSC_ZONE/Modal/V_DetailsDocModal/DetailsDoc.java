package V_DetailsDocModal;

import java.util.Date;

public class DetailsDoc {
	private Long DocumentID;
	private String Title;
	private String Description;
	private Date CreatedAt;
	private Date UpdatedAt;
	private String FilePath;
	private Long CategoryID;
	private Long MaterialID;
	private Long UploadedBy;
	private String CategoryName;
	private String MaterialName;
	private String Name;
	private Long CountBookmarks;
	private String CategoryImage;
	public DetailsDoc() {
		super();
	}
	public DetailsDoc(Long documentID, String title, String desription, Date createdAt, Date updatedAt, String filePath,
			Long categoryID, Long materialID, Long uploadedBy, String categoryName, String materialName,
			String name, Long countBookmarks, String categoryImage) {
		super();
		DocumentID = documentID;
		Title = title;
		Description = desription;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		FilePath = filePath;
		CategoryID = categoryID;
		MaterialID = materialID;
		UploadedBy = uploadedBy;
		CategoryName = categoryName;
		MaterialName = materialName;
		Name = name;
		CountBookmarks = countBookmarks;
		CategoryImage = categoryImage;
	}
	public Long getDocumentID() {
		return DocumentID;
	}
	public void setDocumentID(Long documentID) {
		DocumentID = documentID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
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
	public Long getUploadedBy() {
		return UploadedBy;
	}
	public void setUploadedBy(Long uploadedBy) {
		UploadedBy = uploadedBy;
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getCountBookmarks() {
		return CountBookmarks;
	}
	public void setCountBookmarks(Long countBookmarks) {
		CountBookmarks = countBookmarks;
	}
	public String getCategoryImage() {
		return CategoryImage;
	}
	public void setCategoryImage(String categoryImage) {
		CategoryImage = categoryImage;
	}
}

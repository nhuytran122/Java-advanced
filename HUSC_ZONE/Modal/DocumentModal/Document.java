package DocumentModal;

import java.util.Date;

public class Document {
	private Long DocumentID;
	private String Title;
	private String Description;
	private Date CreatedAt;
	private Date UpdatedAt;
	private String FilePath;
	private Long CategoryID;
	private Long MaterialID;
	private Long UploadedBy;
	public Document() {
		super();
	}
	public Document(Long documentID, String title, String description, Date createdAt, Date updatedAt, String filePath,
			Long categoryID, Long materialID, Long uploadedBy) {
		super();
		DocumentID = documentID;
		Title = title;
		Description = description;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		FilePath = filePath;
		CategoryID = categoryID;
		MaterialID = materialID;
		UploadedBy = uploadedBy;
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
	public String getDesription() {
		return Description;
	}
	public void setDesription(String description) {
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
}

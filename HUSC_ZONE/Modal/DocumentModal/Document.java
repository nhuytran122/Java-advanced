package DocumentModal;

import java.sql.Date;

public class Document {
	private Long DocumentID;
	private String Title;
	private String Desription;
	private Date CreatedAt;
	private String FilePath;
	private Long CategoryID;
	private Long MaterialID;
	private Long UploadBy;
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Document(Long documentID, String title, String desription, Date createdAt, String filePath, Long categoryID,
			Long materialID, Long uploadBy) {
		super();
		DocumentID = documentID;
		Title = title;
		Desription = desription;
		CreatedAt = createdAt;
		FilePath = filePath;
		CategoryID = categoryID;
		MaterialID = materialID;
		UploadBy = uploadBy;
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
		return Desription;
	}
	public void setDesription(String desription) {
		Desription = desription;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
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
	public Long getUploadBy() {
		return UploadBy;
	}
	public void setUploadBy(Long uploadBy) {
		UploadBy = uploadBy;
	}
}

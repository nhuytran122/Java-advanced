package StatusPostModal;

import java.util.Date;

public class StatusPost {
	private Long PostID;
	private String PostContent;
	private Long UploadedBy;
	private Date CreatedAt;
	private Date UpdatedAt;
	private String ImagePath;
	private boolean IsPublic;

	public StatusPost() {
		super();
	}

	public StatusPost(Long postID, String postContent, Long uploadedBy, Date createdAt, Date updatedAt,
			String imagePath, boolean isPublic) {
		super();
		PostID = postID;
		PostContent = postContent;
		UploadedBy = uploadedBy;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		ImagePath = imagePath;
		IsPublic = isPublic;
	}

	public Long getPostID() {
		return PostID;
	}

	public void setPostID(Long postID) {
		PostID = postID;
	}

	public String getPostContent() {
		return PostContent;
	}

	public void setPostContent(String postContent) {
		PostContent = postContent;
	}

	public Long getUploadBy() {
		return UploadedBy;
	}

	public void setUploadBy(Long uploadedBy) {
		UploadedBy = uploadedBy;
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

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}

	public boolean isIsPublic() {
		return IsPublic;
	}

	public void setIsPublic(boolean isPublic) {
		IsPublic = isPublic;
	}
}

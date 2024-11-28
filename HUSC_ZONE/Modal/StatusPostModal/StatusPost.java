package StatusPostModal;

import java.sql.Date;

public class StatusPost {
	private Long PostID;
	private String PostContent;
	private Long UploadBy;
	private Date CreatedAt;
	private Date UpdatedAt;
	public StatusPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusPost(Long postID, String postContent, Long uploadBy, Date createdAt, Date updatedAt) {
		super();
		PostID = postID;
		PostContent = postContent;
		UploadBy = uploadBy;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
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
		return UploadBy;
	}
	public void setUploadBy(Long uploadBy) {
		UploadBy = uploadBy;
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
}

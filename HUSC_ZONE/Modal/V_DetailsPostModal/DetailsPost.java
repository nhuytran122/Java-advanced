package V_DetailsPostModal;

import java.util.Date;

public class DetailsPost {
	private Long PostID;
	private String PostContent;
	private Long UploadedBy;
	private Date CreatedAt;
	private Date UpdatedAt;
	private String ImagePath;
	private String Name;
	private String Avatar;
	private Long CountLikes;
	private Long CountComments;
	private String PostVisibility;
	
	public DetailsPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailsPost(Long postID, String postContent, Long uploadedBy, Date createdAt, Date updatedAt,
			String imagePath, String name, String avatar, Long countLikes, Long countComments, String postVisibility) {
		super();
		PostID = postID;
		PostContent = postContent;
		UploadedBy = uploadedBy;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		ImagePath = imagePath;
		Name = name;
		Avatar = avatar;
		CountLikes = countLikes;
		CountComments = countComments;
		PostVisibility = postVisibility;
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
	public Long getUploadedBy() {
		return UploadedBy;
	}
	public void setUploadedBy(Long uploadedBy) {
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	public Long getCountLikes() {
		return CountLikes;
	}
	public void setCountLikes(Long countLikes) {
		CountLikes = countLikes;
	}
	public Long getCountComments() {
		return CountComments;
	}
	public void setCountComments(Long countComments) {
		CountComments = countComments;
	}
	public String getPostVisibility() {
		return PostVisibility;
	}
	public void setPostVisibility(String postVisibility) {
		PostVisibility = postVisibility;
	}
}

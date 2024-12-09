package V_DetailsCommentModal;

import java.util.Date;

public class DetailsComment {
	private Long CommentID;
	private String CommentContent;
	private Long PostID;
	private Long CommentedBy;
	private Date CommentedAt;
	private Date UpdatedAt;
	private String CommentedByName;
	private String CommentedByAvatar;
	private String PosterName;
	private String PosterAvatar;
	private Long PosterID;
	private String ImagePath;
	public DetailsComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailsComment(Long commentID, String commentContent, Long postID, Long commentedBy, Date commentedAt,
			Date updatedAt, String commentedByName, String commentedByAvatar, 
			String posterName, String posterAvatar, Long posterID, String imagePath) {
		super();
		CommentID = commentID;
		CommentContent = commentContent;
		PostID = postID;
		CommentedBy = commentedBy;
		CommentedAt = commentedAt;
		UpdatedAt = updatedAt;
		CommentedByName = commentedByName;
		CommentedByAvatar = commentedByAvatar;
		PosterName = posterName;
		PosterAvatar = posterAvatar;
		PosterID = posterID;
		ImagePath = imagePath;
	}
	public Long getCommentID() {
		return CommentID;
	}
	public void setCommentID(Long commentID) {
		CommentID = commentID;
	}
	public String getCommentContent() {
		return CommentContent;
	}
	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}
	public Long getPostID() {
		return PostID;
	}
	public void setPostID(Long postID) {
		PostID = postID;
	}
	public Long getCommentedBy() {
		return CommentedBy;
	}
	public void setCommentedBy(Long commentedBy) {
		CommentedBy = commentedBy;
	}
	public Date getCommentedAt() {
		return CommentedAt;
	}
	public void setCommentedAt(Date commentedAt) {
		CommentedAt = commentedAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	public String getCommentedByName() {
		return CommentedByName;
	}
	public void setCommentedByName(String commentedByName) {
		CommentedByName = commentedByName;
	}
	public String getCommentedByAvatar() {
		return CommentedByAvatar;
	}
	public void setCommentedByAvatar(String commentedByAvatar) {
		CommentedByAvatar = commentedByAvatar;
	}
	public String getPosterName() {
		return PosterName;
	}
	public void setPosterName(String posterName) {
		PosterName = posterName;
	}
	public String getPosterAvatar() {
		return PosterAvatar;
	}
	public void setPosterAvatar(String posterAvatar) {
		PosterAvatar = posterAvatar;
	}
	public Long getPosterID() {
		return PosterID;
	}
	public void setPosterID(Long posterID) {
		PosterID = posterID;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
}

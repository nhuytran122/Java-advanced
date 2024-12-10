package CommentModal;

import java.util.Date;

public class Comment {
	private Long CommentID;
	private String CommentContent;
	private Long PostID;
	private Long CommentedBy;
	private Date CommentedAt;
	private Date UpdatedAt;
	public Comment() {
		super();
	}
	public Comment(Long commentID, String commentContent, Long postID, Long commentedBy, Date commentedAt,
			Date updatedAt) {
		super();
		CommentID = commentID;
		CommentContent = commentContent;
		PostID = postID;
		CommentedBy = commentedBy;
		CommentedAt = commentedAt;
		UpdatedAt = updatedAt;
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
}

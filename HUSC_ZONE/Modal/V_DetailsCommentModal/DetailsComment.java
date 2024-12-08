package V_DetailsCommentModal;

import java.util.Date;

public class DetailsComment {
	private Long CommentID;
	private String CommentContent;
	private Long PostID;
	private Long CommentedBy;
	private Date CommentedAt;
	private Date UpdatedAt;
	private String Name;
	private String Avatar;
	public DetailsComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailsComment(Long commentID, String commentContent, Long postID, Long commentedBy, Date commentedAt,
			Date updatedAt, String name, String avatar) {
		super();
		CommentID = commentID;
		CommentContent = commentContent;
		PostID = postID;
		CommentedBy = commentedBy;
		CommentedAt = commentedAt;
		UpdatedAt = updatedAt;
		Name = name;
		Avatar = avatar;
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
}

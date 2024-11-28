package CommentModal;

public class Comment {
	private Long CommentID;
	private String CommentContent;
	private Long PostID;
	private Long UploadedBy;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(Long commentID, String commentContent, Long postID, Long uploadedBy) {
		super();
		CommentID = commentID;
		CommentContent = commentContent;
		PostID = postID;
		UploadedBy = uploadedBy;
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
	public Long getUploadedBy() {
		return UploadedBy;
	}
	public void setUploadedBy(Long uploadedBy) {
		UploadedBy = uploadedBy;
	}
}

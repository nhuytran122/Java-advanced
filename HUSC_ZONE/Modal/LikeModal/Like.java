package LikeModal;

import java.sql.Date;

public class Like {
	private Long LikeID;
	private Long PostID;
	private Date CreatedAt;
	private Long LikedBy;
	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Like(Long likeID, Long postID, Date createdAt, Long likedBy) {
		super();
		LikeID = likeID;
		PostID = postID;
		CreatedAt = createdAt;
		LikedBy = likedBy;
	}
	public Long getLikeID() {
		return LikeID;
	}
	public void setLikeID(Long likeID) {
		LikeID = likeID;
	}
	public Long getPostID() {
		return PostID;
	}
	public void setPostID(Long postID) {
		PostID = postID;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Long getLikedBy() {
		return LikedBy;
	}
	public void setLikedBy(Long likedBy) {
		LikedBy = likedBy;
	}
}

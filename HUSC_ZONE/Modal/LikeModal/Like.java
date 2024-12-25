// ! use
package LikeModal;

import java.util.Date;

public class Like {
	private Long LikeID;
	private Long PostID;
	private Date LikedAt;
	private Long LikedBy;

	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Like(Long likeID, Long postID, Date likedAt, Long likedBy) {
		super();
		LikeID = likeID;
		PostID = postID;
		LikedAt = likedAt;
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

	public Date getLikedAt() {
		return LikedAt;
	}

	public void setLikedAt(Date likedAt) {
		LikedAt = likedAt;
	}

	public Long getLikedBy() {
		return LikedBy;
	}

	public void setLikedBy(Long likedBy) {
		LikedBy = likedBy;
	}
}

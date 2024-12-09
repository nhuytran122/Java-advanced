package V_DetailsLikedModal;

import java.util.Date;

public class DetailsLiked {
	private Long LikeID;
	private Long PostID;
	private Date LikedAt;
	private Long LikedBy;
	private String PostContent;
	private String ImagePath;
	private String PosterName;
	private String PosterAvatar;
	private Long PosterID;

	public DetailsLiked() {
		super();
	}

	public DetailsLiked(Long likeID, Long postID, Date likedAt, Long likedBy, String postContent, String imagePath,
			String posterName, String avatar, Long posterID) {
		LikeID = likeID;
		PostID = postID;
		LikedAt = likedAt;
		LikedBy = likedBy;
		PostContent = postContent;
		ImagePath = imagePath;
		PosterName = posterName;
		PosterAvatar = avatar;
		PosterID = posterID;
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

	public String getPostContent() {
		return PostContent;
	}

	public void setPostContent(String postContent) {
		PostContent = postContent;
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
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

	public void setPosterAvatar(String avatar) {
		PosterAvatar = avatar;
	}

	public Long getPosterID() {
		return PosterID;
	}

	public void setPosterID(Long posterID) {
		PosterID = posterID;
	}
	
}

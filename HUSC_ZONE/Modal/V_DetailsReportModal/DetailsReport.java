package V_DetailsReportModal;

import java.util.Date;

public class DetailsReport {
	private Long ReportID;
	private String Reason;
	private Date CreatedAt;
	private Long CreatedBy;
	private Long PostID;
	private Long StatusID;
	private Long PosterID;
	private String PosterName;
	private String PosterAvatar;
	private String PostContent;
	private String ImagePath;
	private String Description;
	private Date SolvedAt;
	
	public DetailsReport(Long reportID, String reason, Date createdAt, Long createdBy, Long postID, Long statusID,
			Long posterID, String posterName, String posterAvatar, String postContent, String imagePath,
			String description, Date solvedAt) {
		super();
		ReportID = reportID;
		Reason = reason;
		CreatedAt = createdAt;
		CreatedBy = createdBy;
		PostID = postID;
		StatusID = statusID;
		PosterID = posterID;
		PosterName = posterName;
		PosterAvatar = posterAvatar;
		PostContent = postContent;
		ImagePath = imagePath;
		Description = description;
		SolvedAt = solvedAt;
	}
	public DetailsReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getReportID() {
		return ReportID;
	}
	public void setReportID(Long reportID) {
		ReportID = reportID;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Long getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(Long createdBy) {
		CreatedBy = createdBy;
	}
	public Long getPostID() {
		return PostID;
	}
	public void setPostID(Long postID) {
		PostID = postID;
	}
	public Long getStatusID() {
		return StatusID;
	}
	public void setStatusID(Long statusID) {
		StatusID = statusID;
	}
	public Long getPosterID() {
		return PosterID;
	}
	public void setPosterID(Long posterID) {
		PosterID = posterID;
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
	public String getDescriptionStatus() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getSolvedAt() {
		return SolvedAt;
	}
	public void setSolvedAt(Date solvedAt) {
		SolvedAt = solvedAt;
	}
}

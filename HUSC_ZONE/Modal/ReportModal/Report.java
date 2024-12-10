package ReportModal;

import java.util.Date;

public class Report {
	private Long ReportID;
	private String Reason;
	private Date CreatedAt;
	private Long CreatedBy;
	private Long PostID;
	private Long StatusID;
	private Date SolvedAt;
	public Report() {
		super();
	}
	public Report(Long reportID, String reason, Date createdAt, Long createdBy, Long postID, Long statusID, Date solvedAt) {
		super();
		ReportID = reportID;
		Reason = reason;
		CreatedAt = createdAt;
		CreatedBy = createdBy;
		PostID = postID;
		StatusID = statusID;
		SolvedAt = solvedAt;
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
	public Date getSolvedAt() {
		return SolvedAt;
	}
	public void setSolvedAt(Date solvedAt) {
		SolvedAt = solvedAt;
	}
}

package ReportModal;

import java.sql.Date;

public class Report {
	private Long ReportID;
	private String Reason;
	private Date CreatedAt;
	private Long CreatedBy;
	private Long DocumentID;
	private Long StatusID;
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Report(Long reportID, String reason, Date createdAt, Long createdBy, Long documentID, Long statusID) {
		super();
		ReportID = reportID;
		Reason = reason;
		CreatedAt = createdAt;
		CreatedBy = createdBy;
		DocumentID = documentID;
		StatusID = statusID;
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
	public Long getDocumentID() {
		return DocumentID;
	}
	public void setDocumentID(Long documentID) {
		DocumentID = documentID;
	}
	public Long getStatusID() {
		return StatusID;
	}
	public void setStatusID(Long statusID) {
		StatusID = statusID;
	}
}

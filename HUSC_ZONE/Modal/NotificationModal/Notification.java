// ! Use
package NotificationModal;

import java.util.Date;

public class Notification {
	private Long NotificationID;
	private Date CreatedAt;
	private Long CmtID;
	private Long ReportID;
	private Long ActivityTypeID;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Long notificationID, Date createdAt, Long cmtID, Long reportID,
			Long activityTypeID) {
		super();
		NotificationID = notificationID;
		CreatedAt = createdAt;
		CmtID = cmtID;
		ReportID = reportID;
		ActivityTypeID = activityTypeID;
	}
	public Long getNotificationID() {
		return NotificationID;
	}
	public void setNotificationID(Long notificationID) {
		NotificationID = notificationID;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Long getCmtID() {
		return CmtID;
	}
	public void setCmtID(Long cmtID) {
		CmtID = cmtID;
	}
	public Long getReportID() {
		return ReportID;
	}
	public void setReportID(Long reportID) {
		ReportID = reportID;
	}
	public Long getActivityTypeID() {
		return ActivityTypeID;
	}
	public void setActivityTypeID(Long activityTypeID) {
		ActivityTypeID = activityTypeID;
	}
}

// ! Use
package NotificationModal;

import java.util.Date;

public class Notification {
	private Long NotificationID;
	private Long SendTo;
	private Date CreatedAt;
	private Long CmtID;
	private Long ReportID;
	private Long ActorID;
	private Long ActivityTypeID;
	private Long PostID;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Long notificationID, Long sendTo, Date createdAt, Long cmtID, Long reportID, Long actorID,
			Long activityTypeID, Long postID) {
		super();
		NotificationID = notificationID;
		SendTo = sendTo;
		CreatedAt = createdAt;
		CmtID = cmtID;
		ReportID = reportID;
		ActorID = actorID;
		ActivityTypeID = activityTypeID;
		PostID = postID;
	}
	public Long getNotificationID() {
		return NotificationID;
	}
	public void setNotificationID(Long notificationID) {
		NotificationID = notificationID;
	}
	public Long getSendTo() {
		return SendTo;
	}
	public void setSendTo(Long sendTo) {
		SendTo = sendTo;
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
	public Long getActorID() {
		return ActorID;
	}
	public void setActorID(Long actorID) {
		ActorID = actorID;
	}
	public Long getActivityTypeID() {
		return ActivityTypeID;
	}
	public void setActivityTypeID(Long activityTypeID) {
		ActivityTypeID = activityTypeID;
	}
	public Long getPostID() {
		return PostID;
	}
	public void setPostID(Long postID) {
		PostID = postID;
	}
}

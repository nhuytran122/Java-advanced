package NotificationModal;

import java.sql.Date;

public class Notification {
	private Long NotificationID;
	private Long NotificationStatusID;
	private Long SendTo;
	private Date CreatedAt;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Long notificationID, Long notificationStatusID, Long userID, Date createdAt) {
		super();
		NotificationID = notificationID;
		NotificationStatusID = notificationStatusID;
		SendTo = userID;
		CreatedAt = createdAt;
	}
	public Long getNotificationID() {
		return NotificationID;
	}
	public void setNotificationID(Long notificationID) {
		NotificationID = notificationID;
	}
	public Long getNotificationStatusID() {
		return NotificationStatusID;
	}
	public void setNotificationStatusID(Long notificationStatusID) {
		NotificationStatusID = notificationStatusID;
	}
	public Long getUserID() {
		return SendTo;
	}
	public void setUserID(Long userID) {
		SendTo = userID;
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
}

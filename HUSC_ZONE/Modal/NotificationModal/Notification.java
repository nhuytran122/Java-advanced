package NotificationModal;

public class Notification {
	private Long NotificationID;
	private Long NotificationStatusID;
	private Long SendTo;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Long notificationID, Long notificationStatusID, Long userID) {
		super();
		NotificationID = notificationID;
		NotificationStatusID = notificationStatusID;
		SendTo = userID;
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
}

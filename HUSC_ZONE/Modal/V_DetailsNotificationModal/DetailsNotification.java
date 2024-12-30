package V_DetailsNotificationModal;

import java.util.Date;

import CommonModal.Constants;

public class DetailsNotification {
    private Long NotificationID;
    private Long SendTo;
    private Date CreatedAt;
    private Long PostID;
    private Long ActorID;
    private String ActorName;
    private String StatusContent;
    private String ActivityContent;
    private Long ActivityTypeID;
    
    public String getNotificationMessage() {
        if (ActivityTypeID == Constants.ACTIVITY_COMMENT) {
            return ActorName + " đã bình luận: \"" + ActivityContent + "\" trên bài viết \"" + StatusContent + "\".";
        } else if (ActivityTypeID == Constants.ACTIVITY_REPORT) {
            return "Một người đã báo cáo bài viết \"" + StatusContent + "\" với lý do: \"" + ActivityContent + "\".";
        } else if (ActivityTypeID == Constants.ACTIVITY_ACCEPTED_REPORT) {
            return "Bài viết của bạn đã bị khóa vì bị báo cáo với lý do: \"" + ActivityContent + "\".";
        } else if (ActivityTypeID == Constants.ACTIVITY_REJECTED_REPORT) {
            return "Báo cáo của bạn về bài viết \"" + StatusContent + "\" với lý do \"" + ActivityContent + "\" đã được xem xét và từ chối.";
        }

        return "Hoạt động không xác định.";
    }

	public DetailsNotification(Long notificationID, Long sendTo, Date createdAt, Long postID, Long actorID,
			String actorName, String statusContent, String activityContent, Long activityTypeID) {
		super();
		NotificationID = notificationID;
		SendTo = sendTo;
		this.CreatedAt = createdAt;
		PostID = postID;
		ActorID = actorID;
		ActorName = actorName;
		StatusContent = statusContent;
		ActivityContent = activityContent;
		ActivityTypeID = activityTypeID;
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
		this.CreatedAt = createdAt;
	}

	public Long getPostID() {
		return PostID;
	}

	public void setPostID(Long postID) {
		PostID = postID;
	}

	public Long getActorID() {
		return ActorID;
	}

	public void setActorID(Long actorID) {
		ActorID = actorID;
	}

	public String getActorName() {
		return ActorName;
	}

	public void setActorName(String actorName) {
		ActorName = actorName;
	}

	public String getStatusContent() {
		return StatusContent;
	}

	public void setStatusContent(String statusContent) {
		StatusContent = statusContent;
	}

	public String getActivityContent() {
		return ActivityContent;
	}

	public void setActivityContent(String activityContent) {
		ActivityContent = activityContent;
	}

	public Long getActivityTypeID() {
		return ActivityTypeID;
	}

	public void setActivityTypeID(Long activityTypeID) {
		ActivityTypeID = activityTypeID;
	}
}

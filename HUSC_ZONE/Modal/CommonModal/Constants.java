package CommonModal;

public class Constants {

    // User Roles
    public static final Long ROLE_USER = 1L; 
    public static final Long ROLE_ADMIN = 2L;

    // Account Statuses
    public static final boolean ACC_LOCKED = false;
    public static final boolean ACC_USING = true;

    // Report Statuses
    public static final Long REPORT_REJECTED = -1L;
    public static final Long REPORT_PENDING = 0L;
    public static final Long REPORT_ACCEPTED = 1L;

    // Notifications - Documents
    public static final Long NOTI_DOCS_DELETED = -4L;
    public static final Long NOTI_DOCS_REPORTED = -3L;
    public static final Long NOTI_DOCS_MARKED = 1L;

    // Notifications - Posts
    public static final Long NOTI_POST_DELETED = -2L;
    public static final Long NOTI_POST_REPORTED = -1L;
    public static final Long NOTI_POST_LIKED = 2L;
    public static final Long NOTI_POST_COMMENTED = 3L;
    
    // Error - Change Password
    public static final Long WRONG_PASSWORD = -2L;
    public static final Long DUPLICATE_PASSWORD = -1L;
    public static final Long CHANGE_PASSWORD_SUCCESS = 1L;
    
    public static final String IMG_POST_FOLDER_PATH = "/images/status_images/";
    public static final String IMG_AVT_USER_FOLDER_PATH = "/images/avt_users/";
    public static final String DOCS_FOLDER_PATH = "/docs/";
    
    public static final Long SEARCH_USER = 1L;
    public static final Long SEARCH_POST = 2L;
    
    public static final Long FILTER_LIKED = 1L;
    public static final Long FILTER_COMMENTED = 2L;
}

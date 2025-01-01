package CommonModal;

public class Constants {
	
	public static final int PAGE_SIZE = 8;
    // User Roles
    public static final Long ROLE_USER = 1L; 
    public static final Long ROLE_ADMIN = 2L;

    // Account Statuses
    public static final boolean ACC_LOCKED = false;
    public static final boolean ACC_USING = true;

    // Report Statuses
    public static final Long REPORT_REJECTED = -1L;
    public static final Long REPORT_PENDING = 1L;
    public static final Long REPORT_ACCEPTED = 2L;

    // Error - Change Password
    public static final Long WRONG_PASSWORD = -2L;
    public static final Long DUPLICATE_PASSWORD = -1L;
    public static final Long CHANGE_PASSWORD_SUCCESS = 1L;
    
    public static final String IMG_POST_FOLDER_PATH = "/images/status_images/";
    public static final String IMG_AVT_USER_FOLDER_PATH = "/images/avt_users/";
    public static final String IMG_CATEGORY_FOLDER_PATH = "/images/categories/";
    public static final String DOCS_FOLDER_PATH = "/docs/";
    
    public static final Long SEARCH_USER = 1L;
    public static final Long SEARCH_POST = 2L;
    
    public static final Long FILTER_LIKED = 1L;
    public static final Long FILTER_COMMENTED = 2L;
    public static final Long FILTER_REPORT = 3L;
    
    //SHOW NOTI
    public static final Long ACTIVITY_COMMENT = 1L;
    public static final Long ACTIVITY_REPORT = 2L;
    public static final Long ACTIVITY_ACCEPTED_REPORT = 3L;
    public static final Long ACTIVITY_REJECTED_REPORT = 4L;
    
    public static final boolean POST_LOCKED = false;
    public static final boolean POST_NOTLOCK = true;
    
    public static final String POST_PUBLIC = "Public";
    public static final String POST_PRIVATE = "Private";
}

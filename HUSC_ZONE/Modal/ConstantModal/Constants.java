package ConstantModal;

public class Constants {
	public static final Long ROLE_USER = 0L;  // sử dụng "L" để thể thị giá trị Long
    public static final Long ROLE_ADMIN = 1L;
    
    public static final Long STATUS_ACC_LOCKED = -1L;
    public static final Long STATUS_ACC_USING = 1L; 
     
    public static final Long STATUS_DENIED = -1L;
    public static final Long STATUS_PENDING = 0L; 
    public static final Long STATUS_UPLOADED = 1L; 
    public static final Long STATUS_ACCEPTED = 2L;
    
    public static final Long NOTI_DELETED = -3L;
    public static final Long NOTI_REPORTED = -2L;
    public static final Long NOTI_DENIED = -1L;
    public static final Long NOTI_ACCEPTED = 1L;
    public static final Long NOTI_LIKED = 2L; 
    public static final Long NOTI_MARKED = 3L;
    public static final Long NOTI_COMMENTED = -2L;
    
}

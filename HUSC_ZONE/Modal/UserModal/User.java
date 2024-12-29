package UserModal;

import java.util.Date;

public class User {
	private Long UserID;
	private String Name;
	private String Password;
	private String Gender;
	private String Email;
	private String Phone;
	private boolean IsUsing;
	private Date CreatedAt;
	private Date UpdatedAt;
	private Long RoleID;
	private String Avatar;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userID, String name, String password, String gender, String email, String phone,
			boolean isUsing, Date createdAt, Date updatedAt, Long roleID, String avatar) {
		super();
		UserID = userID;
		Name = name;
		Password = password;
		Gender = gender;
		Email = email;
		Phone = phone;
		IsUsing = isUsing;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		RoleID = roleID;
		Avatar = avatar;
	}
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public boolean isIsUsing() {
		return IsUsing;
	}
	public void setIsUsing(boolean isUsing) {
		IsUsing = isUsing;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	public Long getRoleID() {
		return RoleID;
	}
	public void setRoleID(Long roleID) {
		RoleID = roleID;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}	
}
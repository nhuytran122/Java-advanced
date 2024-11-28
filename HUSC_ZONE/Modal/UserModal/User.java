package UserModal;

import java.util.Date;

public class User {
	private Long UserID;
	private String Name;
	private String Password;
	private String Gender;
	private String Email;
	private String Phone;
	private Date Birthdate;
	private boolean Status;
	private Date CreatedAt;
	private Date UpdatedAt;
	private Long RoleID;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userID, String name, String password, String gender, String email, String phone, Date birthdate,
			boolean status, Date createdAt, Date updatedAt, Long roleID) {
		super();
		UserID = userID;
		Name = name;
		Password = password;
		Gender = gender;
		Email = email;
		Phone = phone;
		Birthdate = birthdate;
		Status = status;
		CreatedAt = createdAt;
		UpdatedAt = updatedAt;
		RoleID = roleID;
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
	public Date getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(Date birthdate) {
		Birthdate = birthdate;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
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
	
	
}
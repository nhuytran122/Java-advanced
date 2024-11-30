package UserModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.*;

public class UserDao {
	public ArrayList<User> getListUsers() throws Exception{
		ArrayList<User> ds = new ArrayList<User>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from tbl_Users";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			Long userID = rs.getLong("UserID");
			String name = rs.getString("Name");
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			String email = rs.getString("Email");
			String phone = rs.getString("Phone");
			boolean status = rs.getBoolean("Status");
			Date createdAt = rs.getDate("CreatedAt");
			Date updatedAt = rs.getDate("UpdatedAt");
			Long roleID = rs.getLong("RoleID");
			String avatar = rs.getString("Avatar");
			ds.add(new User(userID, name, password, gender, email, phone, status, createdAt, updatedAt, roleID, avatar));
		} 
		rs.close();
		kn.cn.close();
		return ds;
	}
	
	public User getUser() throws Exception{
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select * from tbl_Users WHERE";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		User user = null;
		while(rs.next()) {
			Long userID = rs.getLong("UserID");
			String name = rs.getString("Name");
			String password = rs.getString("Password");
			String gender = rs.getString("Gender");
			String email = rs.getString("Email");
			String phone = rs.getString("Phone");
			boolean status = rs.getBoolean("Status");
			Date createdAt = rs.getDate("CreatedAt");
			Date updatedAt = rs.getDate("UpdatedAt");
			Long roleID = rs.getLong("RoleID");
			String avatar = rs.getString("Avatar");
			user = new User(userID, name, password, gender, email, phone, status, createdAt, updatedAt, roleID, avatar);
		} 
		rs.close();
		kn.cn.close();
		return user;
	}
	
	public User getUserWithEmail(String email) throws Exception {
	    KetNoi kn = new CommonModal.KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM tbl_Users WHERE Email = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, email);
	    ResultSet rs = cmd.executeQuery();

	    User user = null;
	    if (rs.next()) {
	    	Long userID = rs.getLong("UserID");
			String name = rs.getString("Name");
			String gender = rs.getString("Gender");
			String phone = rs.getString("Phone");
			boolean status = rs.getBoolean("Status");
			Date createdAt = rs.getDate("CreatedAt");
			Date updatedAt = rs.getDate("UpdatedAt");
			Long roleID = rs.getLong("RoleID");
			String password = rs.getString("password");
			String avatar = rs.getString("Avatar");
			user = new User(userID, name, password, gender, email, phone, status, createdAt, updatedAt, roleID, avatar);
	    }
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return user;
	}
	
	public int addUser(String name, String password, String gender, String email, 
			String phone, Long roleID) throws Exception {
		
		if (getUserWithEmail(email) != null) {
	        return -1;
	    }
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO tbl_Users (Name, Password, Gender, Email, Phone, Status, CreatedAt, RoleID) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?);";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
            
            cmd.setString(1, name);
            cmd.setString(2, password);
            cmd.setString(3, gender);
            cmd.setString(4, email);
            cmd.setString(5, phone);
            cmd.setBoolean(6, Constants.ACC_USING);
            cmd.setLong(7, roleID);
        int kq = cmd.executeUpdate();
        kn.cn.close();
            
        return kq;
    }
	
	public int countUsers() throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT COUNT(UserID) FROM tbl_Users";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    ResultSet rs = cmd.executeQuery();

	    int count = 0;
	    if (rs.next()) {
	        count = rs.getInt(1);
	    }
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return count;
	}
	
	public int updateUser(Long userID, String name, String gender, 
			String phone, boolean status, Long roleID, String avatar) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Users SET Name = ?, Gender = ?, Phone = ?, "
        		+ "Status = ?, UpdatedAt = GETDATE(), RoleID = ?, Avatar = ? WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setString(1, name);
        cmd.setString(2, gender);
        cmd.setString(3, phone);
        cmd.setBoolean(4, status);
        cmd.setLong(5, roleID);
        cmd.setLong(6, userID);
        cmd.setString(7, avatar);
        int kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }

    public int changePassword(Long userID, String newPassword) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Users SET Password = ? WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setString(1, newPassword);
        cmd.setLong(2, userID);

        int kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }

    public int deleteUser(Long userID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Users WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setLong(1, userID);

        int kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
}

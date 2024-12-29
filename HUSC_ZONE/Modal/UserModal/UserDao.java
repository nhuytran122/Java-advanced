package UserModal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import CommonModal.*;

public class UserDao {
	public ArrayList<User> getListUsers() throws Exception {
        ArrayList<User> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Users";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            ds.add(mapUser(rs));
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }
	
	public ArrayList<User> getListUserByCondition(int page, int pageSize, String searchValue) throws Exception {
	    ArrayList<User> ds = new ArrayList<User>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT * " +
	            "FROM tbl_Users " +
	            "WHERE Name LIKE ? OR Email = ? " +
	            "ORDER BY CreatedAt DESC " +
	            "OFFSET (? - 1) * ? ROWS " +
	            "FETCH NEXT ? ROWS ONLY";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, "%" + searchValue + "%");
	    cmd.setString(2, searchValue);
	    cmd.setInt(3, page);
	    cmd.setInt(4, pageSize);
	    cmd.setInt(5, pageSize);

	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        ds.add(mapUser(rs));
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();

	    return ds;
	}
	
	public User getUserByID(Long userID) throws Exception {
        User user = null;
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Users WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        ResultSet rs = cmd.executeQuery();

        if (rs.next()) {
            user = mapUser(rs);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return user;
    }
	
	public User getUserByEmail(String email) throws Exception {
        User user = null;
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Users WHERE Email = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setString(1, email);
        ResultSet rs = cmd.executeQuery();

        if (rs.next()) {
            user = mapUser(rs);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return user;
    }
	
	public int addUser(String name, String password, String gender, String email, 
			String phone, Long roleID) throws Exception {
		
		if (getUserByEmail(email) != null) {
	        return -1;
	    }
        KetNoi kn = new KetNoi();
		kn.ketnoi();
        String sql = "INSERT INTO tbl_Users (Name, Password, Gender, Email, Phone, IsUsing, CreatedAt, RoleID) "
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
	
	public int countUsersByCondition(String searchValue) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT COUNT(UserID) " +
	            "FROM tbl_Users " +
	            "WHERE Name LIKE ? OR Email = ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, "%" + searchValue + "%");
	    cmd.setString(2, searchValue);

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
			String phone, String avatar) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Users SET Name = ?, Gender = ?, Phone = ?, "
        		+ "UpdatedAt = GETDATE(), Avatar = ? WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setString(1, name);
        cmd.setString(2, gender);
        cmd.setString(3, phone);
        cmd.setString(4, avatar);
        cmd.setLong(5, userID);  
        int kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
	
	public int updateStatusAndRoleUser(Long userID, boolean status, Long roleID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Users SET IsUsing = ?, UpdatedAt = GETDATE(), RoleID = ?"
        		+ " WHERE UserID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setBoolean(1, status);
        cmd.setLong(2, roleID);
        cmd.setLong(3, userID);  
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
    
    private User mapUser(ResultSet rs) throws Exception {
        Long userID = rs.getLong("UserID");
        String name = rs.getString("Name");
        String password = rs.getString("Password");
        String gender = rs.getString("Gender");
        String email = rs.getString("Email");
        String phone = rs.getString("Phone");
        boolean isUsing = rs.getBoolean("IsUsing");
        Date createdAt = rs.getDate("CreatedAt");
        Date updatedAt = rs.getDate("UpdatedAt");
        Long roleID = rs.getLong("RoleID");
        String avatar = rs.getString("Avatar");
        return new User(userID, name, password, gender, email, phone, isUsing, createdAt, updatedAt, roleID, avatar);
    }
}

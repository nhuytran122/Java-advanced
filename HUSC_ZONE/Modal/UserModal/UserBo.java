package UserModal;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import CommonModal.Constants;

public class UserBo {

	UserDao userDao = new UserDao();
	
	public ArrayList<User> getListUsers() throws Exception{
		return userDao.getListUsers();
	}
	
	public ArrayList<User> getListUserByCondition(int page, int pageSize, String searchValue) throws Exception {
		return userDao.getListUserByCondition(page, pageSize, searchValue);
	}
	
	public User getUserByID(Long userID) throws Exception {
		return userDao.getUserByID(userID);
	}
	
	public User checkLogin(String email, String password) throws Exception {
	    User user = userDao.getUserByEmail(email);
	    
	    if (user == null) {
	        return null;
	    }
	    if (BCrypt.checkpw(password, user.getPassword())) {
	        return user; 
	    } else {
	        return null; 
	    }
	}

	public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
	
	public int addUser(String name, String password, String gender, String email, 
			String phone, Long roleID) throws Exception {
		return userDao.addUser(name, hashPassword(password), gender, email, phone, roleID);
	}
	
	public int countUsers() throws Exception {
		return userDao.countUsers();
	}
	
	public int countUsersByCondition(String searchValue) throws Exception {
		return userDao.countUsersByCondition(searchValue);
	}
	
	public int updateUser(Long userID, String name, String gender, 
			String phone, String avatar) throws Exception {
		return userDao.updateUser(userID, name, gender, phone, avatar);
	}
	
	public int updateStatusAndRoleUser(Long userID, boolean status, Long roleID) throws Exception {
		return userDao.updateStatusAndRoleUser(userID, status, roleID);
	}
	
	public Long changePassword(String email, String oldPassword, String newPassword) throws Exception {
        User user = userDao.getUserByEmail(email);
        
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            return Constants.WRONG_PASSWORD;
        }

        if (BCrypt.checkpw(newPassword, user.getPassword())) {
            return Constants.DUPLICATE_PASSWORD;
        }

        String hashedNewPassword = hashPassword(newPassword);
        userDao.changePassword(user.getUserID(), hashedNewPassword);
        return Constants.CHANGE_PASSWORD_SUCCESS;
    }
	
	public int deleteUser(Long userID) throws Exception {
		return userDao.deleteUser(userID);
	}
}

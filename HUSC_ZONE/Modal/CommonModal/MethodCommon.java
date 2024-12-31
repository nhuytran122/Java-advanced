package CommonModal;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import MaterialModal.Material;
import MaterialModal.MaterialBo;
import UserModal.User;
import V_DetailsNotificationModal.DetailsNotification;
import V_DetailsNotificationModal.DetailsNotificationBo;

public class MethodCommon {

	public static ArrayList<Category> getListCates() throws Exception {
        CategoryBo cateBo = new CategoryBo();
        return cateBo.getListCategories();
    }
	
	public static ArrayList<Material> getListMates() throws Exception {
        MaterialBo mateBo = new MaterialBo();
        return mateBo.getListMaterials();
    }
	
	public static ArrayList<DetailsNotification> getListNotis(Long userID) throws Exception {
        DetailsNotificationBo notiBo = new DetailsNotificationBo();
        return notiBo.getNotificationsByUserID(userID);
    }
	
	public static String convertDateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDay = sdf.format(d);
		return strDay;
	}
	
	public static int calculatePageCount(int rowCount, int pageSize) {
    	int pageCount = rowCount / pageSize;
        if (rowCount % pageSize > 0) {
            pageCount += 1;
        }
        return pageCount;
    }
	
	public static User getUserFromSession(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("user") == null) {
            return null;
        }
        return (User) session.getAttribute("user");
    }
	
	public static boolean checkAdminAccess(User user, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
	    if (user != null) {
	        if (user.getRoleID() != Constants.ROLE_ADMIN) {
	            RequestDispatcher rd = request.getRequestDispatcher("/User/access-denied.jsp");
	            rd.forward(request, response);
	            return false; 
	        }
	    }
	    return true; 
	}
	
	public static boolean checkLoginAndAdminAccess(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
	    User user = getUserFromSession(session, response);
	    if (user == null) {
	        RequestDispatcher rd = request.getRequestDispatcher("/User/login.jsp");
	        rd.forward(request, response);
	        return false; 
	    }

	    if (!checkAdminAccess(user, session, response, request)) {
	        return false;
	    }

	    return true;
	}


}

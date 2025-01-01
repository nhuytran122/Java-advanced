package CommonModal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UserModal.User;

public class ControllerUtils {
	
	public static User getUserFromSession(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("user") == null) {
            return null;
        }
        return (User) session.getAttribute("user");
    }
	
	public static boolean ensureUserLogin(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
	    if (getUserFromSession(session, response) == null) {
	        RequestDispatcher rd = request.getRequestDispatcher("/User/login.jsp");
	        rd.forward(request, response);
	        return false; 
	    }
	    return true;
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
	
    public static int getPage(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        return (pageParam != null) ? Integer.parseInt(pageParam) : 1;
    }

    public static String getSearchValue(HttpServletRequest request) {
        if (request.getParameter("btn-search") != null && request.getParameter("txtSearch") != null) {
            return request.getParameter("txtSearch");
        }
        return "";
    }

    public static void setPaginationAttributes(HttpServletRequest request, int page, int pageCount) {
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", page);
    }

    public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }
}

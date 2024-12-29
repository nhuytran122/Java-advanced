package ControllerAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.MethodCommon;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/admin/users")
public class ShowUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
//TODO
//            if (MethodCommon.getAdminFromSession(session, response) == null) {
//    	        response.sendRedirect("login");
//    	        return;
//    	    }
            
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
            int rowCount = 0;
            

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }
            
            UserBo userBo = new UserBo();
            
            ArrayList<User> dsUsers = null;
            dsUsers = userBo.getListUserByCondition(page, pageSize, searchValue);
            rowCount = userBo.countUsersByCondition(searchValue);
            int pageCount = MethodCommon.calculatePageCount(rowCount, pageSize);
            
            request.setAttribute("dsUsers", dsUsers);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("/Admin/show-users.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

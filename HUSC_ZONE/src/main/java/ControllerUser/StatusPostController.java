package ControllerUser;

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
import UserModal.User;
import UserModal.UserBo;
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/status-post")
public class StatusPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StatusPostController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }
            
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
            int rowCount = 0;
            
            Long typeSearchID = Constants.SEARCH_POST;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }
            
            if (request.getParameter("typeSearchID") != null) {
            	typeSearchID = Long.parseLong(request.getParameter("typeSearchID"));
            }
            
            DetailsPostBo sttBo = new DetailsPostBo();
            UserBo userBo = new UserBo();
            
            ArrayList<DetailsPost> dsPosts = null;
            ArrayList<User> dsUsers = null;
            if(typeSearchID == Constants.SEARCH_POST) {
            	dsPosts = sttBo.getPostsByConditions(page, pageSize, searchValue);
            	rowCount = sttBo.getCountPostsByConditions(searchValue);
            }
            else {
            	dsUsers = userBo.getListUserByCondition(page, pageSize, searchValue);
            	rowCount = userBo.countUsersByCondition(searchValue);
            }
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }
            
            request.setAttribute("dsPosts", dsPosts);
            request.setAttribute("dsUsers", dsUsers);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("searchKeyword", searchValue);

            RequestDispatcher rd = request.getRequestDispatcher("User/show-status.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

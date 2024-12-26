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

import CommonModal.MethodCommon;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/docs-of-user")
public class ShowDocsOfUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowDocsOfUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            
            User currentUser = null;
            Long posterID = 0L;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }
            else {
            	currentUser = (User)session.getAttribute("user");
            	posterID = currentUser.getUserID();
            }

            DetailsDocBo dtdocBo = new DetailsDocBo();
            UserBo userBo = new UserBo();
            
            int page = 1;
            int pageSize = 9;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("posterID") != null) {
            	posterID = Long.parseLong(request.getParameter("posterID"));
            }
            
            ArrayList<DetailsDoc> ds = dtdocBo.getDocsByUserIDPagination(page, pageSize, posterID);
            User poster = userBo.getUserByID(posterID);
            		
            int rowCount = dtdocBo.getCountDocsByUserID(posterID);
            
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }

            request.setAttribute("ds", ds);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("namePoster", poster.getName());
            request.setAttribute("IDPoster", poster.getUserID());

            RequestDispatcher rd = request.getRequestDispatcher("User/docs-of-user.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

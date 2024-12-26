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

import BookmarkModal.BookmarkBo;
import CommonModal.MethodCommon;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsBookmarkModal.DetailsBookmark;
import V_DetailsBookmarkModal.DetailsBookmarkBo;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/liked-docs")
public class LikedDocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikedDocsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            
            User currentUser = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }
            else
            	currentUser = (User)session.getAttribute("user");

            DetailsBookmarkBo dtBmBo = new DetailsBookmarkBo();
            
            int page = 1;
            int pageSize = 9;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            ArrayList<DetailsBookmark> ds = dtBmBo.getListBookmarksByUserID(page, pageSize, currentUser.getUserID());
            		
            int rowCount = dtBmBo.getCountBookmarksByUserID(currentUser.getUserID());
            
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }

            request.setAttribute("ds", ds);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("User/liked-docs.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

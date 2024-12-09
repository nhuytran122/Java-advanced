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
import V_DetailsCommentModal.DetailsComment;
import V_DetailsCommentModal.DetailsCommentBo;
import V_DetailsLikedModal.DetailsLiked;
import V_DetailsLikedModal.DetailsLikedBo;

@WebServlet("/activity-history")
public class ActivityHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActivityHistoryController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }
            User user = (User)session.getAttribute("user");
            Long userID = user.getUserID();
            
            int page = 1;
            int pageSize = 9;
            int rowCount = 0;
            
            Long filterID = Constants.FILTER_LIKED;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            if (request.getParameter("filterID") != null) {
            	filterID = Long.parseLong(request.getParameter("filterID"));
            }
            else if(request.getAttribute("filterID") != null) {
            	filterID = (Long)(request.getAttribute("filterID"));
            }
            
            DetailsLikedBo dtLBo = new DetailsLikedBo();
            DetailsCommentBo dtCBo = new DetailsCommentBo();
            
            ArrayList<DetailsLiked> dsLikes = null;
            ArrayList<DetailsComment> dsCmts = null;
            if(filterID == Constants.FILTER_LIKED) {
            	dsLikes = dtLBo.getListLikesByUserID(page, pageSize, userID);
            	rowCount = dtLBo.getCountLikesByUserID(userID);
            }
            else {
            	dsCmts = dtCBo.getCommentsByUserID(page, pageSize, userID);
            	rowCount = dtCBo.getCountCommentsByUserID(userID);
            }
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }
            
            request.setAttribute("dsLikes", dsLikes);
            request.setAttribute("dsCmts", dsCmts);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("User/liked-status.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

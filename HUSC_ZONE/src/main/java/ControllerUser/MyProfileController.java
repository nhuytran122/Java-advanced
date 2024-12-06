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

import DocumentModal.DocumentBo;
import StatusPostModal.StatusPost;
import StatusPostModal.StatusPostBo;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/my-profile")
public class MyProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyProfileController() {
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
            DetailsPostBo dtSttBo = new DetailsPostBo();
            DetailsDocBo dtDocBo = new DetailsDocBo();
            
            int currentPagePosts = 1, currentPageDocs =  1;
            int pageSize = 9;

            if (request.getParameter("pagePosts") != null) {
            	currentPagePosts = Integer.parseInt(request.getParameter("pagePosts"));
            }
            if (request.getParameter("pageDocs") != null) {
            	currentPageDocs = Integer.parseInt(request.getParameter("pageDocs"));
            }

            ArrayList<DetailsPost> dsStt = dtSttBo.getPostsByUserID(currentPagePosts, pageSize, user.getUserID());
            ArrayList<DetailsDoc> dsDocs = dtDocBo.getDocsByUserID(currentPageDocs, pageSize, user.getUserID());

            int rowCountStt = dtSttBo.getCountPostsByConditions("");
            int rowCountDocs = dtDocBo.getCountDocsByConditions("", 0L, 0L);
            
            int pageCountPosts = rowCountStt / pageSize;
            int pageCountDocs = rowCountDocs / pageSize;
            
            if (rowCountStt % pageSize > 0) {
            	pageCountPosts += 1;
            }
            
            if (rowCountDocs % pageSize > 0) {
            	pageCountDocs += 1;
            }

            request.setAttribute("dsStt", dsStt);
            request.setAttribute("pageCountPosts", pageCountPosts);
            request.setAttribute("currentPagePosts", currentPagePosts);

            request.setAttribute("dsDocs", dsDocs);
            request.setAttribute("pageCountDocs", pageCountDocs);
            request.setAttribute("currentPageDocs", currentPagePosts);

            RequestDispatcher rd = request.getRequestDispatcher("User/my-profile.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

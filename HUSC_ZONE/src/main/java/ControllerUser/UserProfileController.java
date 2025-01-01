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
import CommonModal.ControllerUtils;
import CommonModal.MethodCommon;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/user-profile")
public class UserProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserProfileController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (!ControllerUtils.ensureUserLogin(session, response, request)) {
				return;
			}
            User currentUser = ControllerUtils.getUserFromSession(session, response);
            
            User user = null;
            Long userID = 0L;
            UserBo userBo = new UserBo();
            boolean isGuest = false;

            if (request.getParameter("userId") != null) {
                userID = Long.parseLong(request.getParameter("userId"));
                user = userBo.getUserByID(userID);
                isGuest = true;
            } else if (request.getAttribute("userId") != null) {
                userID = (Long) request.getAttribute("userId");
                user = userBo.getUserByID(userID);
                isGuest = true;
            } else {
                user = currentUser;
            }

            DetailsPostBo dtSttBo = new DetailsPostBo();
            DetailsDocBo dtDocBo = new DetailsDocBo();

            int page = ControllerUtils.getPage(request);
            
            ArrayList<DetailsPost> dsStt = dtSttBo.getPostsByUserID(page, Constants.PAGE_SIZE, user.getUserID());
            ArrayList<DetailsDoc> dsDocs = dtDocBo.getListDocsByUserID(user.getUserID());

            int rowCountStt = dtSttBo.getCountPostsByConditions("");

            int pageCountPosts = MethodCommon.calculatePageCount(rowCountStt, Constants.PAGE_SIZE);

            request.setAttribute("dsStt", dsStt);
            ControllerUtils.setPaginationAttributes(request, page, pageCountPosts);
            request.setAttribute("dsDocs", dsDocs);

            RequestDispatcher rd = null;

            if (isGuest) {
                if (user.getUserID() == currentUser.getUserID()) {
                    rd = request.getRequestDispatcher("User/my-profile.jsp");
                } else {
                    rd = request.getRequestDispatcher("User/user-profile.jsp");
                    
                    request.setAttribute("targetUser", user);
                }
            } else
                rd = request.getRequestDispatcher("User/my-profile.jsp");
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

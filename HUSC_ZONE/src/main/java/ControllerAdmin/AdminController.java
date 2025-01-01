package ControllerAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ControllerUtils;
import ReportModal.ReportBo;
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDocBo;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
            request.setAttribute("cntDocs", (new DetailsDocBo().getCountDocsByConditions("", 0L, 0L)));
            request.setAttribute("cntUsers", (new UserBo().countUsersByCondition("")));
            request.setAttribute("cntPosts", (new DetailsPostBo().getCountPostsByConditions("")));
            request.setAttribute("cntPendingReports", (new ReportBo().getCountPendingReports()));

            ControllerUtils.forwardRequest(request, response, "/Admin/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

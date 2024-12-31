package ControllerAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDocBo;
import V_DetailsPostModal.DetailsPostBo;
import V_DetailsReportModal.DetailsReportBo;

@WebServlet("/admin/details")
public class ViewDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
            if (!MethodCommon.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
			if (request.getParameter("docID") != null) {
                Long docID = Long.parseLong(request.getParameter("docID"));
                request.setAttribute("docs", new DetailsDocBo().getDetailsDocByID(docID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-docs.jsp");
                rd.forward(request, response);
                return;
			}
			else if(request.getParameter("postID") != null) {
                Long postID = Long.parseLong(request.getParameter("postID"));
                request.setAttribute("post", new DetailsPostBo().getDetailsPostByID(postID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-post.jsp");
                rd.forward(request, response);
                return;
			}
			else if(request.getParameter("reportID") != null || request.getAttribute("reportID") != null) {
				Long reportID = request.getParameter("reportID") != null 
		                ? Long.parseLong(request.getParameter("reportID")) 
		                : (Long) request.getAttribute("reportID");
                request.setAttribute("report", new DetailsReportBo().getReportByID(reportID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-report.jsp");
                rd.forward(request, response);
                return;
			}
			else if(request.getParameter("userID") != null) {
                Long userID = Long.parseLong(request.getParameter("userID"));
                request.setAttribute("user", new UserBo().getUserByID(userID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-user.jsp");
                rd.forward(request, response);
                return;
			}
            else if(request.getParameter("profile") != null){
            	RequestDispatcher rd = request.getRequestDispatcher("/Admin/my-profile.jsp");
                rd.forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

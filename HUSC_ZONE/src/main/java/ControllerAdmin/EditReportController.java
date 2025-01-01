package ControllerAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import NotificationModal.NotificationBo;
import ReportModal.ReportBo;
import StatusPostModal.StatusPostBo;

@WebServlet("/admin/edit-report")
public class EditReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditReportController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
        	Long reportID = null;
        	if (request.getParameter("reportID") != null) 
        		reportID =  Long.parseLong(request.getParameter("reportID"));
            if (reportID == null) {
                response.sendRedirect("reports");
                return;
            }
            ReportBo rpBo = new ReportBo();
            NotificationBo notiBo = new NotificationBo();
            
            if (request.getParameter("btnApproveReport") != null) {
                handleApproveReport(reportID, request, rpBo, notiBo);
            } else if (request.getParameter("btnRejectReport") != null) {
                handleRejectReport(reportID, notiBo);
            } else if (request.getParameter("btnDeleteReport") != null) {
                handleDeleteReport(reportID, response, rpBo);
                return; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleApproveReport(Long reportID, HttpServletRequest 
    		request, ReportBo rpBo, NotificationBo notiBo) throws Exception {
        StatusPostBo postBo = new StatusPostBo();
        rpBo.updateReport(reportID, Constants.REPORT_ACCEPTED);
        Long postID = Long.parseLong(request.getParameter("btnApproveReport"));
        postBo.updateVisibilityStatusPost(postID, Constants.POST_LOCKED);
        notiBo.createNotiRelatedToHandleReport(reportID, Constants.ACTIVITY_ACCEPTED_REPORT);
    }

    private void handleRejectReport(Long reportID, NotificationBo notiBo) throws Exception {
        ReportBo rpBo = new ReportBo();
        rpBo.updateReport(reportID, Constants.REPORT_REJECTED);
        notiBo.createNotiRelatedToHandleReport(reportID, Constants.ACTIVITY_REJECTED_REPORT);
    }

    private void handleDeleteReport(Long reportID, HttpServletResponse response, ReportBo rpBo) throws Exception {
        rpBo.deleteReport(reportID);
        response.sendRedirect("reports"); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package ControllerAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CommonModal.Constants;
import ReportModal.ReportBo;
import StatusPostModal.StatusPostBo;
import V_DetailsReportModal.DetailsReportBo;

@WebServlet("/admin/edit-report")
public class EditReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditReportController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Long reportID = null;
        	if (request.getParameter("reportID") != null) 
        		reportID =  Long.parseLong(request.getParameter("reportID"));
            if (reportID == null) {
                response.sendRedirect("reports");
                return;
            }
            ReportBo rpBo = new ReportBo();

            if (request.getParameter("btnApproveReport") != null) {
                handleApproveReport(reportID, request, rpBo);
            } else if (request.getParameter("btnRejectReport") != null) {
                handleRejectReport(reportID);
            } else if (request.getParameter("btnDeleteReport") != null) {
                handleDeleteReport(reportID, response, rpBo);
                return; 
            }
            showReportDetails(reportID, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleApproveReport(Long reportID, HttpServletRequest request, ReportBo rpBo) throws Exception {
        StatusPostBo postBo = new StatusPostBo();
        rpBo.updateReport(reportID, Constants.REPORT_ACCEPTED);
        Long postID = Long.parseLong(request.getParameter("btnApproveReport"));
        postBo.updateVisibilityStatusPost(postID, Constants.POST_LOCKED);
    }

    private void handleRejectReport(Long reportID) throws Exception {
        ReportBo rpBo = new ReportBo();
        rpBo.updateReport(reportID, Constants.REPORT_REJECTED);
    }

    private void handleDeleteReport(Long reportID, HttpServletResponse response, ReportBo rpBo) throws Exception {
        rpBo.deleteReport(reportID);
        response.sendRedirect("reports"); 
    }

    private void showReportDetails(Long reportID, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DetailsReportBo detailsReportBo = new DetailsReportBo();
        request.setAttribute("report", detailsReportBo.getReportByID(reportID));
        RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-report.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

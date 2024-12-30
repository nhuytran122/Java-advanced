package ControllerAdmin;

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
import V_DetailsReportModal.DetailsReport;
import V_DetailsReportModal.DetailsReportBo;

@WebServlet("/admin/reports")
public class ShowReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowReportController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
//TODO
//            if (MethodCommon.getAdminFromSession(session, response) == null) {
//    	        response.sendRedirect("login");
//    	        return;
//    	    }
            
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
            Long statusID = 0L;
            int rowCount = 0;
            

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }
            
            if (request.getParameter("statusID") != null) {
            	statusID = Long.parseLong(request.getParameter("statusID"));
            }
            
            DetailsReportBo dtReportBo = new DetailsReportBo();
            
            ArrayList<DetailsReport> dsReports = null;
            dsReports = dtReportBo.getReportsByConditions(page, pageSize, searchValue, statusID);
            rowCount = dtReportBo.getCountReportsByConditions(searchValue, statusID);
            int pageCount = MethodCommon.calculatePageCount(rowCount, pageSize);
            
            request.setAttribute("dsReports", dsReports);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("/Admin/show-reports.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
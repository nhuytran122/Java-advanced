package ControllerAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
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
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
            
            int page = ControllerUtils.getPage(request);
            String searchValue = ControllerUtils.getSearchValue(request);
            Long statusID = 0L;
            int rowCount = 0;
            
            if (request.getParameter("statusID") != null) {
            	statusID = Long.parseLong(request.getParameter("statusID"));
            }
            
            DetailsReportBo dtReportBo = new DetailsReportBo();
            
            ArrayList<DetailsReport> dsReports = null;
            dsReports = dtReportBo.getReportsByConditions(page, Constants.PAGE_SIZE, searchValue, statusID);
            rowCount = dtReportBo.getCountReportsByConditions(searchValue, statusID);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("dsReports", dsReports);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "/Admin/show-reports.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
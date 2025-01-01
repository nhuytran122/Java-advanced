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
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/admin/docs")
public class ShowDocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ShowDocsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            } 
			DetailsDocBo dtdocBo = new DetailsDocBo();
			int page = ControllerUtils.getPage(request);
            String searchValue = ControllerUtils.getSearchValue(request);
            
            Long cateID = 0L; 
            Long mateID = 0L; 

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            
            if (request.getParameter("cateID") != null) {
                cateID = Long.parseLong(request.getParameter("cateID"));
            }

            if (request.getParameter("mateID") != null) {
                mateID = Long.parseLong(request.getParameter("mateID"));
            }
            
            if(request.getParameter("btn-search") != null) {

	            if (request.getParameter("txtSearch") != null) {
	                searchValue = request.getParameter("txtSearch");
	            }
            }
            ArrayList<DetailsDoc> ds = dtdocBo.getDocsByConditions(page, Constants.PAGE_SIZE, searchValue, cateID, mateID);
            int rowCount = dtdocBo.getCountDocsByConditions(searchValue, cateID, mateID);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("ds", ds);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "/Admin/show-docs.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

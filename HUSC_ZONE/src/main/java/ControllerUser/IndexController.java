package ControllerUser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CommonModal.MethodCommon;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/home")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DetailsDocBo dtdocBo = new DetailsDocBo();
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
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
            ArrayList<DetailsDoc> ds = dtdocBo.getDocsByConditions(page, pageSize, searchValue, cateID, mateID);

            int rowCount = dtdocBo.getCountDocsByConditions(searchValue, cateID, mateID);
            
            int pageCount = MethodCommon.calculatePageCount(rowCount, pageSize);
            
            request.setAttribute("ds", ds);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("User/index.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

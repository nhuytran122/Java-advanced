package ControllerUser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
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
            int page = ControllerUtils.getPage(request);
            String searchValue = ControllerUtils.getSearchValue(request);
            Long cateID = (request.getParameter("cateID") != null) 
                    ? Long.parseLong(request.getParameter("cateID")) : 0L;

            Long mateID = (request.getParameter("mateID") != null) 
                    ? Long.parseLong(request.getParameter("mateID")) : 0L;
            
            ArrayList<DetailsDoc> ds = dtdocBo.getDocsByConditions(page, Constants.PAGE_SIZE, searchValue, cateID, mateID);

            int rowCount = dtdocBo.getCountDocsByConditions(searchValue, cateID, mateID);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("ds", ds);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "User/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

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
import DocumentModal.Document;
import DocumentModal.DocumentBo;

@WebServlet("/home")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IndexController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            request.setAttribute("listCates", MethodCommon.getListCates());
            request.setAttribute("listMates", MethodCommon.getListMates());

            DocumentBo docBo = new DocumentBo();
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
            Long cateID = 0L; 
            Long mateID = 0L; 

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }

            if (request.getParameter("cateID") != null) {
                cateID = Long.parseLong(request.getParameter("cateID"));
            }

            if (request.getParameter("mateID") != null) {
                mateID = Long.parseLong(request.getParameter("mateID"));
            }

            ArrayList<Document> ds = docBo.getDocsByConditions(page, pageSize, searchValue, cateID, mateID);

            int rowCount = docBo.getCountDocsByConditions(searchValue, cateID, mateID);
            
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }

            request.setAttribute("ds", ds);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("searchKeyword", searchValue);
            request.setAttribute("cateID", cateID);
            request.setAttribute("mateID", mateID);

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

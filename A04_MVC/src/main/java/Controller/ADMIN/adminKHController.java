package Controller.ADMIN;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khachhangmodal.khachhang;
import khachhangmodal.khachhangbo;

@WebServlet("/adminKHController")
public class adminKHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminKHController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            HttpSession session = request.getSession();
           
            if(session.getAttribute("ad") == null) {
            	response.sendRedirect("adminloginController");
            }

            khachhangbo khBo = new khachhangbo();
            int page = 1;
            int pageSize = 9;
            String searchValue = "";

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }

            ArrayList<khachhang> ds;
            ds = khBo.getKHByCondition(page, pageSize, searchValue);

            int rowCount = khBo.countKHWithSearch(searchValue);
            
            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }

            request.setAttribute("ds", ds);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("searchKeyword", searchValue); 

            RequestDispatcher rd = request.getRequestDispatcher("ADMIN/show_customers.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

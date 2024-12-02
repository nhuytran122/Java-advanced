package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hoadonmodal.hoadonbo;
import khachhangmodal.khachhangbo;
import sachmodal.sachbo;

@WebServlet("/adminController")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminController() {
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
            request.setAttribute("cntKH", (new khachhangbo().countAllKH()));
            request.setAttribute("cntSach", (new sachbo().getRowCount("")));
            request.setAttribute("cntHD", (new hoadonbo().countAllHD()));
            request.setAttribute("cntHDPaid", (new hoadonbo().countHDPaid()));

            RequestDispatcher rd = request.getRequestDispatcher("ADMIN/index.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

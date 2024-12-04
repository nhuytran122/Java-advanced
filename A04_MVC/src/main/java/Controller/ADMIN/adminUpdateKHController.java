package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khachhangmodal.khachhangbo;


@WebServlet("/adminUpdateKHController")
public class adminUpdateKHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminUpdateKHController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            HttpSession session = request.getSession();

            if (session.getAttribute("ad") == null) {
                response.sendRedirect("adminloginController");
                return;
            }

            khachhangbo khbo = new khachhangbo();
            Long maKH = null;
            if(request.getParameter("idKH") != null) {
            	maKH = Long.parseLong(request.getParameter("idKH"));
            }

            if (request.getParameter("btnDeleteKH") != null) {
                khbo.deleteKH(maKH);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("adminKHController");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

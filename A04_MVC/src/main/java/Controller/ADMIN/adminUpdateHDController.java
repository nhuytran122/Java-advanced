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
import lichsumodal.lichsubo;

@WebServlet("/adminUpdateHDController")
public class adminUpdateHDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminUpdateHDController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            HttpSession session = request.getSession();
           
            if(session.getAttribute("ad") == null) {
            	response.sendRedirect("adminloginController");
            	return;
            }

            hoadonbo hdbo = new hoadonbo();
            lichsubo lsbo = new lichsubo();
            
            Long maHD = null;
            if(request.getParameter("idHD") != null) {
            	maHD = Long.parseLong(request.getParameter("idHD"));
            }
            
            if (request.getParameter("btnDeleteHD") != null) {
                    hdbo.deleteHD(maHD);
                    response.sendRedirect("adminOrderController");
                    return;
            }
            
            
            if (request.getParameter("btnDetailHD") != null) {
            	request.setAttribute("lstCTHD", lsbo.getCTHDByOrderID(maHD));
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/detail_order.jsp");
                rd.forward(request, response);
                return;
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

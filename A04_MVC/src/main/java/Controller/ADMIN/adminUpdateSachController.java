package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Chung;
import sachmodal.sachbo;

@WebServlet("/adminUpdateSachController")
public class adminUpdateSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminUpdateSachController() {
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

            sachbo sbo = new sachbo();
            String ms = request.getParameter("idSach");
            if (request.getParameter("btnAddSach") != null) {
            	request.setAttribute("dsLoai", Chung.getDsLoai());
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_book.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (request.getParameter("btnUpdateSach") != null) {
            	request.setAttribute("dsLoai", Chung.getDsLoai());
            	request.setAttribute("book", sbo.getSach(ms));
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/update_book.jsp");
                rd.forward(request, response);
                return;
            }
            
            
            if (request.getParameter("btnDeleteSach") != null) {
                boolean inUsed = sbo.inUsedSach(ms);
                request.setAttribute("inUsed", inUsed);
                if (!inUsed) {
                    sbo.deleteSach(ms); 
                    response.sendRedirect("adminSachController");
                    return;
                }
                RequestDispatcher rd = request.getRequestDispatcher("adminSachController");
                rd.forward(request, response);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
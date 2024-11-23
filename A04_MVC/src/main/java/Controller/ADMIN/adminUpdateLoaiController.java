package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loaimodal.loaibo;

@WebServlet("/adminUpdateLoaiController")
public class adminUpdateLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminUpdateLoaiController() {
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

            loaibo lbo = new loaibo();
            String ms = request.getParameter("idLoai");
            if (request.getParameter("btnAddLoai") != null) {
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_loai.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (request.getParameter("btnUpdateLoai") != null) {
            	request.setAttribute("loai", lbo.getLoai(ms));
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/update_loai.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (request.getParameter("btnDeleteLoai") != null) {
                boolean inUsed = lbo.inUsedLoai(ms);
                request.setAttribute("inUsed", inUsed);
                if (!inUsed) {
                    lbo.deleteLoai(ms); 
                    response.sendRedirect("adminLoaiController");
                    return;
                }
                RequestDispatcher rd = request.getRequestDispatcher("adminLoaiController");
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

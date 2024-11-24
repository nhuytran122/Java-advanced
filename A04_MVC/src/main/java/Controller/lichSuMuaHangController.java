package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khachhangmodal.khachhang;
import lichsumodal.lichsubo;

@WebServlet("/lichSuMuaHangController")
public class lichSuMuaHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public lichSuMuaHangController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            request.setAttribute("dsLoai", Chung.getDsLoai());
           
            if(session.getAttribute("kh") == null) {
            	response.sendRedirect("loginController");
            }
            else {
                 khachhang kh = (khachhang) session.getAttribute("kh");
                 lichsubo lsbo = new lichsubo();
                 request.setAttribute("listLS", lsbo.getLichsu(kh.getMakh(), true));
                 RequestDispatcher rd = request.getRequestDispatcher("purchase-history.jsp");
                 rd.forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

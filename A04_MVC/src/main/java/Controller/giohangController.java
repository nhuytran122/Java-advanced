package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cartmodal.GioHangBo;
import loaimodal.loaibo;

/**
 * Servlet implementation class giohangController
 */
@WebServlet("/giohangController")
public class giohangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public giohangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lay loai ve
		loaibo lbo = new loaibo();
		//chuyen dsLoai sang cart.jsp de hien thi
		request.setAttribute("dsLoai", lbo.getLoai());
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null)
            response.sendRedirect("loginController");
		
        GioHangBo g = (GioHangBo) session.getAttribute("gh");

        String masach = request.getParameter("bookId");

        // Nếu mua hàng lần đầu
        if (g == null) {
            g = new GioHangBo();
            session.setAttribute("gh", g);
        }

        // Thêm hàng vào biến g
        // Nếu có mã sách, thêm sách vào giỏ hàng 
        if (masach != null) {
            g.Them(masach, 1);
        }

        // Lưu giỏ hàng vào session
        session.setAttribute("gh", g);
        
        RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

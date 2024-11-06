package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khachhangmodal.khachhangbo;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();  
			request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            request.setAttribute("dsLoai", Chung.getDsLoai());
			String txtLoginId = request.getParameter("txtLoginId");
			String txtPassword = request.getParameter("txtPassword");
			String btnLogin = request.getParameter("btn-login");
			   
			khachhangbo khbo = new khachhangbo();
			boolean isInvalid = false; 
			  
			if (btnLogin != null) {
				if (khbo.checkLogin(txtLoginId, txtPassword) != null) {
			    session.setAttribute("kh", khbo.checkLogin(txtLoginId, txtPassword));
			    response.sendRedirect("sachController");
			    return;
				} else
					isInvalid = true;
			}
			request.setAttribute("loginId", txtLoginId);
			request.setAttribute("password", txtPassword);
			request.setAttribute("isInvalid", isInvalid);
			  
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

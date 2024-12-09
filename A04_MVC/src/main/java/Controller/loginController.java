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

@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginController() {
        super();
    }
    
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
			boolean isWrong = false;
			  
			if (btnLogin != null) {
				if (txtLoginId.trim().isEmpty() || txtPassword.trim().isEmpty()) {
        		    isInvalid = true;
	        	}
				else {
					if (khbo.checkLogin(txtLoginId, txtPassword) != null) {
					    session.setAttribute("kh", khbo.checkLogin(txtLoginId, txtPassword));
					    response.sendRedirect("sachController");
					    return;
					} else
						isWrong = true;
				}
			}
			request.setAttribute("isInvalid", isInvalid);
			request.setAttribute("isWrong", isWrong);
			  
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

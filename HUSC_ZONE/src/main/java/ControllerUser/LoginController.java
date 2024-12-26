package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import UserModal.UserBo;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();  
            
			String txtLoginId = request.getParameter("txtLoginId");
			String txtPassword = request.getParameter("txtPassword");
			String btnLogin = request.getParameter("btn-login");
			   
			UserBo userBo = new UserBo();
			boolean isInvalid = false; 
			boolean isWrong = false;
			  
			if (btnLogin != null) {
				if (txtLoginId.trim().isEmpty() || txtPassword.trim().isEmpty()) {
        		    isInvalid = true;
	        	}
	        	else {
					if (userBo.checkLogin(txtLoginId, txtPassword) != null) {
					    session.setAttribute("user", userBo.checkLogin(txtLoginId, txtPassword));
					    response.sendRedirect("home");
					    return;
					} else
						isWrong = true;
	        	}
			}
			request.setAttribute("loginId", txtLoginId);
			request.setAttribute("isInvalid", isInvalid);
			request.setAttribute("isWrong", isWrong);
			  
			RequestDispatcher rd = request.getRequestDispatcher("User/login.jsp");
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

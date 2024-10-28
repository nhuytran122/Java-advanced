package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		  HttpSession session = request.getSession();  
		  String txtLoginId = request.getParameter("txtLoginId");
		  String txtPassword = request.getParameter("txtPassword");
		  String btnLogin = request.getParameter("btn-login");
		    
		  boolean isInvalid = false; 

		  if (btnLogin != null) {
		      if (txtLoginId != null && txtPassword != null) {
		          if (txtLoginId.equals("abc") && txtPassword.equals("123")) {
		        	  session.setAttribute("userId", txtLoginId);
		        	  session.setAttribute("userPass", txtPassword);
		        	  
		        	  response.sendRedirect("sachController");
		        	  return;
		          } else {
		              isInvalid = true;
		          }
		      }
		  }
		  request.setAttribute("loginId", txtLoginId);
		  request.setAttribute("password", txtPassword);
		  request.setAttribute("isInvalid", isInvalid);
		  
		  RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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

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
import khachhangmodal.khachhangbo;

/**
 * Servlet implementation class signupController
 */
@WebServlet("/signupController")
public class signupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
			String txtHoten = request.getParameter("txtHoten");
			String txtLoginId = request.getParameter("txtLoginId");
			String txtSdt = request.getParameter("txtSdt");
			String txtEmail = request.getParameter("txtEmail");
			String txtDiachi = request.getParameter("txtDiachi");
			String txtPassword = request.getParameter("txtPassword");
			String btnSignup = request.getParameter("btn-signup");
			   
			khachhangbo khbo = new khachhangbo();
			boolean isInvalid = false; 
			  
			if (btnSignup != null) {
				if (txtHoten != null && !txtHoten.trim().isEmpty() &&
						txtLoginId != null && !txtLoginId.trim().isEmpty() &&
						txtPassword != null && !txtPassword.trim().isEmpty()) {
                	int kqkh = khbo.themKH(txtHoten, txtDiachi, txtSdt, txtEmail, txtLoginId, txtPassword);
                	if(kqkh > 0) {
        			    response.sendRedirect("loginController");
        			    return;
                	}
				} else
					isInvalid = true;
			}
			request.setAttribute("loginId", txtLoginId);
			request.setAttribute("hoten", txtHoten);
			request.setAttribute("sdt", txtSdt);
			request.setAttribute("email", txtEmail);
			request.setAttribute("diachi", txtDiachi);
			request.setAttribute("password", txtPassword);
			request.setAttribute("isInvalid", isInvalid);
			  
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
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

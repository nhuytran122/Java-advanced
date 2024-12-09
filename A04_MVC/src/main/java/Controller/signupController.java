package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khachhangmodal.khachhangbo;

@WebServlet("/signupController")
public class signupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public signupController() {
        super();
    }

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
			boolean isDuplicate = false;
			  
			if (btnSignup != null) {
				if (txtHoten.trim().isEmpty() ||
					txtLoginId.trim().isEmpty() ||
					txtPassword.trim().isEmpty()) {
					
					isInvalid = true;
				}
				else
				{
                	int kqkh = khbo.themKH(txtHoten, txtDiachi, txtSdt, txtEmail, txtLoginId, txtPassword);
                	if(kqkh <= 0) {
		            	isDuplicate = true;
		            }
                	else {
        			    response.sendRedirect("loginController");
        			    return;
                	}	
				} 
			}
			request.setAttribute("isInvalid", isInvalid);
			request.setAttribute("isDuplicate", isDuplicate);
			  
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		    rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

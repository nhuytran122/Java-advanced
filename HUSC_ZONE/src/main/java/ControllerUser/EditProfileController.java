package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/edit-profile")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
	        
	        if (session.getAttribute("user") == null) {
	            response.sendRedirect("login");
	            return;
	        }

            if (request.getParameter("btnUpdateProfile") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("User/update-profile.jsp");
                rd.forward(request, response);
                return;
            }
            if (request.getParameter("btnChangePW") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("User/change-password.jsp");
                rd.forward(request, response);
                return;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

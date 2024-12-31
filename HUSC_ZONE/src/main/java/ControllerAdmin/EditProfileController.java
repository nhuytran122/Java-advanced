package ControllerAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import UserModal.User;

@WebServlet("/admin/edit-profile")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditProfileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			if (!MethodCommon.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }

            if (request.getParameter("btnUpdateProfile") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-profile.jsp");
                rd.forward(request, response);
                return;
            }
            if (request.getParameter("btnChangePW") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/change-password.jsp");
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

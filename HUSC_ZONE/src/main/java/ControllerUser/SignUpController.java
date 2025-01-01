package ControllerUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import UserModal.UserBo;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {           
            String txtHoten = request.getParameter("txtHoten");
            String txtLoginId = request.getParameter("txtLoginId");
            String txtSdt = request.getParameter("txtSdt");
            String txtGioiTinh = request.getParameter("txtGioiTinh");
            String txtPassword = request.getParameter("txtPassword");
            String btnSignup = request.getParameter("btn-signup");
            
            UserBo userBo = new UserBo();
            
            boolean isInvalid = false;
            boolean isDuplicate = false;
            
            if (btnSignup != null) {
            	if (txtHoten.trim().isEmpty() || txtGioiTinh.trim().isEmpty() || txtPassword.trim().isEmpty()) {
            		    isInvalid = true;
            	}
            	else {
	            	int add = userBo.addUser(txtHoten, txtPassword, txtGioiTinh, txtLoginId, 
	        			txtSdt, Constants.ROLE_USER);
		            if(add <= 0) {
		            	isDuplicate = true;
		            }
		            else {
		            	response.sendRedirect("login");
        			    return;
		            }
	            }
            }
            request.setAttribute("isInvalid", isInvalid);
            request.setAttribute("isDuplicate", isDuplicate);
            ControllerUtils.forwardRequest(request, response, "User/sign-up.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

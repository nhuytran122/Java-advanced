package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import UserModal.User;
import UserModal.UserBo;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			if (!ControllerUtils.ensureUserLogin(session, response, request)) {
    	        return;
    	    }
			User currentUser = (User) session.getAttribute("user");
	        String currentPassword = request.getParameter("txtCurrPw");
	        String newPassword = request.getParameter("txtNewPw");
	        String confirmNewPassword = request.getParameter("txtConfNewPw");

	        request.setAttribute("txtCurrPw", currentPassword);
	        request.setAttribute("txtNewPw", newPassword);
	        request.setAttribute("txtConfNewPw", confirmNewPassword);
	        
	        String message = null;
	        String messageType = null;
	        String redirectPage = "User/change-password.jsp";
	        if(currentUser.getRoleID() == Constants.ROLE_ADMIN) {
	        	if(request.getParameter("changePwWithAd")!= null)
	        		redirectPage= "/Admin/change-password.jsp";
	        }
	       
	        if (!newPassword.equals(confirmNewPassword)) {
                message = "Mật khẩu xác nhận không khớp!";
                request.setAttribute("message", message);
                messageType = "warning";
                request.setAttribute("messageType", messageType);
                RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
                rd.forward(request, response);
                return;
            }

            UserBo userBo = new UserBo();
            Long result = userBo.changePassword(currentUser.getEmail(), currentPassword, newPassword);

            if (result == Constants.WRONG_PASSWORD) {
                message = "Mật khẩu hiện tại không đúng!";
                messageType = "danger";
            } else if (result == Constants.DUPLICATE_PASSWORD) {
                message = "Mật khẩu mới không được trùng với mật khẩu hiện tại!";
                messageType = "warning";
            } else if (result == Constants.CHANGE_PASSWORD_SUCCESS) {
                message = "Đổi mật khẩu thành công!";
                messageType = "success";
                request.setAttribute("isPasswordChanged", true);
            } else {
                message = "Có lỗi xảy ra, vui lòng thử lại sau!";
                messageType = "danger";
            }
            request.setAttribute("messageType", messageType);
            request.setAttribute("message", message);
            ControllerUtils.forwardRequest(request, response, redirectPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

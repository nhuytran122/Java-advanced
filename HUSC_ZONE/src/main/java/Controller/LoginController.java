package Controller;

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
import nl.captcha.Captcha;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			if(ControllerUtils.getUserFromSession(session, response) != null) {
            	User user = ControllerUtils.getUserFromSession(session, response);
            	handleUserRedirection(user, response);
            	return;
            }
			
			Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
			String answerCapcha = request.getParameter("answerCapcha");

			String txtLoginId = request.getParameter("txtLoginId");
			String txtPassword = request.getParameter("txtPassword");
			String btnLogin = request.getParameter("btn-login");

			UserBo userBo = new UserBo();
			boolean isInvalid = false;
			boolean isWrong = false;
			boolean isInvalidCapcha = false;
			boolean isLocked = false;

			if (btnLogin != null) {
				if (txtLoginId.trim().isEmpty() || txtPassword.trim().isEmpty()) {
        		    isInvalid = true;
				}

                if (session.getAttribute("dem") != null) {
                    int d = (int) session.getAttribute("dem");
                    if (d >= 3) {
                        if (answerCapcha == null || !captcha.isCorrect(answerCapcha)) {
                            isInvalidCapcha = true;
                        }
                    }
                }

                if (!isInvalidCapcha) {
                    User user = userBo.checkLogin(txtLoginId, txtPassword);
                    if (user != null) {
                    	if (!user.isIsUsing()) { 
                            isLocked = true;
                            request.setAttribute("isLocked", isLocked);
                            RequestDispatcher rd = request.getRequestDispatcher("User/login.jsp");
                			rd.forward(request, response);
                        }
                    	else {
	                        session.removeAttribute("dem");
	                        session.setAttribute("user", user);
	                        if (user.getRoleID() == Constants.ROLE_ADMIN) {
	                            response.sendRedirect("admin");
	                            return;
	                        }
	                        response.sendRedirect("home");
	                        return;
	                    }
                    } else {
                        if (session.getAttribute("dem") == null) {
                            session.setAttribute("dem", 0);
                        }
                        int d = (int) session.getAttribute("dem");
                        d++;
                        session.setAttribute("dem", d);
                        isWrong = true;
                    }
                }
            }

			request.setAttribute("loginId", txtLoginId);
			request.setAttribute("isInvalid", isInvalid);
			request.setAttribute("isInvalidCapcha", isInvalidCapcha);
			request.setAttribute("isWrong", isWrong);

            ControllerUtils.forwardRequest(request, response, "User/login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean handleUserRedirection(User user, HttpServletResponse response) throws IOException {
        if (user != null) {
            if (user.getRoleID() == Constants.ROLE_ADMIN) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("home");
            }
            return true;
        }
        return false;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Chung;
import adminloginmodal.loginadminbo;
import nl.captcha.Captcha;

@WebServlet("/adminloginController")
public class adminloginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminloginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();  
            Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
            String answerCapcha = request.getParameter("answerCapcha");
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            request.setAttribute("dsLoai", Chung.getDsLoai());
            
            String txtLoginId = request.getParameter("txtLoginId");
            String txtPassword = request.getParameter("txtPassword");
            String btnLogin = request.getParameter("btn-login");
            
            loginadminbo adbo = new loginadminbo();
            boolean isInvalid = false; 
            boolean isInvalidCapcha = false; 
            
            if (btnLogin != null) {
                if (session.getAttribute("dem") != null) {
                    int d = (int) session.getAttribute("dem");
                    if (d >= 3) {
                        if (answerCapcha == null || !captcha.isCorrect(answerCapcha)) {
                        	isInvalidCapcha = true; 
                        }
                    }
                }
                String ad = adbo.checkLogin(txtLoginId, txtPassword);
                if (ad != null && !isInvalidCapcha) {
                    session.removeAttribute("dem"); 
                    session.setAttribute("ad", ad);
                    response.sendRedirect("adminController");
                    return;
                } else {
                    if (session.getAttribute("dem") == null) {
                        session.setAttribute("dem", 0);
                    }
                    int d = (int) session.getAttribute("dem");
                    d++;
                    session.setAttribute("dem", d);
                    isInvalid = true;
                }
            }

            request.setAttribute("loginId", txtLoginId);
            request.setAttribute("isInvalid", isInvalid);

            RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

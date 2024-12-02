package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BookmarkModal.BookmarkBo;
import UserModal.User;

@WebServlet("/interact")
public class InteractionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InteractionController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            User user = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            } else {
                user = (User) session.getAttribute("user");
            }

            BookmarkBo bmkBo = new BookmarkBo();
            Long docsID = 0L;

            if (request.getParameter("docsID") != null) {
                docsID = Long.parseLong(request.getParameter("docsID"));
            } else {
                response.sendRedirect("home");
                return;
            }

            if (request.getParameter("btn-mark") != null) {
                String bmark = request.getParameter("btn-mark");
                if (bmark.equals("mark")) {
                    bmkBo.addBookmark(docsID, user.getUserID());  
                } else {
                    bmkBo.deleteBookmark(docsID, user.getUserID());  
                }
                request.setAttribute("docsID", docsID);
                
                //C1: response.sendRedirect("details?docsID=" + docsID);
                //return;
                
                RequestDispatcher rd = request.getRequestDispatcher("details");
                rd.forward(request, response); 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

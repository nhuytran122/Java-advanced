package ControllerUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ActionsCommonUtils;
import CommonModal.ControllerUtils;
import DocumentModal.DocumentBo;
import UserModal.User;

@WebServlet("/edit-docs")
public class EditDocsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditDocsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (!ControllerUtils.ensureUserLogin(session, response, request)) {
    	        return;
    	    }
            User user = ControllerUtils.getUserFromSession(session, response);

            DocumentBo docBo = new DocumentBo();
            Long docID = 0L;
            if (request.getParameter("docID") != null)
                docID = Long.parseLong(request.getParameter("docID"));
            
	        if (request.getParameter("btnDeleteDoc") != null) {
	        	ActionsCommonUtils.handleDeleteDocument(request, response, docBo, docID, "docs-of-user");
	            return;
	        }
            response.setContentType("text/html; charset=utf-8");
            int uploaded = ActionsCommonUtils.addOrUpdateDocument(request, docBo, user.getUserID());
            if (uploaded == 1) {
                response.sendRedirect("docs-of-user");
                return;
            } 
            ControllerUtils.forwardRequest(request, response, "User/add-docs.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

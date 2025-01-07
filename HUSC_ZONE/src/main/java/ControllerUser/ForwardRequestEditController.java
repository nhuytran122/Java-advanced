package ControllerUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ControllerUtils;
import DocumentModal.DocumentBo;
import StatusPostModal.StatusPostBo;

@WebServlet("/edit")
public class ForwardRequestEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForwardRequestEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			if (!ControllerUtils.ensureUserLogin(session, response, request)) {
    	        return;
    	    }

            if (request.getParameter("btnUpdateProfile") != null) {
            	ControllerUtils.forwardRequest(request, response, "User/update-profile.jsp");
                return;
            }
            if (request.getParameter("btnChangePW") != null) {
            	ControllerUtils.forwardRequest(request, response, "User/change-password.jsp");
                return;
            }

            if (request.getParameter("btnAddDoc") != null) {
            	ControllerUtils.forwardRequest(request, response, "User/add-docs.jsp");
                return;
            }

            if (request.getParameter("btnUpdateDoc") != null) {
            	if (request.getParameter("docID") != null) {
            		DocumentBo docBo = new DocumentBo();
            		Long docID = Long.parseLong(request.getParameter("docID"));
            		request.setAttribute("doc", docBo.getDocumentByID(docID));
                    ControllerUtils.forwardRequest(request, response, "User/update-docs.jsp");
                    return;
            	}
            	response.sendRedirect("home");  
            }
            
            if (request.getParameter("editPost") != null ) {
            	if (request.getParameter("postID") != null) {
            		StatusPostBo sttBo = new StatusPostBo();
            		Long postID = Long.parseLong(request.getParameter("postID"));
                	request.setAttribute("stt", sttBo.getStatusPostByID(postID));
                	ControllerUtils.forwardRequest(request, response, "User/update-status.jsp");
                    return;
            	}
            	response.sendRedirect("home");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

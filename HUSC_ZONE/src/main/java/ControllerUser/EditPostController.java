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
import CommonModal.FileUtils;
import StatusPostModal.StatusPostBo;
import UserModal.User;

@WebServlet("/edit-post")
public class EditPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            
            if (!ControllerUtils.ensureUserLogin(session, response, request)) {
            	return;
            }
            StatusPostBo sttBo = new StatusPostBo();
            Long postID = 0L;
            if (request.getParameter("postID") != null)
            	postID = Long.parseLong(request.getParameter("postID"));
            
            if (request.getParameter("btnDeleteStt") != null) {
            	String filePath = sttBo.getStatusPostByID(postID).getImagePath();
            	FileUtils.deleteFile(request, filePath);
            	sttBo.deleteStatusPost(postID);
            	response.sendRedirect("user-profile");
                return;
            }
            
            User user = ControllerUtils.getUserFromSession(session, response);
			response.setContentType("text/html; charset=utf-8");
			int uploaded = ActionsCommonUtils.addOrUpdatePost(request, sttBo, user.getUserID());
            if (uploaded == 1) {
            	response.sendRedirect("status-post");
                return;
            } 
            else if(uploaded == 2) {
            	response.sendRedirect("user-profile");
                return;
            }
            response.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

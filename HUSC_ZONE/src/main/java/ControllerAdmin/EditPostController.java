package ControllerAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ActionsCommonUtils;
import CommonModal.ControllerUtils;
import StatusPostModal.StatusPostBo;
import UserModal.User;

@WebServlet("/admin/edit-post")
public class EditPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		  HttpSession session = request.getSession();
          if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
              return; 
          }
          User user = ControllerUtils.getUserFromSession(session, response);

          StatusPostBo postBo = new StatusPostBo();
          Long postID = 0L;
          if (request.getParameter("postID") != null)
        	  postID = Long.parseLong(request.getParameter("postID"));
          
          if (request.getParameter("btnDeletePost") != null) {
        	  ActionsCommonUtils.handleDeletePost(request, response, postBo, postID, "posts");
              return;
          }
          
          response.setContentType("text/html; charset=utf-8");
		  int uploaded = ActionsCommonUtils.addOrUpdatePost(request, postBo, user.getUserID());
		  if(uploaded != 0) {
			  response.sendRedirect("posts");
			  return;
		  }
          response.sendRedirect("../admin");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

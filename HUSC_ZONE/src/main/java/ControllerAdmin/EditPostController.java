package ControllerAdmin;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import StatusPostModal.StatusPostBo;

@WebServlet("/admin/edit-post")
public class EditPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditPostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
            if (!MethodCommon.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }

          StatusPostBo postBo = new StatusPostBo();
          Long postID = 0L;
          if (request.getParameter("postID") != null)
        	  postID = Long.parseLong(request.getParameter("postID"));
          
          if (request.getParameter("btnDeletePost") != null) {
        	  handleDeletePost(request, response, postBo, postID);
              return;
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  private void handleDeletePost(HttpServletRequest request, HttpServletResponse response, StatusPostBo postBo, Long postID)
          throws IOException {
      try {
      	String filePath = postBo.getStatusPostByID(postID).getImagePath();
      	postBo.deleteStatusPost(postID);
          
          String appPath = request.getServletContext().getRealPath("") + filePath;
          File fileDocs = new File(appPath);
          System.out.println("Path of image: " + fileDocs.getAbsolutePath());
          if (fileDocs.exists()) {
              boolean isImageDeleted = fileDocs.delete(); // Xóa file
              if (!isImageDeleted) {
                  System.out.println("Không thể xóa file: " + filePath);
              }
          }
          response.sendRedirect("posts");
      } catch (Exception e) {
          e.printStackTrace();
      }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

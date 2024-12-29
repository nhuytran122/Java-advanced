package ControllerAdmin;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DocumentModal.DocumentBo;
import UserModal.UserBo;

@WebServlet("/admin/edit-user")
public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//          HttpSession session = request.getSession();
//          MethodCommon.ensureUserIsLoggedIn(session, response);

          UserBo userBo = new UserBo();
          Long userID = 0L;
          if (request.getParameter("userID") != null)
        	  userID = Long.parseLong(request.getParameter("userID"));
          
          if (request.getParameter("btnDetail") != null) {
              request.setAttribute("user", userBo.getUserByID(userID));
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/detail-user.jsp");
              rd.forward(request, response);
              return;
          }
          if (request.getParameter("btnAddUser") != null) {
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-user.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnUpdateUser") != null) {
              request.setAttribute("user", userBo.getUserByID(userID));
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-user.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnDeleteUser") != null) {
        	  handleDeleteUser(request, response, userBo, userID);
              return;
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
  private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response, UserBo userBo, Long userID)
          throws IOException {
      try {
      	String filePath = userBo.getUserByID(userID).getAvatar();
      	userBo.deleteUser(userID);
          
          String appPath = request.getServletContext().getRealPath("") + filePath;
          File fileDocs = new File(appPath);
          System.out.println("Path of image: " + fileDocs.getAbsolutePath());
          if (fileDocs.exists()) {
              boolean isImageDeleted = fileDocs.delete(); // Xóa file
              if (!isImageDeleted) {
                  System.out.println("Không thể xóa file: " + filePath);
              }
          }
          response.sendRedirect("users");
          return;
      } catch (Exception e) {
          e.printStackTrace();
      }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

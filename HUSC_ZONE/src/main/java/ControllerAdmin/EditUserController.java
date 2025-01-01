package ControllerAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ControllerUtils;
import CommonModal.FileUtils;
import UserModal.UserBo;

@WebServlet("/admin/edit-user")
public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }

          UserBo userBo = new UserBo();
          Long userID = 0L;
          if (request.getParameter("userID") != null)
        	  userID = Long.parseLong(request.getParameter("userID"));
          
          if (request.getParameter("btnDeleteUser") != null) {
        	  handleDeleteUser(request, response, userBo, userID);
              return;
          }
          
          String txtRoleID = request.getParameter("txtVaiTro");
          if (request.getParameter("btnAdd") != null) {
              handleAddUser(request, response, userBo, txtRoleID);
              return;
          } else if (request.getParameter("btnUpdate") != null) {
              handleUpdateUser(request, response, userBo, txtRoleID);
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
	        FileUtils.deleteFile(request, filePath);
	        userBo.deleteUser(userID);
	        response.sendRedirect("users");
      } catch (Exception e) {
          e.printStackTrace();
      }
	}
  
  private void handleAddUser(HttpServletRequest request, HttpServletResponse response, UserBo userBo, String txtRoleID)
          throws NumberFormatException, Exception {
      String txtHoten = request.getParameter("txtHoten");
      String txtLoginId = request.getParameter("txtLoginId");
      String txtSdt = request.getParameter("txtSdt");
      String txtGioiTinh = request.getParameter("txtGioiTinh");
      String txtPassword = request.getParameter("txtPassword");

      boolean isInvalid = false;
      boolean isDuplicate = false;

      if (txtHoten.trim().isEmpty() || txtGioiTinh.trim().isEmpty() || txtPassword.trim().isEmpty() || txtRoleID.isEmpty()) {
          isInvalid = true;
      } else {
          int add = userBo.addUser(txtHoten, txtPassword, txtGioiTinh, txtLoginId, txtSdt, Long.parseLong(txtRoleID));
          if (add <= 0) {
              isDuplicate = true;
          } else {
              response.sendRedirect("users");
              return;
          }
      }
      request.setAttribute("isInvalid", isInvalid);
      request.setAttribute("isDuplicate", isDuplicate);
      ControllerUtils.forwardRequest(request, response, "/Admin/add-user.jsp");
  }

  private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response, UserBo userBo, String txtRoleID)
          throws Exception {
      if (request.getParameter("userID") != null) {
          Long userID = Long.parseLong(request.getParameter("userID"));
          boolean isInvalid = false;

          if (txtRoleID.isEmpty()) {
              isInvalid = true;
              request.setAttribute("isInvalid", isInvalid);
              request.setAttribute("user", userBo.getUserByID(userID));
              ControllerUtils.forwardRequest(request, response, "/Admin/update-user.jsp");
              return;
          }

          String isLockedParam = request.getParameter("isUsing");
          boolean isUsing = "true".equals(isLockedParam);
          userBo.updateStatusAndRoleUser(userID, isUsing, Long.parseLong(txtRoleID));

          response.sendRedirect("users");
      }
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

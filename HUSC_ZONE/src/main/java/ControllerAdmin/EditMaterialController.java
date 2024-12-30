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
import MaterialModal.MaterialBo;
import UserModal.UserBo;

@WebServlet("/admin/edit-material")
public class EditMaterialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditMaterialController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//          HttpSession session = request.getSession();
//          MethodCommon.ensureUserIsLoggedIn(session, response);

          MaterialBo mateBo = new MaterialBo();
          Long mateID = 0L;
          if (request.getParameter("mateID") != null)
        	  mateID = Long.parseLong(request.getParameter("mateID"));
          
          if (request.getParameter("btnAddMate") != null) {
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-material.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnUpdateMate") != null) {
              request.setAttribute("material", mateBo.getMaterialByID(mateID));
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-material.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnDeleteMate") != null) {
        	  mateBo.deleteMaterial(mateID);
        	  response.sendRedirect("materials");
              return;
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

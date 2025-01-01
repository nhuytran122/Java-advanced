package ControllerAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.ControllerUtils;
import MaterialModal.MaterialBo;

@WebServlet("/admin/edit-material")
public class EditMaterialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditMaterialController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		  HttpSession session = request.getSession();
          if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
        	  return; 
          }

          MaterialBo mateBo = new MaterialBo();
          Long mateID = 0L;
          if (request.getParameter("mateID") != null) {
        	  mateID = Long.parseLong(request.getParameter("mateID"));
	          if (request.getParameter("btnDeleteMate") != null) {
	        	  mateBo.deleteMaterial(mateID);
	        	  response.sendRedirect("materials");
	              return;
	          }
          }
          
          String tenloai = request.getParameter("txtTenLoai");
          String mota = request.getParameter("txtMoTa");

          if(request.getParameter("btnAdd") != null) {
              mateBo.addMaterial(tenloai, mota);
          }
          
          if(request.getParameter("btnUpdate") != null) {
          	if(request.getParameter("mateID")  != null) {
	            mateID = Long.parseLong(request.getParameter("mateID"));
	            mateBo.updateMaterial(mateID, tenloai, mota);
            }
          }
          response.sendRedirect("materials");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

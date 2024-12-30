package ControllerAdmin;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CategoryModal.CategoryBo;
import UserModal.UserBo;

@WebServlet("/admin/edit-category")
public class EditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCategoryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//          HttpSession session = request.getSession();
//          MethodCommon.ensureUserIsLoggedIn(session, response);

          CategoryBo cateBo = new CategoryBo();
          Long cateID = 0L;
          if (request.getParameter("cateID") != null)
        	  cateID = Long.parseLong(request.getParameter("cateID"));
          
          if (request.getParameter("btnAddCate") != null) {
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-category.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnUpdateCate") != null) {
              request.setAttribute("category", cateBo.getCategoryByID(cateID));
              RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-category.jsp");
              rd.forward(request, response);
              return;
          }

          if (request.getParameter("btnDeleteCate") != null) {
        	  handleDeleteCategory(request, response, cateBo, cateID);
              return;
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
	
	private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response, CategoryBo cateBo, Long cateID)
	          throws IOException {
	      try {
	      	String filePath = cateBo.getCategoryByID(cateID).getImage();
	      	cateBo.deleteCategory(cateID);
	          
	          String appPath = request.getServletContext().getRealPath("") + filePath;
	          File fileDocs = new File(appPath);
	          System.out.println("Path of image: " + fileDocs.getAbsolutePath());
	          if (fileDocs.exists()) {
	              boolean isImageDeleted = fileDocs.delete(); // Xóa file
	              if (!isImageDeleted) {
	                  System.out.println("Không thể xóa file: " + filePath);
	              }
	          }
	          response.sendRedirect("categories");
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

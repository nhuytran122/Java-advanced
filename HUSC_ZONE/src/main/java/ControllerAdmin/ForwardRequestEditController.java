package ControllerAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CategoryModal.CategoryBo;
import CommonModal.ControllerUtils;
import DocumentModal.DocumentBo;
import MaterialModal.MaterialBo;
import UserModal.UserBo;

@WebServlet("/admin/edit")
public class ForwardRequestEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForwardRequestEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
	        if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
	              return; 
	        }
			
			if (request.getParameter("btnAddCate") != null) {
	              RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-category.jsp");
	              rd.forward(request, response);
	              return;
	        }
			if (request.getParameter("btnUpdateCate") != null) {
				if (request.getParameter("cateID") != null) {
	        	  Long cateID = Long.parseLong(request.getParameter("cateID"));
	        	  CategoryBo cateBo = new CategoryBo();
	          
	              request.setAttribute("category", cateBo.getCategoryByID(cateID));
	              RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-category.jsp");
	              rd.forward(request, response);
	              return;
	          }
			}
			
            if (request.getParameter("btnAddDoc") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-docs.jsp");
                rd.forward(request, response);
                return;
            }
            if (request.getParameter("btnUpdateDoc") != null) {
            	if (request.getParameter("docID") != null) {
	            	Long docID = Long.parseLong(request.getParameter("docID"));
	            	DocumentBo docBo = new DocumentBo();
	            
	                request.setAttribute("doc", docBo.getDocumentByID(docID));
	                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-docs.jsp");
	                rd.forward(request, response);
	                return;
	            }
            	response.sendRedirect("../admin");  
            }
            
            if (request.getParameter("btnAddMate") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-material.jsp");
                rd.forward(request, response);
                return;
            }
            if (request.getParameter("btnUpdateMate") != null) {
            	if (request.getParameter("mateID") != null) {
	            	Long mateID = Long.parseLong(request.getParameter("mateID"));
	                MaterialBo mateBo = new MaterialBo();
	            
	                request.setAttribute("material", mateBo.getMaterialByID(mateID));
	                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-material.jsp");
	                rd.forward(request, response);
	                return;
	            }
            	response.sendRedirect("../admin");  
            }
            
            if (request.getParameter("btnUpdateProfile") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-profile.jsp");
                rd.forward(request, response);
                return;
            }
            if (request.getParameter("btnChangePW") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/change-password.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (request.getParameter("btnAddUser") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-user.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnUpdateUser") != null) {
            	if (request.getParameter("userID") != null) {
            		Long userID = Long.parseLong(request.getParameter("userID"));
	            	UserBo userBo = new UserBo();
	                request.setAttribute("user", userBo.getUserByID(userID));
	                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-user.jsp");
	                rd.forward(request, response);
	                return;
            	}
            	response.sendRedirect("../admin");  
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

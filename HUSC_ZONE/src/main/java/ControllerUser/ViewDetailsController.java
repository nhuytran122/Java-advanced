package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import CommonModal.MethodCommon;
import DocumentModal.Document;
import DocumentModal.DocumentBo;
import MaterialModal.Material;
import MaterialModal.MaterialBo;
import UserModal.User;
import UserModal.UserBo;

@WebServlet("/details")
public class ViewDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
	        
	        request.setAttribute("listCates", MethodCommon.getListCates());
	        request.setAttribute("listMates", MethodCommon.getListMates());
	        
	        
	        if(request.getParameter("docsID") != null) {
	        	Long docID = Long.parseLong(request.getParameter("docsID"));
	        	DocumentBo docBo = new DocumentBo();
	        	Document docs = docBo.getDocument(docID);
	        	if(docs != null) {
	        		CategoryBo cateBo = new CategoryBo();
		        	MaterialBo mateBo = new MaterialBo();
		        	UserBo uBo = new UserBo();
		        	
		        	Category cateOfDocs = cateBo.getCategoryByID(docs.getCategoryID());
		        	Material mateOfDocs = mateBo.getMaterialByID(docs.getMaterialID());
		        	User uploadedBy = uBo.getUserByID(docs.getUploadedBy());
		        	
	        		request.setAttribute("docs", docs);
	        		request.setAttribute("cateOfDocs", cateOfDocs);
	        		request.setAttribute("mateOfDocs", mateOfDocs);
	        		request.setAttribute("uploadedBy", uploadedBy);
	        		
	        		RequestDispatcher rd = request.getRequestDispatcher("User/detail-docs.jsp");
	                rd.forward(request, response);
	                return;
	        	}
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		

        
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package ControllerUser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BookmarkModal.BookmarkBo;
import CategoryModal.Category;
import CategoryModal.CategoryBo;
import CommonModal.MethodCommon;
import DocumentModal.Document;
import DocumentModal.DocumentBo;
import MaterialModal.Material;
import MaterialModal.MaterialBo;
import UserModal.User;
import UserModal.UserBo;
import V_DetailsDoc.DetailsDoc;
import V_DetailsDoc.DetailsDocBo;

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
	        HttpSession session = request.getSession();
	        
	        request.setAttribute("listCates", MethodCommon.getListCates());
	        request.setAttribute("listMates", MethodCommon.getListMates());
	        
			User user = null;
			Boolean isMarked = false;
			
	        if(session.getAttribute("user") != null) 
	        	user = (User)(session.getAttribute("user"));
	        
	        if(request.getParameter("docsID") != null) {
	        	Long docID = Long.parseLong(request.getParameter("docsID"));
	        	
	        	DetailsDocBo dtdocsBo = new DetailsDocBo();
	        	
	        	DetailsDoc dtlDocs = dtdocsBo.getDetailsDocByID(docID);
	        	if(dtlDocs != null) {
		        	BookmarkBo bmkBo = new BookmarkBo();
		        	
		        	ArrayList<DetailsDoc> lstDocsSuggest = dtdocsBo.getListDocsSuggest(docID, dtlDocs.getCategoryID());
		        	if(user != null) {
		        		isMarked = bmkBo.hasUserMarkedDocs(user.getUserID(), docID);
		        	}
		        	
		        	
	        		request.setAttribute("dtlDocs", dtlDocs);
	        		request.setAttribute("lstDocsSuggest", lstDocsSuggest);
	        		request.setAttribute("isMarked", isMarked);
	        		
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

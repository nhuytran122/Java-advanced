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
import CommonModal.MethodCommon;
import LikeModal.LikeBo;
import UserModal.User;
import V_DetailsCommentModal.DetailsCommentBo;
import V_DetailsCommentModal.DetailsComment;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

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
			Long userID = null;
	        if(session.getAttribute("user") != null) {
	        	user = (User)(session.getAttribute("user"));
	        	userID = user.getUserID();
	        }
	       
	        if(request.getParameter("docsID") != null) {
	        	Long docID = Long.parseLong(request.getParameter("docsID"));
	        	
	        	DetailsDocBo dtdocsBo = new DetailsDocBo();
	        	
	        	DetailsDoc dtlDocs = dtdocsBo.getDetailsDocByID(docID);
	        	if(dtlDocs != null) {
		        	BookmarkBo bmkBo = new BookmarkBo();
		        	boolean isMarked = false;
		        	
		        	ArrayList<DetailsDoc> lstDocsSuggest = dtdocsBo.getListDocsSuggest(docID, dtlDocs.getCategoryID());
		        	if(user != null) {
		        		isMarked = bmkBo.hasUserMarkedDocs(userID, docID);
		        	}
		        	
	        		request.setAttribute("dtlDocs", dtlDocs);
	        		request.setAttribute("lstDocsSuggest", lstDocsSuggest);
	        		request.setAttribute("isMarked", isMarked);
	        		
	        		RequestDispatcher rd = request.getRequestDispatcher("User/detail-docs.jsp");
	                rd.forward(request, response);
	                return;
	        	}
	        }
	        else if(request.getParameter("postID") != null || request.getAttribute("postID") != null) {
	        	if(session.getAttribute("user") == null) {
	        		response.sendRedirect("login");
	                return;
	        	}
	        	Long postID = null;
	        	if(request.getParameter("postID") != null)
	        		postID = Long.parseLong(request.getParameter("postID"));
	        	else
	        		postID = (Long)request.getAttribute("postID");

	        	DetailsPostBo dtdocsBo = new DetailsPostBo();
	        	DetailsCommentBo dtCmtBo = new DetailsCommentBo();
	        	boolean isLiked = false;
	        	
	        	DetailsPost dtlPost = dtdocsBo.getDetailsPostByID(postID);
	        	if(dtlPost != null) {
		        	LikeBo likeBo = new LikeBo();
		        	isLiked = likeBo.hasUserLikedPost(userID, postID);
		        	
		        	ArrayList<DetailsComment>  listCmts = dtCmtBo.getCommentsByPostID(postID);
		        	
	        		request.setAttribute("dtlPost", dtlPost);
	        		request.setAttribute("listCmts", listCmts);
	        		request.setAttribute("isLiked", isLiked);
	        		
	        		RequestDispatcher rd = request.getRequestDispatcher("User/detail-post.jsp");
	                rd.forward(request, response);
	                return;
	        	}
	        }
	        else
	        	response.sendRedirect("home");
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

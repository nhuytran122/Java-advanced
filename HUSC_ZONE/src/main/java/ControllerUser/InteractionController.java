package ControllerUser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BookmarkModal.BookmarkBo;
import CommentModal.CommentBo;
import CommonModal.Constants;
import LikeModal.LikeBo;
import ReportModal.ReportBo;
import UserModal.User;

@WebServlet("/interact")
public class InteractionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InteractionController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            User user = null;
            Long userID = null;
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            } else {
                user = (User) session.getAttribute("user");
                userID = user.getUserID();
            }

            if (request.getParameter("docsID") != null) {
                Long docsID = Long.parseLong(request.getParameter("docsID"));
                BookmarkBo bmkBo = new BookmarkBo();

	            if (request.getParameter("btn-mark") != null) {
	                String bmark = request.getParameter("btn-mark");
	                if (bmark.equals("mark")) {
	                    bmkBo.addBookmark(docsID, userID);  
	                } else {
	                    bmkBo.deleteBookmark(docsID, userID);  
	                }
	                request.setAttribute("docsID", docsID);
	                
	                if(request.getParameter("unMarkInList") != null) {
	                	response.sendRedirect("liked-docs");
	                    return;
	                }
	                //C1: response.sendRedirect("details?docsID=" + docsID);
	                //return;
	                RequestDispatcher rd = request.getRequestDispatcher("details");
	                rd.forward(request, response); 
	            } 
            }
            
            if (request.getParameter("postID") != null) {
            	LikeBo likeBo = new LikeBo();
            	Long postID = Long.parseLong(request.getParameter("postID"));
            
	            if (request.getParameter("btn-like") != null) {
	                String blike = request.getParameter("btn-like");
	                if (blike.equals("like")) {
	                    likeBo.addLike(postID, userID);  
	                } else {
	                	likeBo.unLike(postID, userID);  
	                }
	                request.setAttribute("postID", postID);
	                
	                if(request.getParameter("unLikeInList") != null) {
	                	response.sendRedirect("activity-history");
	                    return;
	                }
	                //C1: response.sendRedirect("details?postID=" + postID);
	                //return;
	                RequestDispatcher rd = request.getRequestDispatcher("details");
	                rd.forward(request, response); 
	            } 
	            
	            if(request.getParameter("btn-report") != null) {
	            	ReportBo rpBo = new ReportBo();
	            	String contentRp = request.getParameter("txtContentReport");
	            	rpBo.addReport(postID, userID, contentRp);
	            	
	            	if(request.getParameter("reportInDetail") != null) {
	            		request.setAttribute("postID", postID);
	            		RequestDispatcher rd = request.getRequestDispatcher("details");
		                rd.forward(request, response); 
		                return;
	                }
	            	else if(request.getParameter("reportInUserProfile") != null) {
	            		Long sttOf = Long.parseLong(request.getParameter("sttOf"));
	            		request.setAttribute("userId", sttOf);
	            		RequestDispatcher rd = request.getRequestDispatcher("user-profile");
		                rd.forward(request, response); 
		                return;
	                }
	            	response.sendRedirect("status-post");
	                return;
	            }
	            
	            if(request.getParameter("btn-cmt") != null) {
	            	CommentBo cmtBo = new CommentBo();
	            	String contentCmt = request.getParameter("txtContentCmt");
	            	cmtBo.addComment(postID, userID, contentCmt);
	            	
	            	if(request.getParameter("cmtInDetail") != null) {
	            		request.setAttribute("postID", postID);
	            		RequestDispatcher rd = request.getRequestDispatcher("details");
		                rd.forward(request, response); 
		                return;
	                }
	            	response.sendRedirect("status-post");
	                return;
	            }
	            
	            if(request.getParameter("btnDeleteCmt") != null) {
	            	Long cmtID = Long.parseLong(request.getParameter("btnDeleteCmt"));
	            	CommentBo cmtBo = new CommentBo();
	            	cmtBo.deleteComment(cmtID);
	            	if(request.getParameter("deleteInList") != null) {
	            		request.setAttribute("filterID", Constants.FILTER_COMMENTED);
	            		RequestDispatcher rd = request.getRequestDispatcher("activity-history");
	            		rd.forward(request, response); 
		                return;
	                }
	            	else {
		        		RequestDispatcher rd = request.getRequestDispatcher("details");
		        		rd.forward(request, response); 
		                return;
	            	}
	            }
	            
	            if(request.getParameter("btnEditCmt") != null) {
	            	Long cmtID = Long.parseLong(request.getParameter("btnEditCmt"));
	            	CommentBo cmtBo = new CommentBo();
	            	String contentEdit = request.getParameter("txtEditContentCmt");
	            	cmtBo.updateComment(cmtID, contentEdit);
	            	request.setAttribute("postID", postID);
		        	RequestDispatcher rd = request.getRequestDispatcher("details");
		        	rd.forward(request, response); 
		            return;
	            }
            }
            response.sendRedirect("home");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

package ControllerUser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BookmarkModal.BookmarkBo;
import CommonModal.Constants;
import CommonModal.ControllerUtils;
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
            HttpSession session = request.getSession();
            User user = ControllerUtils.getUserFromSession(session, response);
            
            if (request.getParameter("docsID") != null) {
                handleDocumentDetails(request, response, user);
            } else if (getPostID(request) != null) {
            	if (user == null) {
                	response.sendRedirect("login");
                	return;
                }
            	handlePostDetails(request, response, user);
            	return;
            }            
            else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void handleDocumentDetails(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        Long docID = Long.parseLong(request.getParameter("docsID"));
        DetailsDocBo dtdocsBo = new DetailsDocBo();
        DetailsDoc dtlDocs = dtdocsBo.getDetailsDocByID(docID);

        if (dtlDocs != null) {
            boolean isMarked = checkIfDocumentIsBookmarked(user, docID);

            ArrayList<DetailsDoc> lstDocsSuggest = dtdocsBo.getListDocsSuggest(docID, dtlDocs.getCategoryID());
            request.setAttribute("dtlDocs", dtlDocs);
            request.setAttribute("lstDocsSuggest", lstDocsSuggest);
            request.setAttribute("isMarked", isMarked);
            ControllerUtils.forwardRequest(request, response, "User/detail-docs.jsp");
        }
    }

    private boolean checkIfDocumentIsBookmarked(User user, Long docID) throws Exception {
        if (user != null) {
            BookmarkBo bmkBo = new BookmarkBo();
            return bmkBo.hasUserMarkedDocs(user.getUserID(), docID);
        }
        return false;
    }

    private void handlePostDetails(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        Long postID = getPostID(request);
        DetailsPostBo dtdocsBo = new DetailsPostBo();
        DetailsPost dtlPost = dtdocsBo.getDetailsPostByID(postID);
        
        if(dtlPost.getPostVisibility().equals(Constants.POST_PRIVATE)) {
        	ControllerUtils.forwardRequest(request, response, "User/locked-post.jsp");
            return;
        }
        if (dtlPost != null) {
            boolean isLiked = checkIfPostIsLiked(user, postID);
            DetailsCommentBo dtCmtBo = new DetailsCommentBo();
            ArrayList<DetailsComment> listCmts = dtCmtBo.getCommentsByPostID(postID);

            request.setAttribute("dtlPost", dtlPost);
            request.setAttribute("listCmts", listCmts);
            request.setAttribute("isLiked", isLiked);
            ControllerUtils.forwardRequest(request, response, "User/detail-post.jsp");
        }
    }

    private Long getPostID(HttpServletRequest request) {
        if (request.getParameter("postID") != null) {
            return Long.parseLong(request.getParameter("postID"));
        } else {
            return (Long) request.getAttribute("postID");
        }
    }

    private boolean checkIfPostIsLiked(User user, Long postID) throws Exception {
        LikeBo likeBo = new LikeBo();
        return likeBo.hasUserLikedPost(user.getUserID(), postID);
    }
}

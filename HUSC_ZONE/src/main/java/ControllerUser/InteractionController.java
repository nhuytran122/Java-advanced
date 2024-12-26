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
import CommonModal.MethodCommon;
import LikeModal.LikeBo;
import NotificationModal.NotificationBo;
import ReportModal.ReportBo;
import StatusPostModal.StatusPost;
import StatusPostModal.StatusPostBo;
import UserModal.User;

@WebServlet("/interact")
public class InteractionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InteractionController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = MethodCommon.getUserFromSession(session, response);
            if (user == null) {
            	response.sendRedirect("login");
            	return;
            }

            Long userID = user.getUserID();
            if (request.getParameter("docsID") != null) {
                handleBookmark(request, response, userID);
            } else if (request.getParameter("postID") != null) {
                handlePost(request, response, userID);
            } else {
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBookmark(HttpServletRequest request, HttpServletResponse response, Long userID) throws Exception {
        Long docsID = Long.parseLong(request.getParameter("docsID"));
        BookmarkBo bmkBo = new BookmarkBo();
        String action = request.getParameter("btn-mark");

        if (action != null) {
            if ("mark".equals(action)) {
                bmkBo.addBookmark(docsID, userID);
            } else {
                bmkBo.deleteBookmark(docsID, userID);
            }
            String redirectPage = request.getParameter("unMarkInList") != null ? "liked-docs" : "details";
            request.setAttribute("docsID", docsID);
            RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
            rd.forward(request, response);
        }
    }

    private void handlePost(HttpServletRequest request, HttpServletResponse response, Long userID) throws Exception {
        Long postID = Long.parseLong(request.getParameter("postID"));
        String action = request.getParameter("btn-like");

        if (action != null) {
            handleLike(request, response, postID, userID);
        } else if (request.getParameter("btn-report") != null) {
            handleReport(request, response, postID, userID);
        } else if (request.getParameter("btn-cmt") != null) {
            handleComment(request, response, postID, userID);
        } else if (request.getParameter("btnDeleteCmt") != null) {
            handleDeleteComment(request, response);
        } else if (request.getParameter("btnEditCmt") != null) {
            handleEditComment(request, response, postID);
        } else if (request.getParameter("btnDeleteReport") != null) {
            handleDeleteReport(request, response);
        }
    }

    private void handleLike(HttpServletRequest request, HttpServletResponse response, Long postID, Long userID) throws Exception {
        LikeBo likeBo = new LikeBo();
        String action = request.getParameter("btn-like");

        if ("like".equals(action)) {
            likeBo.addLike(postID, userID);
        } else {
            likeBo.unLike(postID, userID);
        }

        String redirectPage = request.getParameter("unLikeInList") != null ? "activity-history" : "details";
        request.setAttribute("postID", postID);
        RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
        rd.forward(request, response);
    }

    private void handleReport(HttpServletRequest request, HttpServletResponse response, Long postID, Long userID) throws Exception {
        ReportBo rpBo = new ReportBo();
        NotificationBo notiBo = new NotificationBo();
        StatusPostBo sttBo = new StatusPostBo();
        StatusPost stt = sttBo.getStatusPostByID(postID);

        String contentRp = request.getParameter("txtContentReport");
        Long reportID = rpBo.addReport(postID, userID, contentRp);
        if (!userID.equals(stt.getUploadBy())) {
            notiBo.createNotiRelatedToReportPost(stt.getUploadBy(), reportID, userID, postID);
        }

        String redirectPage = request.getParameter("reportInDetail") != null ? "details" : "status-post";
        request.setAttribute("postID", postID);
        RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
        rd.forward(request, response);
    }

    private void handleComment(HttpServletRequest request, HttpServletResponse response, Long postID, Long userID) throws Exception {
        CommentBo cmtBo = new CommentBo();
        NotificationBo notiBo = new NotificationBo();
        StatusPostBo sttBo = new StatusPostBo();
        StatusPost stt = sttBo.getStatusPostByID(postID);

        String contentCmt = request.getParameter("txtContentCmt");
        Long cmtID = cmtBo.addComment(postID, userID, contentCmt);
        if (!userID.equals(stt.getUploadBy())) {
            notiBo.createNotiRelatedToCmtPost(stt.getUploadBy(), cmtID, userID, postID);
        }

//        String redirectPage = request.getParameter("cmtInDetail") != null ? "details" : "status-post";
        request.setAttribute("postID", postID);
        RequestDispatcher rd = request.getRequestDispatcher("details");
        rd.forward(request, response);
    }

    private void handleDeleteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long cmtID = Long.parseLong(request.getParameter("btnDeleteCmt"));
        CommentBo cmtBo = new CommentBo();
        cmtBo.deleteComment(cmtID);

        String redirectPage = request.getParameter("deleteInList") != null ? "activity-history" : "details";
        request.setAttribute("filterID", Constants.FILTER_COMMENTED);
        RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
        rd.forward(request, response);
    }

    private void handleEditComment(HttpServletRequest request, HttpServletResponse response, Long postID) throws Exception {
        Long cmtID = Long.parseLong(request.getParameter("btnEditCmt"));
        String contentEdit = request.getParameter("txtEditContentCmt");
        CommentBo cmtBo = new CommentBo();
        cmtBo.updateComment(cmtID, contentEdit);

        request.setAttribute("postID", postID);
        RequestDispatcher rd = request.getRequestDispatcher("details");
        rd.forward(request, response);
    }

    private void handleDeleteReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long reportID = Long.parseLong(request.getParameter("btnDeleteReport"));
        ReportBo rpBo = new ReportBo();
        rpBo.deleteReport(reportID);

        String redirectPage = request.getParameter("deleteInList") != null ? "activity-history" : "home";
        request.setAttribute("filterID", Constants.FILTER_REPORT);
        RequestDispatcher rd = request.getRequestDispatcher(redirectPage);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

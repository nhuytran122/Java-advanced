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

import CommonModal.Constants;
import CommonModal.MethodCommon;
import UserModal.User;
import V_DetailsCommentModal.DetailsComment;
import V_DetailsCommentModal.DetailsCommentBo;
import V_DetailsLikedModal.DetailsLiked;
import V_DetailsLikedModal.DetailsLikedBo;
import V_DetailsReportModal.DetailsReport;
import V_DetailsReportModal.DetailsReportBo;

@WebServlet("/activity-history")
public class ActivityHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActivityHistoryController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            User user = MethodCommon.getUserFromSession(session, response);

            if (user == null) {
                response.sendRedirect("login");
                return;
            }
            Long userID = user.getUserID();

            int page = getPageParameter(request);
            Long filterID = getFilterIDParameter(request);
            int rowCount = 0;

            if (filterID == Constants.FILTER_LIKED) {
                rowCount = handleLikedFilter(request, userID, page);
            } else if (filterID == Constants.FILTER_COMMENTED) {
                rowCount = handleCommentedFilter(request, userID, page);
            } else if (filterID == Constants.FILTER_REPORT) {
                rowCount = handleReportFilter(request, userID, page);
            }

            int pageCount = MethodCommon.calculatePageCount(rowCount, 9);

            request.setAttribute("filterID", filterID);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("User/activity-history.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private int getPageParameter(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        return (pageParam != null) ? Integer.parseInt(pageParam) : 1;
    }

    private Long getFilterIDParameter(HttpServletRequest request) {
        String filterParam = request.getParameter("filterID");
        if (filterParam != null) {
            return Long.parseLong(filterParam);
        }

        Object filterAttr = request.getAttribute("filterID");
        return (filterAttr != null) ? (Long) filterAttr : Constants.FILTER_LIKED;
    }

    private int handleLikedFilter(HttpServletRequest request, Long userID, int page) throws Exception {
        DetailsLikedBo likedBo = new DetailsLikedBo();
        ArrayList<DetailsLiked> dsLikes = likedBo.getListLikesByUserID(page, 9, userID);
        request.setAttribute("dsLikes", dsLikes);
        return likedBo.getCountLikesByUserID(userID);
    }

    private int handleCommentedFilter(HttpServletRequest request, Long userID, int page) throws Exception {
        DetailsCommentBo commentBo = new DetailsCommentBo();
        ArrayList<DetailsComment> dsCmts = commentBo.getCommentsByUserID(page, 9, userID);
        request.setAttribute("dsCmts", dsCmts);
        return commentBo.getCountCommentsByUserID(userID);
    }

    private int handleReportFilter(HttpServletRequest request, Long userID, int page) throws Exception {
        DetailsReportBo reportBo = new DetailsReportBo();
        ArrayList<DetailsReport> dsRpts = reportBo.getListReportsByUserID(page, 9, userID);
        request.setAttribute("dsRpts", dsRpts);
        return reportBo.getCountReportsByUserID(userID);
    }
}

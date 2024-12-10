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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }

            User user = (User) session.getAttribute("user");
            Long userID = user.getUserID();

            int page = 1;
            int pageSize = 9;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            Long filterID = Constants.FILTER_LIKED;
            if (request.getParameter("filterID") != null) {
                filterID = Long.parseLong(request.getParameter("filterID"));
            }
            // cho trường hợp controller interact (action xóa) truyền filterID sang
            else if(request.getAttribute("filterID") != null) {
            	filterID = (Long)(request.getAttribute("filterID"));
            }
            int rowCount = 0;

            // Lấy dữ liệu theo bộ lọc
            if (filterID == Constants.FILTER_LIKED) {
            	ArrayList<DetailsLiked> dsLikes = null;
                DetailsLikedBo dtLBo = new DetailsLikedBo();
                dsLikes = dtLBo.getListLikesByUserID(page, pageSize, userID);
                rowCount = dtLBo.getCountLikesByUserID(userID);
                request.setAttribute("dsLikes", dsLikes);
                
            } else if (filterID == Constants.FILTER_COMMENTED) {
            	ArrayList<DetailsComment> dsCmts = null;
                DetailsCommentBo dtCBo = new DetailsCommentBo();
                dsCmts = dtCBo.getCommentsByUserID(page, pageSize, userID);
                rowCount = dtCBo.getCountCommentsByUserID(userID);
                request.setAttribute("dsCmts", dsCmts);
                
            } else if (filterID == Constants.FILTER_REPORT) {
            	ArrayList<DetailsReport> dsRpts = null;
                DetailsReportBo dtRBo = new DetailsReportBo();
                dsRpts = dtRBo.getListReportsByUserID(page, pageSize, userID);
                rowCount = dtRBo.getCountReportsByUserID(userID);
                request.setAttribute("dsRpts", dsRpts);
            }

            int pageCount = rowCount / pageSize;
            if (rowCount % pageSize > 0) {
                pageCount += 1;
            }

            request.setAttribute("filterID", filterID);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("User/liked-status.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

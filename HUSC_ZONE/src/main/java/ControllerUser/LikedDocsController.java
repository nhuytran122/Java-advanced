package ControllerUser;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import CommonModal.MethodCommon;
import UserModal.User;
import V_DetailsBookmarkModal.DetailsBookmark;
import V_DetailsBookmarkModal.DetailsBookmarkBo;

@WebServlet("/liked-docs")
public class LikedDocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikedDocsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            User currentUser = ControllerUtils.getUserFromSession(session, response);
            if (!ControllerUtils.ensureUserLogin(session, response, request)) {
            	return;
            }
            DetailsBookmarkBo dtBmBo = new DetailsBookmarkBo();
            int page = ControllerUtils.getPage(request);
            
            ArrayList<DetailsBookmark> ds = dtBmBo.getListBookmarksByUserID(page, Constants.PAGE_SIZE, currentUser.getUserID());
            		
            int rowCount = dtBmBo.getCountBookmarksByUserID(currentUser.getUserID());
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);

            request.setAttribute("ds", ds);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "User/liked-docs.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import UserModal.UserBo;
import V_DetailsDocModal.DetailsDoc;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/docs-of-user")
public class ShowDocsOfUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowDocsOfUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            ControllerUtils.ensureUserLogin(session, response, request);
            Long posterID = 0L;
            User currentUser = ControllerUtils.getUserFromSession(session, response);
            posterID = currentUser.getUserID();

            DetailsDocBo dtdocBo = new DetailsDocBo();
            UserBo userBo = new UserBo();
            
            int page = ControllerUtils.getPage(request);
            if (request.getParameter("posterID") != null) {
            	posterID = Long.parseLong(request.getParameter("posterID"));
            }
            
            ArrayList<DetailsDoc> ds = dtdocBo.getDocsByUserIDPagination(page, Constants.PAGE_SIZE, posterID);
            User poster = userBo.getUserByID(posterID);
            		
            int rowCount = dtdocBo.getCountDocsByUserID(posterID);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);

            request.setAttribute("ds", ds);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            request.setAttribute("namePoster", poster.getName());
            request.setAttribute("IDPoster", poster.getUserID());
            ControllerUtils.forwardRequest(request, response, "User/docs-of-user.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
